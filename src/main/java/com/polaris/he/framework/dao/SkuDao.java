package com.polaris.he.framework.dao;

import com.polaris.he.framework.dao.object.SkuDO;
import com.polaris.he.framework.dao.object.SkuQueryDO;

import java.util.Collection;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 22:37
 * Description:
 */
public interface SkuDao extends Dao {


    /**
     * @return
     */
    List<SkuDO> query(SkuQueryDO queryDO);

    /**
     * @param id
     * @return
     */
    SkuDO getById(Long id);

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


}