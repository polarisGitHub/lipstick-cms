package com.polaris.he.application.mvc.advice;

import com.polaris.he.application.entity.RestResponse;
import com.polaris.he.application.entity.constant.ExceptionCodeEnum;
import com.polaris.he.application.entity.constant.ResponseCodeEnum;
import com.polaris.he.application.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * User: hexie
 * Date: 2018-12-16 21:12
 * Description:
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class, basePackages = "com.polaris.he.lipstick.controller")
public class ExceptionHandlerAdvice {

    /**
     * 业务异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public RestResponse bizExceptionHandler(BizException e) {
        log.error("[业务异常{}],message={}", e.getMessage(), e.getExceptionCode(), e);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodeEnum.error);
        response.setErrorCode(e.getExceptionCode());
        response.setMessage(e.getMessage());
        return response;
    }

    /**
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public RestResponse validateExceptionHandler(IllegalArgumentException e) {
        log.error("[参数异常{}],message={}", e.getMessage(), e);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodeEnum.error);
        response.setErrorCode(ExceptionCodeEnum.E00001);
        response.setMessage(e.getMessage());
        return response;
    }

    /**
     * 通用异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse defaultExceptionHandler(Exception e) {
        log.error("[系统异常],message={}", e.getMessage(), e);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodeEnum.error);
        response.setMessage("出错了");
        return response;
    }


}