package com.polaris.he.lipstick.converter;

import com.polaris.he.application.utils.JsonUtils;
import com.polaris.he.framework.annotation.FrameworkBizConverter;
import com.polaris.he.framework.dao.object.SkuDO;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.entity.SkuListItem;
import com.polaris.he.lipstick.entity.LipstickExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: hexie
 * Date: 2019-02-23 23:29
 * Description:
 */
@Service
@FrameworkBizConverter(type = CosmeticsEnum.LIPSTICK, biz = "skuListItem")
public class SkuDOSkuListItemConverter implements Converter<SkuDO, SkuListItem> {

    @Override
    public SkuListItem convert(SkuDO source) {
        SkuListItem sku = new SkuListItem();
        sku.setBrandCode(source.getBrandCode());
        sku.setGoodsCode(source.getGoodsCode());
        sku.setSkuCode(source.getSkuCode());
        sku.setSkuName(source.getSkuName());
        LipstickExtension extension = JsonUtils.toJavaObject(source.getExtension(), LipstickExtension.class);
        sku.setColorNo(Optional.ofNullable(extension).map(LipstickExtension::getColorNo).orElse(""));
        return sku;
    }
}