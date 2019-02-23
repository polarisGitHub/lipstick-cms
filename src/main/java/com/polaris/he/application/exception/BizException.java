package com.polaris.he.application.exception;

import com.polaris.he.application.entity.constant.ExceptionCodeEnum;
import lombok.Getter;

/**
 * User: hexie
 * Date: 2018-12-16 21:23
 * Description:
 */
@Getter
public class BizException extends RuntimeException {

    private ExceptionCodeEnum exceptionCode;

    public BizException(String message, ExceptionCodeEnum exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public BizException(String message, ExceptionCodeEnum exceptionCode, Throwable cause) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }
}