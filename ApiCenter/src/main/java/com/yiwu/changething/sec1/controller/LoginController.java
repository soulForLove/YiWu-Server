package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.model.AuthModel;
import com.yiwu.changething.sec1.service.LoginService;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ModelMap login(@Valid @RequestBody AuthModel authModel) throws OAuthSystemException {
        ModelMap modelMap = new ModelMap();
        modelMap.put("access_token", null);
        modelMap.put("token_type", null);
        modelMap.put("expired_time", null);
        modelMap.put("userInfo", null);
        return modelMap;
    }

    @DeleteMapping("/logout")
    public ModelMap logout() {
//        AccessToken accessToken = (AccessToken) SecurityUtils.getSubject().getPrincipal();
//        authService.logout(accessToken);
        ModelMap modelMap = new ModelMap();
        modelMap.put("success", true);
        modelMap.put("description", "success logout.");
        return modelMap;
    }

}
