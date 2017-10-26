package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.LoginResp;
import com.yiwu.changething.sec1.bean.WxUserInfo;
import com.yiwu.changething.sec1.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/weChat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;


    @PostMapping("/login/{code}")
    public LoginResp login(@PathVariable("code") String code) {
        return weChatService.login(code);
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public void updateUserInfo(@RequestBody WxUserInfo wxUserInfo) {
        weChatService.updateWeChatInfo(wxUserInfo);
    }
}
