package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.Principal;
import com.yiwu.changething.sec1.bean.RestToken;
import com.yiwu.changething.sec1.bean.User;
import com.yiwu.changething.sec1.bean.UserCache;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.UserMapper;
import com.yiwu.changething.sec1.utils.CommentUtil;
import com.yiwu.changething.sec1.utils.PasswordUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private CommentUtil commentUtil;

    @Autowired
    private UserCache userCache;

    /**
     * 通过密码登录
     *
     * @param name
     * @param password
     * @return
     */
    public Principal loginByPassword(String name, String password) {
        // check user
        User user = userMapper.getUser(name);
        if (null == user) {
            throw new YwException(101006); //用户不存在
        }
        // check password
        //PasswordUtil.generateSaltStr()获取salt的混淆值
        if (!user.getPassword().equals(PasswordUtil.encrypt(password, user.getSalt()))) {
            throw new YwException(101003);//密码错误
        }
        return loginAfterValidation(user);
    }

    /**
     * 验证(包括密码验证和手机验证码验证这两种方式）之后调用的登录逻辑
     *
     * @param user
     * @return
     */
    public Principal loginAfterValidation(User user) {
        Principal principal = new Principal(user.getId(), user.getPhone(), user.getName(), true);

        principal = userCache.cacheUser(principal); //缓存用户凭证

        SecurityUtils.getSubject().login(new RestToken(principal.getToken()));

        return principal;
    }

    public void cleanToken() {
        Principal principal = commentUtil.getCurrentPrincipal();
        userCache.removeUserToken(principal.getToken(), principal.getId().toString());
        userCache.cleanAuthorizationCache(principal.getId());
    }
}
