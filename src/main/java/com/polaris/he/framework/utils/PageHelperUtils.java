package com.polaris.he.framework.utils;

import com.github.pagehelper.Page;
import com.polaris.he.framework.entity.page.PageResult;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-02-23 22:47
 * Description:
 */
public class PageHelperUtils {

    public static <T> PageResult<T> getPageInfo(List<?> pageHelperOriginList, PageResult<T> pageResult) {
        int pageNo = 0;
        int pageSize = pageHelperOriginList.size();
        int totalPate = 1;
        if (pageHelperOriginList instanceof Page) {
            Page page = (Page) pageHelperOriginList;
            pageNo = page.getPageNum();
            pageSize = page.getPageSize();
            totalPate = page.getPages();
        }
        pageResult.setPageNo(pageNo);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalPage(totalPate);
        return pageResult;
    }
}