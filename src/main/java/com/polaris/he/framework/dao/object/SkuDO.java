package com.polaris.he.framework.dao.object;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-05 22:32
 * Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SkuDO extends BaseDO {

    private Long id;

    private String type;

    private String brandCode;

    private String goodsCode;

    private String skuCode;

    private String skuName;

    private String skuByName;

    private String url;

    private JsonNode extension;
}