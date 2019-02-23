package com.polaris.he.framework.entity.page;

import lombok.Getter;
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
public class PageResult<T> {

    private List<T> list;

    private Integer pageNo;

    private Integer pageSize;

    private Integer totalPage;
}