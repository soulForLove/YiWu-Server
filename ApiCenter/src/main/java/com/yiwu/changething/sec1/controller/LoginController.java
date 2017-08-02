package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.Principal;
import com.yiwu.changething.sec1.model.AuthModel;
import com.yiwu.changething.sec1.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, Object> login(@RequestBody @Valid AuthModel authModel) {
        Principal principal = loginService.loginByPassword(authModel.getUsername(), authModel.getPassword());
        ModelMap modelMap = new ModelMap();
        modelMap.put("changeKey", principal.getTokenKey());
        modelMap.put("accessToken", principal.getToken());
        modelMap.put("isInit", StringUtils.isNotBlank(principal.getPhone()));
        return modelMap;
    }

    @DeleteMapping("/logout")
    public void logout() {
        loginService.cleanToken();
    }

}
