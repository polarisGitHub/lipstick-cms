package com.polaris.he.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.polaris.he.framework.dao.SkuDao;
import com.polaris.he.framework.dao.object.SkuDO;
import com.polaris.he.framework.dao.object.SkuQueryDO;
import com.polaris.he.lipstick.entity.SkuListItem;
import com.polaris.he.framework.entity.page.PageResult;
import com.polaris.he.framework.entity.query.SkuListQueryEntity;
import com.polaris.he.framework.repository.FrameworkBizConverterRepository;
import com.polaris.he.framework.service.SkuManagerService;
import com.polaris.he.framework.utils.BeanCopyUtils;
import com.polaris.he.framework.utils.PageHelperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-10 22:36
 * Description:
 */
@Slf4j
@Service
public class SkuManagerServiceImpl implements SkuManagerService {

    @Resource
    private SkuDao skuDao;

    @Resource
    private FrameworkBizConverterRepository frameworkBizConverterRepository;

    @Override
    public PageResult<?> query(SkuListQueryEntity query) {
        log.info("查询sku列表，query={}", query);
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<SkuDO> list = skuDao.query(BeanCopyUtils.copyObject(query, new SkuQueryDO()));
        Converter<SkuDO, ?> converter = frameworkBizConverterRepository.getConverter(query.getType(), SkuDO.class, "skuListItem");
        List<?> convertList = list.stream().map(converter::convert).collect(Collectors.toList());
        return PageHelperUtils.getPageInfo(list, new PageResult<>(convertList));
    }

    @Override
    public SkuListItem getById(Long id) {
        return null;
    }

    @Override
    public void save(SkuListItem sku) {

    }

    @Override
    public void importer(Collection<SkuListItem> collection) {

    }
}