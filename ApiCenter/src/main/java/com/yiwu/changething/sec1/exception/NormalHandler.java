package com.yiwu.changething.sec1.exception;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@ControllerAdvice
public class NormalHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof RespModel || !selectedContentType.includes(MediaType.APPLICATION_JSON)
                || returnType.getMethodAnnotation(IgnoreNormal.class) != null) {
            return body;
        }
        RespModel respModel = new RespModel();
        respModel.setDetails(body);
        return respModel;
    }
}
