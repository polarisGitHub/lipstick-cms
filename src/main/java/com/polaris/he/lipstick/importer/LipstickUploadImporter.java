package com.polaris.he.lipstick.importer;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.polaris.he.application.utils.JsonUtils;
import com.polaris.he.framework.entity.*;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.framework.importer.AbstractUploadImporter;
import com.polaris.he.framework.importer.converter.UploadByteConverter;
import com.polaris.he.framework.importer.data.UploadValidateErrorLine;
import com.polaris.he.framework.importer.data.UploadValidateResult;
import com.polaris.he.framework.service.BrandService;
import com.polaris.he.framework.service.CategoryService;
import com.polaris.he.framework.service.GoodsService;
import com.polaris.he.framework.service.SkuManagerService;
import com.polaris.he.lipstick.entity.LipstickExtension;
import com.polaris.he.lipstick.entity.LipstickUploadData;
import com.polaris.he.lipstick.entity.SkuListItem;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-16 23:30
 * Description:
 */
@Component
public class LipstickUploadImporter extends AbstractUploadImporter<LipstickUploadData> {

    @Resource(type = LipstickUploadConverter.class)
    private UploadByteConverter converter;

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private SkuManagerService skuService;


    @Override
    public List<LipstickUploadData> byteConvert(byte[] data, String extension) {
        return converter.convert(data, extension, LipstickUploadData.class);
    }

    @Override
    public UploadValidateResult validate(List<LipstickUploadData> data) {
        Set<String> brandSet = brandService.getBrands(CosmeticsEnum.LIPSTICK.getCode()).stream().map(Brand::getName).collect(Collectors.toSet());
        Set<String> categorySet = categoryService.getCategories(CosmeticsEnum.LIPSTICK.getCode()).stream().map(Category::getName).collect(Collectors.toSet());

        UploadValidateResult ret = new UploadValidateResult(new LinkedList<>());
        int index = 0;
        for (LipstickUploadData item : data) {
            List<String> errors = new LinkedList<>();
            // brand
            if (!brandSet.contains(item.getBrandName())) {
                errors.add(String.format("【%s】不存在", item.getBrandName()));
            }
            // category
            if (!categorySet.contains(item.getCatalogName())) {
                errors.add(String.format("【%s】不存在", item.getCatalogName()));
            }
            if (!errors.isEmpty()) {
                ret.getResult().add(new UploadValidateErrorLine(index, String.join("，", errors)));
            }
            index++;
        }
        return ret;
    }

    @Override
    public void resolved(List<LipstickUploadData> data) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }

        Map<String, Brand> brandMap = brandService.getBrands(CosmeticsEnum.LIPSTICK.getCode()).stream().collect(Collectors.toMap(Brand::getName, Function.identity()));
        Map<String, Category> categoryMap = categoryService.getCategories(CosmeticsEnum.LIPSTICK.getCode()).stream().collect(Collectors.toMap(Category::getName, Function.identity()));
        Set<String> skuSet = new HashSet<>();
        Map<String, Goods> goodsMap = new HashMap<>();
        List<SkuListItem> skuList = new LinkedList<>();
        Multimap<String, Category> goodsCategoryMapping = ArrayListMultimap.create();
        data.forEach(l -> {
            // goods
//            if (!goodsMap.containsKey(l.getGoodsCode())) {
//                Goods goods = new Goods();
//                goods.setBrandCode(brandMap.get(l.getBrandName()).getCode());
//                goods.setType(CosmeticsEnum.LIPSTICK.getCode());
//                goods.setGoodsCode(l.getGoodsCode());
//                goods.setGoodsName(l.getGoodsName());
//                goods.setUrl(l.getGoodsUrl());
//                goods.setIllustration(l.getGoodsIllustration());
//                goodsMap.put(l.getGoodsCode(), goods);
//                goodsCategoryMapping.put(goods.getGoodsCode(), categoryMap.get(l.getCatalogName()));
//            }
//            // sku
//            if (!skuSet.contains(l.getSkuCode())) {
//                skuSet.add(l.getSkuCode());
//                SkuListItem sku = new SkuListItem();
//                sku.setBrandCode(brandMap.get(l.getBrandName()).getCode());
//                sku.setType(CosmeticsEnum.LIPSTICK.getCode());
//                sku.setGoodsCode(l.getGoodsCode());
//                sku.setSkuCode(l.getSkuCode());
//                sku.setSkuName(l.getSkuName());
//                sku.setSkuByName("");
//                sku.setUrl(l.getSkuUrl());
//
//                LipstickExtension extension = new LipstickExtension();
//                extension.setColorNo(l.getColorNo());
//                extension.setColor(l.getColor());
//                if (!StringUtils.equals(l.getColor(), l.getColor1())) {
//                    extension.setColor1(l.getColor1());
//                }
//
//                String[] imgs = StringUtils.split(l.getSkuImgDownloadFile(), ",");
//                if (ArrayUtils.isNotEmpty(imgs)) {
//                    extension.setFigure(imgs[0]);
//                    extension.setImgs(Arrays.asList(imgs));
//                }
//                sku.setExtension(JsonUtils.javaObjectToObjectJson(extension));
//                skuList.add(sku);
//            }
        });

        Collection<GoodsCategoryMapping> mappings = new LinkedList<>();
        Collection<Goods> goodsList = goodsMap.values();
        goodsList.forEach(l -> {
            GoodsCategoryMapping mapping = new GoodsCategoryMapping();
            mapping.setGoods(l);
            mapping.setCategories(goodsCategoryMapping.get(l.getGoodsCode()));
            mappings.add(mapping);
        });
        goodsService.saveGoodsCategoriesMapping(mappings);
        goodsService.save(goodsList);
        skuService.importer(skuList);
    }
}