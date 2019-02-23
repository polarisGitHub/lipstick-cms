package com.polaris.he.framework.dao;

import com.polaris.he.framework.dao.object.BrandDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-17 22:09
 * Description:
 */
public interface BrandDao extends Dao {

    /**
     * @param type
     * @return
     */
    List<BrandDO> getAll(String type);

    /**
     * @param type
     * @param code
     * @return
     */
    BrandDO getByCode(@Param("type") String type, @Param("code") String code);
}