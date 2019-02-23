package com.polaris.he.framework.service.impl;

import com.polaris.he.framework.entity.Sku;
import com.polaris.he.framework.entity.query.SkuListQueryEntity;
import com.polaris.he.framework.service.SkuManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 22:36
 * Description:
 */
@Slf4j
@Service
public class SkuManagerServiceImpl implements SkuManagerService {

    @Override
    public List<Sku> query(SkuListQueryEntity query) {
        log.info("查询sku列表，query={}", query);
        return null;
    }

    @Override
    public Sku getById(Long id) {
        return null;
    }

    @Override
    public void save(Sku sku) {

    }

    @Override
    public void importer(Collection<Sku> collection) {

    }
}