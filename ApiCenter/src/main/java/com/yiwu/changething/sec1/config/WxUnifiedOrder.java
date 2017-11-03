package com.yiwu.changething.sec1.config;

import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.utils.HttpUtils;
import com.yiwu.changething.sec1.utils.WxUtils;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 *
 * @参考: https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_1
 * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_1
 */
@Data
public class WxUnifiedOrder {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private String appid;
    private String mch_id;
    private String device_info;// 设备号，终端设备号(门店号或收银设备ID)，PC网页或公众号内支付请传"WEB"，可为空
    private String nonce_str;// 随机字符串，不长于32位
    private String sign;// 签名
    private String body;// 商品描述，商品简要描述
    private String out_trade_no;// 商户系统内部的订单号
    private int total_fee;// 订单总金额(单位:分)
    private String spbill_create_ip;// 终端IP，APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
    private String time_start;// 交易起始时间，格式为yyyyMMddHHmmss，可为空
    private String time_expire;// 交易结束时间，格式为yyyyMMddHHmmss，可为空
    // private String goods_tag;// 商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠，可为空
    private String notify_url;// 通知地址，接收微信支付异步通知回调地址
    private String trade_type;// 交易类型，取值：JSAPI，NATIVE，APP，WAP.小程序取值如下：JSAPI
    private String product_id;// 商品ID，trade_type=NATIVE时不可为空
    private String limit_pay;// 指定支付方式，no_credit指定不能使用信用卡支付，可为空
    private String openid;// 用户标识，trade_type=JSAPI时不可为空

    private String prepay_id;// 调用预支付返回的预支付交易会话标识

    private String wxAppPartnerKey;// TODO 单个支付账号时用不到，这边是兼容多个微信支付

    public WxUnifiedOrder(String shopId, String ipAddress, String openid, String outTradeNo, int totalFee, String body) {
        // ShopConf shopConf = ShopConfServiceImpl.get(shopId);
        this.appid = "wx27235947019a8e9a";
        this.mch_id = "1481964212";
        this.wxAppPartnerKey = "35048119930104651435048119930104";
        // this.appid = WxConfig.wxAppId;
        // this.mch_id = WxConfig.wxAppPartner;
        // this.notify_url = WxConfig.wxPayNotifyurl + "/" + shopId;
        this.notify_url = WxConfig.wxPayNotifyurl;
        this.trade_type = WxConfig.TRADE_TYPE_JSAPI;
        this.device_info = WxConfig.DEVICE_INFO_WEB;
        this.nonce_str = RandomStringUtils.randomAlphabetic(32);
        this.spbill_create_ip = ipAddress;
        this.openid = openid;
        this.out_trade_no = outTradeNo;
        this.total_fee = totalFee;
        this.body = body;
    }

    private String getPrepayXml() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("appid", this.appid);
        treeMap.put("mch_id", this.mch_id);
        treeMap.put("device_info", this.device_info);
        treeMap.put("nonce_str", this.nonce_str);
        treeMap.put("body", this.body);
        treeMap.put("out_trade_no", this.out_trade_no);
        treeMap.put("total_fee", String.valueOf(this.total_fee));
//        treeMap.put("spbill_create_ip", this.spbill_create_ip);
        treeMap.put("trade_type", this.trade_type);
        treeMap.put("notify_url", this.notify_url);
        treeMap.put("openid", this.openid);
        return WxUtils.wrapXmlWithSign(treeMap, this.wxAppPartnerKey);
    }

    /**
     * 发起预支付
     */
    public void prepay() {
        try {
            String respXml = HttpUtils.post(WxConfig.URL_UNIFIEDORDER, null, getPrepayXml(), false);
            Map<String, String> resp = WxUtils.parseWxResp(respXml);
            if ("SUCCESS".equals(resp.get("return_code")) && "SUCCESS".equals(resp.get("result_code"))) {
                logger.info(">>>预支付成功,out_trade_no:" + this.out_trade_no);
                this.prepay_id = resp.get("prepay_id");
            } else {
                logger.info(">>>预支付失败,out_trade_no:" + this.out_trade_no);
                String errInfo = StringUtils.isNoneBlank(resp.get("err_code_des")) ? "[" + resp.get("err_code_des") + "]" : null;
                throw new YwException(ErrorBuilder.E101014);
            }
        } catch (Exception e) {
            logger.error("预支付失败", e);
            throw new YwException(ErrorBuilder.E101014);
        }
    }

    /**
     * 获取网页端调起支付API相关参数
     * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-pay.html
     * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=3
     */
    public SortedMap<String, String> getJsApiPayParam() {
        SortedMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("appId", this.appid);
        treeMap.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        treeMap.put("nonceStr", DigestUtils.md5Hex(String.valueOf(new Random().nextInt(10000))));
        treeMap.put("package", "prepay_id=" + prepay_id);
        treeMap.put("signType", "MD5");
        treeMap.put("paySign", WxUtils.getSign(treeMap, this.wxAppPartnerKey));
        return treeMap;
    }

}
