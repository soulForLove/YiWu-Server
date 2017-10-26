package com.yiwu.changething.sec1.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiwu.changething.sec1.bean.LoginResp;
import com.yiwu.changething.sec1.bean.WeChatInfo;
import com.yiwu.changething.sec1.bean.WxUserInfo;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.WeChatMapper;
import com.yiwu.changething.sec1.utils.Cache;
import com.yiwu.changething.sec1.utils.CommonUtils;
import com.yiwu.changething.sec1.utils.HttpUtils;
import com.yiwu.changething.sec1.utils.YwSecurityUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class WeChatService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WeChatMapper weChatMapper;

    @Autowired
    private Cache cache;

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.secret}")
    private String secret;

    @Autowired
    private YwSecurityUtil ywSecurityUtil;

    /**
     * 根据wx.login返回的code获取openId，进而设置用户
     *
     * @param code
     * @return
     */
    public LoginResp login(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("grant_type", "authorization_code");
        params.put("js_code", code);
        String respJson = HttpUtils.get("https://api.weixin.qq.com/sns/jscode2session", params);
        // String respJson =
        // "{\"session_key\":\"jdOMDoqV4pJCai1I5G29Qw==\",\"expires_in\":7200,\"openid\":\"oolkM0TGbrTLGqYxjzQlc0tv_pMc\"}";
        JSONObject obj = JSON.parseObject(respJson);
        String openId = obj.getString("openid");
        // session_key 是对用户数据进行加密签名的密钥,不应该在网络上传输
        String session_key = obj.getString("session_key");
        logger.debug(">>>session_key:{}", session_key);
        if (openId == null) {
            throw new YwException(ErrorBuilder.E101013);
        }
        WeChatInfo weChatInfo = checkByOpenId(openId);
        String token = new String(RandomStringUtils.randomAlphanumeric(32));
        cache.setToken(weChatInfo.getUserId(), token);
        cache.setUser(weChatInfo);
        return new LoginResp(weChatInfo.getUserId(), token);
    }

    /**
     * 用户是否存在
     *
     * @param openId
     * @return
     */
    private WeChatInfo checkByOpenId(String openId) {
        WeChatInfo weChatInfo = weChatMapper.getByOpenId(openId);
        if (weChatInfo == null) {
            WeChatInfo wxInfo = new WeChatInfo();
            wxInfo.setOpenId(openId);
            weChatMapper.insert(wxInfo);
            return weChatMapper.getByOpenId(openId);
        }
        return weChatInfo;
    }

    /**
     * 更新用户信息
     *
     * @param wxUserInfo
     */
    public void updateWeChatInfo(WxUserInfo wxUserInfo) {
        WeChatInfo user = ywSecurityUtil.getUser();
        WeChatInfo weChatInfo = new WeChatInfo();
        weChatInfo.setUserId(user.getUserId());
        WxUserInfo.UserInfo userInfo = wxUserInfo.getUserInfo();
        weChatInfo.setGender(userInfo.getGender());
        weChatInfo.setNickName(CommonUtils.filterEmoji(userInfo.getNickName()));
        weChatInfo.setAvatarUrl(userInfo.getAvatarUrl());
        weChatMapper.update(weChatInfo);
    }
}
