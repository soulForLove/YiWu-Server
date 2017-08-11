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

import com.yiwu.changething.sec1.service.IdleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class IdleRealm extends AuthorizingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(IdleRealm.class);

    @Autowired
    private IdleService idleService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();

        //若该方法什么都不做直接返回null的话,就会导致任何用户访问页面时都会自动跳转到unauthorizedUrl指定的地址
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
        //return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        IdleToken idleToken = (IdleToken) authcToken;

        if (idleService.getIdleById(idleToken.getCredentials().toString()) == null) {
            LOG.warn("Invalid Credentials: {}, because it is null ", idleToken.getCredentials().toString());
            throw new AuthenticationException("Invalid Credentials : " + idleToken.getCredentials().toString());
        }
        return new SimpleAuthenticationInfo(idleToken.getPrincipal(), idleToken.getCredentials(), this.getName());
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof IdleToken;
    }

    public void setIdleService(IdleService idleService) {
        this.idleService = idleService;
    }
}