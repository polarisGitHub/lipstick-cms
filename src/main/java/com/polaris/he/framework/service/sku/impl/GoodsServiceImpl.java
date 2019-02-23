package com.polaris.he.framework.service.sku.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.polaris.he.framework.dao.GoodsCategoryMappingDao;
import com.polaris.he.framework.dao.GoodsDao;
import com.polaris.he.framework.dao.object.GoodsCategoryMappingDO;
import com.polaris.he.framework.dao.object.GoodsDO;
import com.polaris.he.framework.entity.sku.BaseGoodsInfo;
import com.polaris.he.framework.entity.sku.Goods;
import com.polaris.he.framework.entity.sku.GoodsCategoryMapping;
import com.polaris.he.framework.service.sku.GoodsService;
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
 * Date: 2019-01-10 22:54
 * Description:
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private GoodsCategoryMappingDao goodsCategoryMappingDao;

    private static final List<Field> GOODS_UNIQUE_FIELDS = Arrays.asList(
            FieldUtils.getField(GoodsDO.class, "brandCode", true),
            FieldUtils.getField(GoodsDO.class, "type", true),
            FieldUtils.getField(GoodsDO.class, "goodsCode", true)
    );

    @Override
    @Transactional
    public int save(Collection<Goods> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return 0;
        }
        String type = getTypeInCollection(collection);

        Set<String> goodsCodeSet = collection.stream().map(Goods::getGoodsCode).collect(Collectors.toSet());
        List<GoodsDO> goodsInDb = goodsDao.getByCodeList(type, goodsCodeSet);
        List<GoodsDO> goodsToSave = collection.stream().map(l -> BeanCopyUtils.copyObject(l, new GoodsDO())).collect(Collectors.toList());

        DiffUtils.DiffResult<GoodsDO> goodsDiff = DiffUtils.diff(goodsToSave, goodsInDb, GOODS_UNIQUE_FIELDS, GoodsDO::equals);

        Collection<GoodsDO> insert = goodsDiff.getAdd();
        if (CollectionUtils.isNotEmpty(insert)) {
            log.info("goods insert,code={}", insert.stream().map(GoodsDO::getGoodsCode).collect(Collectors.toList()));
            goodsDao.insert(insert);
        }

        Collection<GoodsDO> update = goodsDiff.getNotEqual();
        if (CollectionUtils.isNotEmpty(update)) {
            log.info("goods update,code={}", update.stream().map(GoodsDO::getGoodsCode).collect(Collectors.toList()));
            update.forEach(l -> goodsDao.update(l));
        }
        return 1;
    }

    @Override
    @Transactional
    public int saveGoodsCategoriesMapping(Collection<GoodsCategoryMapping> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return 0;
        }
        String type = getTypeInCollection(collection.stream().map(GoodsCategoryMapping::getGoods).collect(Collectors.toList()));
        Multimap<String, String> map = HashMultimap.create();
        collection.forEach(l -> l.getCategories().forEach(ll -> map.put(l.getGoods().getGoodsCode(), ll.getCode())));
        goodsCategoryMappingDao.deleteByGoodsCode(type, map.keySet());

        List<GoodsCategoryMappingDO> inserts = map.entries().stream().map(l -> {
            GoodsCategoryMappingDO data = new GoodsCategoryMappingDO();
            data.setType(type);
            data.setGoodsCode(l.getKey());
            data.setCategoryCode(l.getValue());
            return data;
        }).collect(Collectors.toList());
        return goodsCategoryMappingDao.insert(inserts);
    }

    @Override
    public Goods getByCode(String brandCode, String type, String skuCode) {
        GoodsDO goodsDO = goodsDao.getByCode(brandCode, type, skuCode);
        return BeanCopyUtils.copyObject(goodsDO, new Goods());
    }

    private String getTypeInCollection(Collection<Goods> collection) {
        List<String> types = collection.stream().map(BaseGoodsInfo::getType).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(types) || types.size() > 1) {
            throw new IllegalArgumentException();
        }
        return types.get(0);
    }
}