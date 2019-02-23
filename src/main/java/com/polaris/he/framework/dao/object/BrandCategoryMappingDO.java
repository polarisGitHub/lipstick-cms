package com.polaris.he.framework.dao.object;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2018-12-22 22:17
 * Description:
 */
@Getter
@Setter
@ToString
public class BrandCategoryMappingDO extends BaseDO {

    private Long id;

    private String type;

    private String brandCode;

    private String categoryCode;

    private CategoryDO category;

    private BrandDO brand;
}