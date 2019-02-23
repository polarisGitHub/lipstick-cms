package com.polaris.he.framework.entity.sku;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-28 22:16
 * Description:
 */
@Getter
@Setter
@ToString
public class BaseGoodsInfo {

    private String brandCode;

    transient private String type; // 具体type具体业务关心，不在接口返回

    private String goodsCode;
}