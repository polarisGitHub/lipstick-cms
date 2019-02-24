package com.polaris.he.lipstick.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-10 22:26
 * Description:
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SkuListItem {

    private Long id;

    private String brandCode;

    private String skuCode;

    private String goodsCode;

    private String skuName;

    private String skuByName;

    private String url;

    private String colorNo;
}