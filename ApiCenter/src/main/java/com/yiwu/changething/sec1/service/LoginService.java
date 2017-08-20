package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.utils.Principal;
import com.yiwu.changething.sec1.bean.UserBean;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.UserMapper;
import com.yiwu.changething.sec1.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过密码登录
     *
     * @param name
     * @param password
     * @return
     */
    public Principal loginByPassword(String name, String password, HttpServletRequest request) {
        // check user
        UserBean user = userMapper.getByName(name);
        if (null == user) {
            throw new YwException(ErrorBuilder.E101006); //用户不存在
        }
        // check password
        //PasswordUtil.generateSaltStr()获取salt的混淆值
        if (!user.getPassword().equals(PasswordUtil.encrypt(password, user.getSalt()))) {
            throw new YwException(ErrorBuilder.E101003);//密码错误
        }
        return loginAfterValidation(user, request);
    }

    /**
     * 验证(包括密码验证和手机验证码验证这两种方式）之后调用的登录逻辑
     *
     * @param user
     * @return
     */
    private Principal loginAfterValidation(UserBean user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Principal principal = new Principal(user.getId(), user.getPhone(), user.getName(), user.getAvatar(), user.getEmail());
        session.setAttribute(SystemVariableService.USER_INFO, principal);
        return principal;
    }

    /**
     * 登出
     *
     * @param request
     */
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SystemVariableService.USER_INFO);
    }
}
