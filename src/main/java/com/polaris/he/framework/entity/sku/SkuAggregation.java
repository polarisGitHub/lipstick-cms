package com.polaris.he.framework.entity.sku;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 22:26
 * Description:
 */
@Getter
@Setter
@ToString
public class SkuAggregation {

    private Brand brand;

    // 一个sku可能在多个类别下
    private List<Category> categories;

    private Goods goods;

    private Sku sku;
}