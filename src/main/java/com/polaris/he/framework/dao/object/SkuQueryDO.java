package com.polaris.he.framework.dao.object;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-02-23 22:56
 * Description:
 */
@Getter
@Setter
@ToString
public class SkuQueryDO {

    private String type;

    private String brandCode;

    private String skuCode;

    private String skuName;
}