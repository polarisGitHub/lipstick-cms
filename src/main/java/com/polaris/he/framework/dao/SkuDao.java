package com.polaris.he.framework.dao;

import com.polaris.he.framework.dao.object.SkuDO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User: hexie
 * Date: 2019-01-10 22:37
 * Description:
 */
public interface SkuDao extends Dao {


    /**
     * @param l
     * @return
     */
    int update(SkuDO l);

    /**
     * @param insert
     * @return
     */
    int insert(Collection<SkuDO> insert);

    /**
     * @param type
     * @param skuCodes
     * @return
     */
    List<SkuDO> getByCodeList(@Param("type") String type, @Param("collection") Set<String> skuCodes);

    /**
     * @param type
     * @param brandCode
     * @return
     */
    SkuDO getSku(@Param("brandCode") String brandCode, @Param("type") String type, @Param("skuCode") String skuCode);

}