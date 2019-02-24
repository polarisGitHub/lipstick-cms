package com.polaris.he.lipstick.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-16 22:04
 * Description:
 */
@Getter
@Setter
@ToString
public class LipstickUploadData {

    @JsonProperty(value = "brand_name")
    private String brandName;

    @JsonProperty(value = "catalog_name")
    private String catalogName;

    @JsonProperty(value = "goods_code")
    private String goodsCode;

    @JsonProperty(value = "goods_name")
    private String goodsName;

    @JsonProperty(value = "goods_url")
    private String goodsUrl;

    @JsonProperty(value = "goods_illustration")
    private String goodsIllustration;

    @JsonProperty(value = "sku_code")
    private String skuCode;

    @JsonProperty(value = "sku_name")
    private String skuName;

    @JsonProperty(value = "sku_url")
    private String skuUrl;

    @JsonProperty(value = "sku_img_urls")
    private String skuImgUrls;

    @JsonProperty(value = "sku_img_downloads")
    private String skuImgDownloadFile;

    @JsonProperty(value = "color_no")
    private String colorNo;

    @JsonProperty(value = "color_card_url")
    private String colorCardUrl;

    @JsonProperty(value = "color")
    private String color;

    @JsonProperty(value = "color1")
    private String color1;
}