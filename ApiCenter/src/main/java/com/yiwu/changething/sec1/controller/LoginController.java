package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.Principal;
import com.yiwu.changething.sec1.bean.User;
import com.yiwu.changething.sec1.model.AuthModel;
import com.yiwu.changething.sec1.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

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
