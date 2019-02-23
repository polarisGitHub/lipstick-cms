package com.polaris.he.framework.dao;

import com.polaris.he.framework.dao.object.GoodsDO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 23:21
 * Description:
 */
public interface GoodsDao extends Dao {

    /**
     * @param inserts
     * @return
     */
    int insert(Collection<GoodsDO> inserts);

    /**
     * @param update
     * @return
     */
    int update(GoodsDO update);

    /**
     *
     * @param brandCode
     * @param type
     * @param goodsCode
     * @return
     */
    GoodsDO getByCode(@Param("brandCode") String brandCode, @Param("type") String type, @Param("goodsCode") String goodsCode);

    /**
     * @param type
     * @param goodsCodeSet
     * @return
     */
    List<GoodsDO> getByCodeList(@Param("type") String type, @Param("collection") Collection<String> goodsCodeSet);
}