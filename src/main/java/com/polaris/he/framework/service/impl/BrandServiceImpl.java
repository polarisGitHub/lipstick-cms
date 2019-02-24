package com.polaris.he.framework.service.impl;

import com.polaris.he.framework.dao.BrandDao;
import com.polaris.he.framework.dao.object.BrandDO;
import com.polaris.he.framework.entity.Brand;
import com.polaris.he.framework.service.BrandService;
import com.polaris.he.framework.utils.BeanCopyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-10 21:17
 * Description:
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandDao brandDao;

    @Override
    public List<Brand> getBrands(String type) {
        List<BrandDO> brands = brandDao.getAll(type);
        if (CollectionUtils.isNotEmpty(brands)) {
            return brands.stream().map(l -> BeanCopyUtils.copyObject(l, new Brand())).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Brand getBrand(String type, String code) {
        BrandDO brand = brandDao.getByCode(type, code);
        return BeanCopyUtils.copyObject(brand, new Brand());
    }
}