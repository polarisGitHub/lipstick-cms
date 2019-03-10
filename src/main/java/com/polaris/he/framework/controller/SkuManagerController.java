package com.polaris.he.framework.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.framework.entity.page.PageResult;
import com.polaris.he.framework.entity.query.SkuListQueryEntity;
import com.polaris.he.framework.service.SkuManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User: hexie
 * Date: 2019-02-23 22:18
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/{type}/data-manager/sku")
public class SkuManagerController {

    @Resource
    private SkuManagerService skuManagerService;

    @GetMapping("/list")
    public PageResult<?> list(@PathVariable CosmeticsEnum type, SkuListQueryEntity query) {
        log.info("查询sku列表，type={},query={}", type, query);
        query.setType(type.getCode());
        return skuManagerService.query(query);
    }

    @GetMapping("/detail/{id}")
    public Object detail(@PathVariable CosmeticsEnum type, @PathVariable Long id) {
        log.info("查询sku详情，id={},type={}", id, type);
        return skuManagerService.getById(type.getCode(), id);
    }

    @PutMapping("/save")
    public String save(@PathVariable CosmeticsEnum type, @RequestBody JsonNode body) {
        log.info("保存sku详情，type={}，data={}", type, body);
        skuManagerService.save(type.getCode(), body);
        return "ok";
    }

    @PutMapping("/importer")
    public String importer(@PathVariable CosmeticsEnum type) {
        return "ok";
    }
}