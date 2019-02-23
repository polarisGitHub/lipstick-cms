package com.polaris.he.framework.dao.object;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * User: hexie
 * Date: 2018-12-22 22:19
 * Description:
 */
@Getter
@Setter
@ToString
public class BaseDO {

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}