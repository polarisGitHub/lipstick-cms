package com.polaris.he.application.mvc.advice;

import com.polaris.he.application.entity.RestResponse;
import com.polaris.he.application.entity.constant.ResponseCodeEnum;
import com.polaris.he.application.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * User: hexie
 * Date: 2018-12-17 23:21
 * Description:
 */
@Slf4j
@ControllerAdvice
public class RestApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        RestResponse restResponse;
        if (body instanceof RestResponse) {
            restResponse = (RestResponse) body;
        } else {
            restResponse = new RestResponse();
            restResponse.setCode(ResponseCodeEnum.success);
            restResponse.setData(body);
        }
        log.info("rest api返回消息，{}", JsonUtils.toJsonString(restResponse));
        return restResponse;
    }
}