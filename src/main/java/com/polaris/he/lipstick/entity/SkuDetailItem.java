package com.polaris.he.lipstick.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-02-25 21:30
 * Description:
 */
@Getter
@Setter
@ToString
public class SkuDetailItem {

    private Long id;

    private String brandCode;

    private String goodsCode;

    private String skuCode;

    private String skuName;

    private String skuByName;

    private String url;

    private String colorNo;

    private List<String> imgs;

    private String figure;

    private String color;

    private String color1;

}