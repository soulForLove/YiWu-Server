package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.utils.Principal;
import com.yiwu.changething.sec1.model.AuthModel;
import com.yiwu.changething.sec1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * @param authModel
     * @return
     */
    @PostMapping("/login")
    public Principal login(@RequestBody @Valid AuthModel authModel, HttpServletRequest request) {
        return loginService.loginByPassword(authModel.getName(), authModel.getPassword(), request);
    }

    @DeleteMapping("/logout")
    public void logout(HttpServletRequest request) {
        loginService.logout(request);
    }

}
