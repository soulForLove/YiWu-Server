/*
 * Copyright (C) 2015-2016  GenGee Technology Co. Ltd. All rights reserved.
 *
 *                              http://www.GenGee.cn
 *
 * IMPORTANT: Your use of this Software is limited to those specific rights
 * granted under the terms of a software license agreement between the user
 * who downloaded the software, his/her employer (which must be your employer)
 * and GenGee Technology Co. Ltd (the "License").  You may not use this
 * Software unless you agree to abide by the terms of the License. The License
 * limits your use, and you acknowledge, that the Software may not be modified,
 * copied or distributed unless embedded on a GenGee Technology intelligent
 * device or system. Other than for the foregoing purpose, you may not use,
 * reproduce, copy, prepare derivative works of, modify, distribute, perform,
 * display or sell this Software and/or its documentation for any purpose.
 *
 * YOU FURTHER ACKNOWLEDGE AND AGREE THAT THE SOFTWARE AND DOCUMENTATION ARE
 * PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY, TITLE,
 * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE. IN NO EVENT SHALL
 * GENGEE TECHNOLOGY CO. LTD OR ITS LICENSORS BE LIABLE OR OBLIGATED UNDER
 * CONTRACT, NEGLIGENCE, STRICT LIABILITY, CONTRIBUTION, BREACH OF WARRANTY,
 * OR OTHER LEGAL EQUITABLE THEORY ANY DIRECT OR INDIRECT DAMAGES OR EXPENSES
 * INCLUDING BUT NOT LIMITED TO ANY INCIDENTAL, SPECIAL, INDIRECT, PUNITIVE
 * OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA, COST OF PROCUREMENT
 * OF SUBSTITUTE GOODS, TECHNOLOGY, SERVICES, OR ANY CLAIMS BY THIRD PARTIES
 *   (INCLUDING BUT NOT LIMITED TO ANY DEFENSE THEREOF), OR OTHER SIMILAR COSTS.
 *
 * Should you have any questions regarding your right to use this Software,
 * contact GenGee Technology Co. Ltd at www.GenGee.cn.
 */

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
