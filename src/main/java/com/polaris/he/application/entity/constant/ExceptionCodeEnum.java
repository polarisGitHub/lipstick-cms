package com.polaris.he.application.entity.constant;

/**
 * User: hexie
 * Date: 2018-12-16 21:24
 * Description:
 */
public enum ExceptionCodeEnum {
    E00001("参数错误"),
    E00002("找不到数据"),
    E00003("数据重复"),
    E00004("无权限"),
    E00005("服务异常"),
    E00006("登录过期"),
    ;
    private String message;

    ExceptionCodeEnum(String message) {
        this.message = message;
    }
}