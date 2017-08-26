package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.model.UserModel;
import com.yiwu.changething.sec1.utils.Principal;
import com.yiwu.changething.sec1.bean.UserBean;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.UserMapper;
import com.yiwu.changething.sec1.utils.PasswordUtil;
import com.yiwu.changething.sec1.utils.YwSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private YwSecurityUtil ywSecurityUtil;

    public void insertUser(UserBean userBean) {
        checkUserNameExist(userBean.getName());
        checkUserEmailExist(userBean.getEmail());
        UserBean user = new UserBean();
        user.setId(UUID.randomUUID().toString());
        user.setName(userBean.getName());
        user.setEmail(userBean.getEmail());
        user.setAvatar(userBean.getAvatar());
        user.setSalt(PasswordUtil.generateSaltStr());
        user.setPassword(PasswordUtil.encrypt(userBean.getPassword(), user.getSalt()));
        user.setPhone(userBean.getPhone());
        userMapper.insert(user);
    }

    /**
     * 判断用户名是否存在
     *
     * @param name
     */
    private void checkUserNameExist(String name) {
        UserBean userInfo = userMapper.getByName(name);
        if (userInfo != null) {
            throw new YwException(ErrorBuilder.E101008);
        }
    }

    /**
     * 判断用户邮箱是否存在
     *
     * @param email
     */
    private void checkUserEmailExist(String email) {
        UserBean userInfo = userMapper.getByEmail(email);
        if (userInfo != null) {
            throw new YwException(ErrorBuilder.E101009);
        }
    }

    /**
     * 修改密码
     *
     * @param password
     * @param request
     */
    public void updatePassword(String password, HttpServletRequest request) {
        Principal currentPrincipal = ywSecurityUtil.checkUserLogin(request);
        UserBean user = userMapper.getByName(currentPrincipal.getName());
        HttpSession session = request.getSession();
        user.setSalt(PasswordUtil.generateSaltStr());
        user.setPassword(PasswordUtil.encrypt(password, user.getSalt()));
        userMapper.updatePassword(user);
        Principal principal = new Principal(user.getId(), user.getPhone(), user.getName(), user.getAvatar(), user.getEmail());
        session.setAttribute(SystemVariableService.USER_INFO, principal);
    }

    /**
     * 根据用户id验证是否正确用户
     *
     * @param userId
     */
    public UserBean checkUserById(String userId) {
        UserBean userBean = userMapper.getById(userId);
        if (userBean == null) {
            throw new YwException(ErrorBuilder.E101002);
        }
        return userBean;
    }
}
