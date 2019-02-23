package com.polaris.he.framework.dao;

import com.polaris.he.framework.dao.object.BrandCategoryMappingDO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-22 22:21
 * Description:
 */
public interface BrandCategoryMappingDao extends Dao {

    /**
     *
     * @param type
     * @param brands
     * @return
     */
    List<BrandCategoryMappingDO> getCategoryByBrands(@Param("type") String type, @Param("brands") Collection<String> brands);
}