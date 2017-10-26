package com.yiwu.changething.sec1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 *
 * @描述:微信支付配置 微信支付 https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
 */
@Component
public class WxConfig {

    public static final String ENCODE_UTF8 = "UTF-8";
    // 统一下单
    public static final String URL_UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 订单查询
    public static final String URL_ORDER_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    // 申请退款
    public static final String URL_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    // 退款状态查询
    public static final String URL_REFUND_QUERY = "https://api.mch.weixin.qq.com/pay/refundquery";
    // 交易类型
    public static final String TRADE_TYPE_JSAPI = "JSAPI";
    public static final String TRADE_TYPE_APP = "APP";

    // 设备号
    public static final String DEVICE_INFO_WEB = "WEB";

    // 支付回调地址
    @Value("${wx.pay.notify.url}")
    private static String wxPayNotifyurl;

    public static final String URL_QRCODE_C = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode";

}
