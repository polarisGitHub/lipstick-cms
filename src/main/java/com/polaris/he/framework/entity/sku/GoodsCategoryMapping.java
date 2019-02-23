package com.polaris.he.framework.entity.sku;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * User: hexie
 * Date: 2019-01-18 23:27
 * Description:
 */
@Getter
@Setter
@ToString
public class GoodsCategoryMapping {

    private Goods goods;

    private Collection<Category> categories;
}