package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.User;
import com.yiwu.changething.sec1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param user
     */
    @PostMapping
    public void insertUser(@RequestBody @Valid User user) {
        userService.insertUser(user);
    }

}
