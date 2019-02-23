package com.polaris.he.application.entity;

import com.polaris.he.application.entity.constant.ExceptionCodeEnum;
import com.polaris.he.application.entity.constant.ResponseCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2018-12-16 21:03
 * Description:
 */
@Getter
@Setter
@ToString
public class RestResponse {

    private Object data;

    private String message;

    private ResponseCodeEnum code;

    private ExceptionCodeEnum errorCode;
}