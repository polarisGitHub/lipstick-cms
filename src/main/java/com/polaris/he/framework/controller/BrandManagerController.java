package com.polaris.he.framework.controller;

import com.polaris.he.framework.entity.Brand;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.framework.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-02-24 23:18
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/{type}/data-manager/brand")
public class BrandManagerController {

    @Resource
    private BrandService brandService;

    @GetMapping("/all")
    public List<Brand> list(@PathVariable CosmeticsEnum type) {
        log.info("查询brand，type={},query={}", type);
        return brandService.getBrands(type.getCode());
    }
}