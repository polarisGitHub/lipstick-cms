package com.polaris.he.framework.entity.sku;

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
public class Goods extends BaseGoodsInfo {

    private String goodsName;

    private String illustration;

    private String url;
}