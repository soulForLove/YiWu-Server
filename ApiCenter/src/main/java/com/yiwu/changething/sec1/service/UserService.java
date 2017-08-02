package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.User;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.UserMapper;
import com.yiwu.changething.sec1.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insertUser(User userModel) {
        checkUserExist(userModel.getName());
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userModel.getName());
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
    private void checkUserExist(String name) {
        User userInfo = userMapper.getUser(name);
        if (userInfo != null) {
            throw new YwException(101008);
        }
    }
}
