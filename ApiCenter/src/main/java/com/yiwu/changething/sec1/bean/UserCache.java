package com.yiwu.changething.sec1.bean;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisMap;
import org.springframework.data.redis.support.collections.DefaultRedisSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Maxwell <huangxiongyi@gengee.cn>
 */
@Component
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class UserCache {

    @Autowired
    private RedisTemplate<String, Principal> redisTemplate;

    @Autowired
    private TokenExpireComponent tokenExpireComponent;

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;


    /**
     * 缓存用户信息
     *
     * @param principal
     * @return
     */
    public Principal cacheUser(Principal principal) {
        String changeKey = this.generateRandomKey();
        //用户token
        String accessToken = this.generateRandomKey(principal.getId());

        String cacheKey = generateCacheKey(principal.getId().toString());
        principal.setToken(accessToken);
        principal.setTokenKey(changeKey);

        DefaultRedisMap<String, Principal> cache = getCache(cacheKey);
        this.validateUsers(cacheKey);
        cache.putIfAbsent(changeKey, principal);
        return principal;
    }

    /**
     * 更新缓存
     *
     * @param principal
     */
    public void updatePrincipal(Principal principal) {
        String cacheKey = generateCacheKey(principal.getId().toString());
        DefaultRedisMap<String, Principal> cache = getCache(cacheKey);
        cache.put(principal.getTokenKey(), principal);
    }

    private DefaultRedisMap<String, Principal> getCache(String cacheKey) {
        return new DefaultRedisMap<>(cacheKey, redisTemplate);
    }

    private List<Principal> getTokenCache(String cacheKey) {
        return (List<Principal>) this.getCache(cacheKey).values();
    }

    /**
     * 踢出用户
     *
     * @param userId
     */
    public void kickUser(Long userId) {
        validateUsers(this.generateCacheKey(userId.toString()));
    }


    /**
     * 校验用户
     *
     * @param cacheKey
     */
    private void validateUsers(String cacheKey) {
        DefaultRedisMap<String, Principal> cache = this.getCache(cacheKey);
        for (Principal principal : cache.values()) {
            principal.setValid(false);
            cache.put(principal.getTokenKey(), principal);
        }
    }

    String generateRandomKey() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 获取用户缓存
     *
     * @param token
     * @return
     * @throws DecoderException
     */
    public Principal getCacheUser(String token) throws DecoderException {
        Long userId = this.getUserIdFromToken(token);
        if (userId != null) {
            List<Principal> redisSet = this.getTokenCache(generateCacheKey(userId.toString()));
            for (Principal principal : redisSet) {
                if (principal.getToken().equals(token)) {
                    return principal;
                }
            }
        }
        return null;
    }

    /**
     * 根据用户Id生成Token
     *
     * @param userId
     * @return
     */
    public String generateRandomKey(Long userId) {
        String tokenSub = RandomStringUtils.randomAlphabetic(32 - userId.toString().length() - 1);
        return Hex.encodeHexString((userId + ":" + tokenSub).getBytes()).toUpperCase();
    }

    /**
     * 根据用户Token获取用户Id
     *
     * @param token
     * @return
     * @throws DecoderException
     */
    public Long getUserIdFromToken(String token) throws DecoderException {
        byte[] result = Hex.decodeHex(token.toCharArray());
        return Long.valueOf(new String(result).split(":")[0]);
    }


    /**
     * 生成缓存key
     *
     * @param id
     * @return
     */
    private String generateCacheKey(String id) {
        return tokenExpireComponent.getKeyPrefix().concat(id);
    }

    /**
     * 删除用户token
     *
     * @param token
     * @param userId
     */
    public void removeUserToken(String token, String userId) {
        DefaultRedisMap<String, Principal> cache = this.getCache(generateCacheKey(userId));
        for (Principal principal : cache.values()) {
            if (principal.getToken().equals(token)) {
                cache.remove(principal.getTokenKey());
            }
        }
    }

    /**
     * 清除用户所有的缓存信息
     *
     * @param userId
     */
    public void clearUserToken(Long userId) {
        redisTemplate.delete(generateCacheKey(userId.toString()));
    }

    /**
     * 根据用户id更新用户缓存用户名称
     *
     * @param userId
     * @param userName
     */
    public void updatePrincipalName(Long userId, String userName) {
        if (userId != null) {
            String cacheKey = generateCacheKey(userId.toString());
            DefaultRedisMap<String, Principal> cache = this.getCache(cacheKey);
            for (Principal principal : cache.values()) {
                principal.setName(userName);
                cache.put(principal.getTokenKey(), principal);
            }
        }
    }

    /**
     * 缓存授权信息
     *
     * @param cacheKey
     * @param list
     */
    public void cacheAuthorization(String cacheKey, List<String> list) {
        if (StringUtils.isNotBlank(cacheKey)) {
            DefaultRedisSet<String> roleCache = new DefaultRedisSet<>(cacheKey, stringRedisTemplate);
            roleCache.addAll(list);
        }
    }

    public String generateRoleCacheKey(Long userId) {
        return tokenExpireComponent.getRoleKeyPrefix().concat(userId.toString());
    }

    public String generatePermissionsCacheKey(Long userId) {
        return tokenExpireComponent.getPermissionsKeyPrefix().concat(userId.toString());
    }

    /**
     * 清空用户授权
     *
     * @param userId
     */
    public void cleanAuthorizationCache(Long userId) {
        stringRedisTemplate.delete(generateRoleCacheKey(userId));
        stringRedisTemplate.delete(generatePermissionsCacheKey(userId));
    }

    /**
     * 从缓存中获取用户授权信息
     *
     * @param cacheKey
     * @return
     */
    public List<String> getCacheAuthorization(String cacheKey) {
        if (stringRedisTemplate.hasKey(cacheKey)) {
            DefaultRedisSet<String> redisSet = new DefaultRedisSet<>(cacheKey, stringRedisTemplate);
            return new ArrayList<>(redisSet);
        }
        return null;
    }

}
