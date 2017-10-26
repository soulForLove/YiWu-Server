package com.yiwu.changething.sec1.utils;

import com.yiwu.changething.common.cache.ICache;
import com.yiwu.changething.sec1.bean.WeChatInfo;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.WeChatMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Component
public class Cache {

    // redis以秒为单位
    public final int EXPIRED_DAY_30 = 24 * 60 * 60 * 30;
    public final int EXPIRED_DAY_7 = 24 * 60 * 60 * 7;
    public final int EXPIRED_DAY_1 = 24 * 60 * 60;
    public final int EXPIRED_HOUR_2 = 60 * 60 * 2;

    private final String TOKEN = "token:";
    private final String USER = "user:";
    private final String WECHAT_ACCESS_TOKEN = "wxAccessToken";
    private final String WECHAT_ACCESS_TOKEN_HASH = "wxAccessTokenHash";
    private final String ORDER_PREPAY = "order:prepay:";

    @Resource
    private ICache cacheService;

    @Resource
    private WeChatMapper weChatMapper;

    public boolean setToken(String userId, String token) {
        return cacheService.set(TOKEN + userId, token, EXPIRED_DAY_7);
    }

    public String getToken(String userId) {
        return cacheService.get(TOKEN + userId);
    }

    private boolean setUser(String userId, WeChatInfo user) {
        return cacheService.set(USER + userId, user, EXPIRED_DAY_7);
    }

    public boolean setUser(WeChatInfo weChatInfo) {
        return setUser(weChatInfo.getUserId(), weChatInfo);
    }

    public WeChatInfo getUser(String userId) {
        return getUser(userId, true);
    }

    public WeChatInfo getUser(String userId, boolean noExistThrowException) {
        WeChatInfo object = cacheService.get(USER + userId, WeChatInfo.class);
        if (object != null) {
            return object;
        }

        WeChatInfo user = weChatMapper.getById(userId);
        if (user != null) {
            setUser(userId, user);
            return user;
        } else if (noExistThrowException) {
            throw new YwException(ErrorBuilder.E101012);
        }
        return null;
    }

    public void hsetAccessToken(String shopId, String accessToken) {
        cacheService.hset(WECHAT_ACCESS_TOKEN_HASH, shopId, accessToken);
    }

    public String hgetAccessToken(String shopId) {
        return cacheService.hget(WECHAT_ACCESS_TOKEN_HASH, shopId, String.class);
    }

    public void putOrderPrepayId(String orderId, String prepayId) {
        cacheService.set(ORDER_PREPAY + orderId, prepayId, EXPIRED_HOUR_2);
    }

    public String getOrderPrepayId(String orderId) {
        return cacheService.get(ORDER_PREPAY + orderId);
    }

}
