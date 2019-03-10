package com.polaris.he.framework.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.polaris.he.lipstick.entity.SkuDetailItem;
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
     * @param type
     * @param id
     * @return
     */
    Object getById(String type, Long id);

    /**
     * @param type
     * @param sku
     */
    void save(String type, JsonNode sku);

    /**
     * @param type
     * @param collection
     */
    void importer(String type, Collection<SkuListItem> collection);
}