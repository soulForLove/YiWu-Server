package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.IdleBean;
import com.yiwu.changething.sec1.bean.LoginResp;
import com.yiwu.changething.sec1.bean.OrderBean;
import com.yiwu.changething.sec1.bean.WxUserInfo;
import com.yiwu.changething.sec1.config.WxUnifiedOrder;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.service.IdleService;
import com.yiwu.changething.sec1.service.OrderService;
import com.yiwu.changething.sec1.service.WeChatService;
import com.yiwu.changething.sec1.utils.Cache;
import com.yiwu.changething.sec1.utils.NetUtils;
import com.yiwu.changething.sec1.utils.YwSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/weChat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private IdleService idleService;

    @Autowired
    private YwSecurityUtil ywSecurityUtil;

    @Autowired
    private Cache cache;


    @PostMapping("/login/{code}")
    public LoginResp login(@PathVariable("code") String code) {
        return weChatService.login(code);
    }

    @PostMapping("/updateUserInfo")
    public void updateUserInfo(@RequestBody WxUserInfo wxUserInfo) {
        weChatService.updateWeChatInfo(wxUserInfo);
    }


    /**
     * 微信支付统一下单
     *
     * @return 小程序支付请求参数，给js调用
     */
    @GetMapping("/unifiedOrder/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> unifiedOrder(HttpServletRequest request, @PathVariable("orderId") String orderId) {
        OrderBean order = orderService.getOrderById(orderId);
        if (order == null) {
            throw new YwException(ErrorBuilder.E101010);
        }
        IdleBean idle = idleService.getIdleById(order.getIdleId());

        String ipAddress = NetUtils.getIpAddr(request);
        String openId = ywSecurityUtil.getUser().getOpenId();
        String outTradeNo = String.valueOf(10301);
        String body = idle != null ? idle.getName() : "";
        int totalFee = Double.valueOf(order.getShareValue() * 100).intValue();// 元转分
        WxUnifiedOrder wx = new WxUnifiedOrder(order.getIdleId(), ipAddress, "o5gsb0XfaGDyQOi6J9rgq3lp529U", outTradeNo, totalFee, body);
        wx.prepay();
        cache.putOrderPrepayId(orderId, wx.getPrepay_id());// 缓存起来，发模板消息用
        return wx.getJsApiPayParam();
    }
}
