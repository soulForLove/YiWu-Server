package com.yiwu.changething.common.cache;

import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface ICache {

    public String get(String key);

    public <T> T get(String key, Class<T> clazz);

    public <T> List<T> getList(String key, Class<T> clazz);

    /**
     * @param key
     * @param expiredTime (单位:秒)
     * @param value
     * @return
     */
    public boolean set(String key, Object value, int expiredTime);

    public boolean delete(String key);

    public long incr(String key, int by, long defaultValue);

    public long decr(String key, int by, long defaultValue);

    /**
     * hash操作
     */
    public Long hset(String key, String field, Object value);

    public <T> T hget(String key, String field, Class<T> clazz);

    public Long hdel(String key, String field);
}