package com.polaris.he.lipstick.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.polaris.he.application.utils.JsonUtils;
import com.polaris.he.framework.annotation.FrameworkBizConverter;
import com.polaris.he.framework.dao.object.SkuDO;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.entity.LipstickExtension;
import com.polaris.he.lipstick.entity.SkuDetailItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * User: hexie
 * Date: 2019-02-25 21:29
 * Description:
 */
@Service
@FrameworkBizConverter(type = CosmeticsEnum.LIPSTICK, biz = "skuDetailSave")
public class JsonNodeSkuDetailItemConverter implements Converter<JsonNode, SkuDO> {

    @Override
    public SkuDO convert(JsonNode source) {
        SkuDO sku = new SkuDO();
        Optional<JsonNode> sourceOp = Optional.of(source);
        sku.setId(sourceOp.map(l -> l.get("id")).map(JsonNode::asLong).orElse(null));
        sku.setBrandCode(sourceOp.map(l -> l.get("brandCode")).map(JsonNode::asText).orElse(null));
        sku.setGoodsCode(sourceOp.map(l -> l.get("goodsCode")).map(JsonNode::asText).orElse(null));
        sku.setSkuCode(sourceOp.map(l -> l.get("skuCode")).map(JsonNode::asText).orElse(null));
        sku.setSkuName(sourceOp.map(l -> l.get("skuName")).map(JsonNode::asText).orElse(null));
        sku.setUrl(sourceOp.map(l -> l.get("url")).map(JsonNode::asText).orElse(null));
        LipstickExtension extension = new LipstickExtension();
        extension.setColorNo(sourceOp.map(l -> l.get("colorNo")).map(JsonNode::asText).orElse(null));
        extension.setImgs(sourceOp.map(l -> l.withArray("imgs")).map(l -> StreamSupport.stream(l.spliterator(), false).map(JsonNode::asText).collect(Collectors.toList())).orElse(null));
        extension.setFigure(sourceOp.map(l -> l.get("figure")).map(JsonNode::asText).orElse(null));
        extension.setColor(sourceOp.map(l -> l.get("color")).map(JsonNode::asText).orElse(null));
        extension.setColor1(sourceOp.map(l -> l.get("color1")).map(JsonNode::asText).orElse(null));
        sku.setExtension(JsonUtils.javaObjectToObjectJson(extension));
        return sku;
    }
}