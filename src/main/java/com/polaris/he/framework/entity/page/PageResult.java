package com.polaris.he.framework.entity.page;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-02-02 21:42
 * Description:
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PageResult<T> {

    final private List<T> list;

    private Integer pageNo;

    private Integer pageSize;

    private Integer totalPage;

    private Integer totalCount;
}