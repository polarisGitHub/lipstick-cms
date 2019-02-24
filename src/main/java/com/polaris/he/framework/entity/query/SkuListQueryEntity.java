package com.polaris.he.framework.entity.query;

import com.polaris.he.framework.entity.page.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-02-23 22:38
 * Description:
 */
@Getter
@Setter
@ToString
public class SkuListQueryEntity extends Page {

    private String type;

    private String brandCode;

    private String skuCode;

    private String skuName;
}