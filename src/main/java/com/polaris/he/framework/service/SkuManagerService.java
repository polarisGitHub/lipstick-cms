package com.polaris.he.framework.service;

import com.polaris.he.lipstick.entity.SkuListItem;
import com.polaris.he.framework.entity.page.PageResult;
import com.polaris.he.framework.entity.query.SkuListQueryEntity;

import java.util.Collection;

/**
 * User: hexie
 * Date: 2019-01-10 21:12
 * Description:
 */
public interface SkuManagerService {


    /**
     * @return
     */
    PageResult<?> query(SkuListQueryEntity query);

    /**
     * @param id
     * @return
     */
    Object getById(Long id);

    /**
     * @param sku
     */
    void save(SkuListItem sku);


    /**
     * @param collection
     */
    void importer(Collection<SkuListItem> collection);
}