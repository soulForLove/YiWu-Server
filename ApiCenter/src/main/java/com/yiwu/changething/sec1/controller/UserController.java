package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.UserBean;
import com.yiwu.changething.sec1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void insertUser(@RequestBody @Valid UserBean user) {
        userService.insertUser(user);
    }

    /**
     * 修改密码
     *
     * @param password
     * @param request
     */
    @PostMapping("/password/{password}")
    public void updatePassword(@PathVariable String password, HttpServletRequest request) {
        userService.updatePassword(password, request);
    }

}
