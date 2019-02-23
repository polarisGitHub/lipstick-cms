package com.polaris.he.framework.service;

import com.polaris.he.framework.entity.Sku;
import com.polaris.he.framework.entity.query.SkuListQueryEntity;

import java.util.Collection;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 21:12
 * Description:
 */
public interface SkuManagerService {


    /**
     * @return
     */
    List<Sku> query(SkuListQueryEntity query);

    /**
     * @param id
     * @return
     */
    Sku getById(Long id);

    /**
     * @param sku
     */
    void save(Sku sku);


    /**
     * @param collection
     */
    void importer(Collection<Sku> collection);
}