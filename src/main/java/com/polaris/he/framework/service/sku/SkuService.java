package com.polaris.he.framework.service.sku;

import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.sku.Sku;
import com.polaris.he.framework.entity.sku.SkuAggregation;

import java.util.Collection;

/**
 * User: hexie
 * Date: 2019-01-10 21:12
 * Description:
 */
public interface SkuService {

    /**
     * @param collection
     * @return
     */
    int save(Collection<Sku> collection);

    /**
     * @param sku
     * @return
     */
    Sku getBySkuInfo(BaseSkuInfo sku);

    /**
     * @param sku
     * @return
     */
    SkuAggregation getAggregationBySkuInfo(BaseSkuInfo sku);
}