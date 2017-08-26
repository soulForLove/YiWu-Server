package com.yiwu.changething.sec1.filter;

import com.yiwu.changething.sec1.service.IdleService;
import com.yiwu.changething.sec1.service.UserService;
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
public class UserRealm extends AuthorizingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

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

        UserToken userToken = (UserToken) authcToken;

        if (userService.checkUserById(userToken.getCredentials().toString()) == null) {
            LOG.warn("Invalid Credentials: {}, because it is null ", userToken.getCredentials().toString());
            throw new AuthenticationException("Invalid Credentials : " + userToken.getCredentials().toString());
        }
        return new SimpleAuthenticationInfo(userToken.getPrincipal(), userToken.getCredentials(), this.getName());
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UserToken;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}