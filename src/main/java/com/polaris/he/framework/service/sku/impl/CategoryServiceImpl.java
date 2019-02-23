package com.polaris.he.framework.service.sku.impl;

import com.polaris.he.framework.dao.BrandCategoryMappingDao;
import com.polaris.he.framework.dao.CategoryDao;
import com.polaris.he.framework.dao.object.BrandCategoryMappingDO;
import com.polaris.he.framework.dao.object.CategoryDO;
import com.polaris.he.framework.entity.sku.Category;
import com.polaris.he.framework.service.sku.CategoryService;
import com.polaris.he.framework.utils.BeanCopyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-10 21:19
 * Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private BrandCategoryMappingDao brandCategoryMappingDao;

    @Override
    public List<Category> getCategories(String type) {
        List<CategoryDO> categories = categoryDao.getCategoriesByType(type);
        List<Category> ret = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(categories)) {
            ret = categories.stream().map(l -> BeanCopyUtils.copyObject(l, new Category())).collect(Collectors.toList());
        }
        return ret;
    }

    @Override
    public List<Category> getCategoriesByBrand(String type, String code) {
        return null;
    }

    @Override
    public List<Category> getCategoriesByBrands(String type, List<String> brandCodes) {
        Assert.notEmpty(brandCodes, "参数不能为空");
        List<BrandCategoryMappingDO> mappings = brandCategoryMappingDao.getCategoryByBrands(type, brandCodes);
        if (CollectionUtils.isEmpty(mappings)) {
            return new LinkedList<>();
        }
        Map<String, Category> distinct = mappings.stream().
                map(BrandCategoryMappingDO::getCategory).
                map(l -> BeanCopyUtils.copyObject(l, new Category())).
                collect(Collectors.toMap(Category::getCode, Function.identity(), (u, v) -> v, LinkedHashMap::new));
        return new ArrayList<>(distinct.values());
    }


    @Override
    public List<Category> getCategoriesByGoods(String type, String goodsCode) {
        return null;
    }
}