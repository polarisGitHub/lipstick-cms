package com.polaris.he.framework.dao.object;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2018-12-17 22:10
 * Description:
 */
@Getter
@Setter
@ToString(callSuper = true)
public class BrandDO extends BaseDO {

    private Long id;

    private String type;

    private String code;

    private String name;
}
