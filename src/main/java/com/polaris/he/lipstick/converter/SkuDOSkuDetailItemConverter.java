package com.polaris.he.lipstick.converter;

import com.polaris.he.application.utils.JsonUtils;
import com.polaris.he.framework.annotation.FrameworkBizConverter;
import com.polaris.he.framework.dao.object.SkuDO;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.entity.LipstickExtension;
import com.polaris.he.lipstick.entity.SkuDetailItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: hexie
 * Date: 2019-02-25 21:29
 * Description:
 */
@Service
@FrameworkBizConverter(type = CosmeticsEnum.LIPSTICK, biz = "skuDetailItem")
public class SkuDOSkuDetailItemConverter implements Converter<SkuDO, SkuDetailItem> {

    @Override
    public SkuDetailItem convert(SkuDO source) {
        SkuDetailItem sku = new SkuDetailItem();
        sku.setId(source.getId());
        sku.setBrandCode(source.getBrandCode());
        sku.setGoodsCode(source.getGoodsCode());
        sku.setSkuCode(source.getSkuCode());
        sku.setSkuName(source.getSkuName());
        sku.setUrl(source.getUrl());
        LipstickExtension extension = JsonUtils.toJavaObject(source.getExtension(), LipstickExtension.class);
        sku.setColorNo(Optional.ofNullable(extension).map(LipstickExtension::getColorNo).orElse(null));
        sku.setImgs(Optional.ofNullable(extension).map(LipstickExtension::getImgs).orElse(null));
        sku.setFigure(Optional.ofNullable(extension).map(LipstickExtension::getFigure).orElse(null));
        sku.setColor(Optional.ofNullable(extension).map(LipstickExtension::getColor).orElse(null));
        sku.setColor1(Optional.ofNullable(extension).map(LipstickExtension::getColor1).orElse(null));
        return sku;
    }
}