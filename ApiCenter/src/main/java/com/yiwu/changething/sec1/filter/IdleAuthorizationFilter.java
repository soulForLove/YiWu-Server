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
package com.yiwu.changething.sec1.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class IdleAuthorizationFilter extends AuthenticatingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdleAuthorizationFilter.class);

    @Value("${resource.id}")
    private String resourceId;

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String equipId = httpRequest.getHeader("Authorization");
        return (new IdleToken(this.resourceId, equipId));
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //表示访问拒绝时是否自己处理，
        // true 表示自己不处理且继续拦截器链执行
        // false 表示自己已经处理了（比如重定向到另一个页面）
        return super.executeLogin(request, response);
    }

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
                                     ServletResponse response) {
        HashMap<String, String> errMap = new HashMap<>();
        errMap.put("description", e.getMessage());
        errMap.put("error", "invalid_equipId");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String errorJson = objectMapper.writeValueAsString(errMap);
            HttpServletResponse httpResponse = WebUtils.toHttp(response);
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Request-Method", "*");
            httpResponse.setHeader("Access-Control-Request-Headers", "*");
            httpResponse.setStatus(401);
            httpResponse.setContentType("application/json;charset=UTF-8");
            httpResponse.getWriter().write(errorJson);
            return false;
        } catch (IOException var9) {
            LOGGER.error("Build JSON message error", var9);
            throw new IllegalStateException(var9);
        }
    }
}