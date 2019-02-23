package com.polaris.he.framework.service.sku.impl;

import com.polaris.he.framework.dao.SkuDao;
import com.polaris.he.framework.dao.object.SkuDO;
import com.polaris.he.framework.entity.sku.*;
import com.polaris.he.framework.service.sku.BrandService;
import com.polaris.he.framework.service.sku.CategoryService;
import com.polaris.he.framework.service.sku.GoodsService;
import com.polaris.he.framework.service.sku.SkuService;
import com.polaris.he.framework.utils.BeanCopyUtils;
import com.polaris.he.framework.utils.DiffUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-10 22:36
 * Description:
 */
@Slf4j
@Service
public class SkuServiceImpl implements SkuService {

    private static final List<Field> SKU_UNIQUE_FIELDS = Arrays.asList(
            FieldUtils.getField(SkuDO.class, "brandCode", true),
            FieldUtils.getField(SkuDO.class, "type", true),
            FieldUtils.getField(SkuDO.class, "skuCode", true)
    );

    @Resource
    private SkuDao skuDao;

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private GoodsService goodsService;

    @Override
    @Transactional
    public int save(Collection<Sku> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return 0;
        }
        List<String> types = collection.stream().map(BaseSkuInfo::getType).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(types) || types.size() > 1) {
            throw new IllegalArgumentException();
        }
        String type = types.get(0);
        Set<String> skuCodeSet = collection.stream().map(Sku::getSkuCode).collect(Collectors.toSet());
        List<SkuDO> skuInDb = skuDao.getByCodeList(type, skuCodeSet);
        List<SkuDO> skuToSave = collection.stream().map(l -> BeanCopyUtils.copyObject(l, new SkuDO())).collect(Collectors.toList());

        DiffUtils.DiffResult<SkuDO> skuDiff = DiffUtils.diff(skuToSave, skuInDb, SKU_UNIQUE_FIELDS, SkuDO::equals);

        Collection<SkuDO> insert = skuDiff.getAdd();
        if (CollectionUtils.isNotEmpty(insert)) {
            log.info("sku insert,code={}", insert.stream().map(SkuDO::getSkuCode).collect(Collectors.toList()));
            skuDao.insert(insert);
        }

        Collection<SkuDO> update = skuDiff.getNotEqual();
        if (CollectionUtils.isNotEmpty(update)) {
            log.info("sku update,code={}", update.stream().map(SkuDO::getSkuCode).collect(Collectors.toList()));
            update.forEach(l -> skuDao.update(l));
        }
        return 1;
    }

    @Override
    public Sku getBySkuInfo(BaseSkuInfo baseSkuInfo) {
        SkuDO sku = skuDao.getSku(baseSkuInfo.getBrandCode(), baseSkuInfo.getType(), baseSkuInfo.getSkuCode());
        if (sku == null) {
            return null;
        }
        return BeanCopyUtils.copyObject(sku, new Sku());
    }

    @Override
    public SkuAggregation getAggregationBySkuInfo(BaseSkuInfo baseSkuInfo) {
        Sku sku = getBySkuInfo(baseSkuInfo);
        if (sku == null) {
            return null;
        }
        SkuAggregation ret = new SkuAggregation();
        ret.setSku(sku);
        ret.setBrand(getBrand(sku));
        // TODO
//        ret.setCategories(getCategories(type, sku));
        ret.setGoods(getGoods(sku));
        return ret;
    }

    private Goods getGoods(Sku sku) {
        return goodsService.getByCode(sku.getBrandCode(), sku.getType(), sku.getGoodsCode());
    }

    private List<Category> getCategories(Sku sku) {
        return categoryService.getCategoriesByGoods(sku.getType(), sku.getGoodsCode());
    }

    private Brand getBrand(Sku sku) {
        return brandService.getBrand(sku.getType(), sku.getBrandCode());
    }
}