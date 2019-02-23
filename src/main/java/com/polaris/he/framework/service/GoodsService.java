package com.polaris.he.framework.service;

import com.polaris.he.framework.entity.Goods;
import com.polaris.he.framework.entity.GoodsCategoryMapping;

import java.util.Collection;

/**
 * User: hexie
 * Date: 2019-01-10 21:12
 * Description:
 */
public interface GoodsService {

    /**
     * @param collection
     * @return
     */
    int save(Collection<Goods> collection);

    /**
     * @param collection
     * @return
     */
    int saveGoodsCategoriesMapping(Collection<GoodsCategoryMapping> collection);

    /**
     * @param brandCode
     * @param type
     * @param goodsCode
     * @return
     */
    Goods getByCode(String brandCode, String type, String goodsCode);
}