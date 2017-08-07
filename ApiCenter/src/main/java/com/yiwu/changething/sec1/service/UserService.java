package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.Mail;
import com.yiwu.changething.sec1.bean.Principal;
import com.yiwu.changething.sec1.bean.User;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.UserMapper;
import com.yiwu.changething.sec1.utils.CommentUtil;
import com.yiwu.changething.sec1.utils.PasswordUtil;
import com.yiwu.changething.sec1.utils.mail.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private CommentUtil commentUtil;

    public void insertUser(User userModel) {
        checkUserNameExist(userModel.getName());
        checkUserEmailExist(userModel.getEmail());
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setAvatar(userModel.getAvatar());
        user.setSalt(PasswordUtil.generateSaltStr());
        user.setPassword(PasswordUtil.encrypt(userModel.getPassword(), user.getSalt()));
        user.setPhone(userModel.getPhone());
        userMapper.insert(user);
    }

    /**
     * 判断用户名是否存在
     *
     * @param name
     */
    private void checkUserNameExist(String name) {
        User userInfo = userMapper.getByName(name);
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
        User userInfo = userMapper.getByEmail(email);
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
        Principal currentPrincipal = commentUtil.getCurrentPrincipal(request);
        User user = userMapper.getByName(currentPrincipal.getName());
        HttpSession session = request.getSession();
        user.setSalt(PasswordUtil.generateSaltStr());
        user.setPassword(PasswordUtil.encrypt(password, user.getSalt()));
        userMapper.updatePassword(user);
        Principal principal = new Principal(user.getId(), user.getPhone(), user.getName(), user.getAvatar(), user.getEmail());
        session.setAttribute(SystemVariableService.USER_INFO, principal);
    }
}
