package com.polaris.he.framework.dao;

import com.polaris.he.framework.dao.object.CategoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-22 22:20
 * Description:
 */
public interface CategoryDao extends Dao {

    /**
     * @param code
     * @return
     */
    CategoryDO getByCode(@Param("type") String type, @Param("code") String code);

    /**
     * @param type
     * @return
     */
    List<CategoryDO> getCategoriesByType(String type);
}