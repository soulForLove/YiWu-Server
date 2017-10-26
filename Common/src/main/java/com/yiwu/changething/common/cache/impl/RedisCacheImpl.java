package com.yiwu.changething.common.cache.impl;

import com.alibaba.fastjson.JSON;
import com.yiwu.changething.common.cache.ICache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;
import java.util.List;


public class RedisCacheImpl implements ICache {

    private Logger logger = LoggerFactory.getLogger(RedisCacheImpl.class);
    private JedisPool jedisPool;

    public RedisCacheImpl(JedisPool jedisPool) throws IOException {
        this.jedisPool = jedisPool;
    }

    /**
     * 从池中获取一个实例，获取后必须归还
     */
    private Jedis getJedisInstance() {
        return jedisPool.getResource();
    }

    private void returnJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    private void handleException(Exception e, String key) {
        logger.warn("Redis client Exception，key:{}, Exception", key, e);
    }

    public String get(String key) {
        return get(key, String.class);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            String value = jedis.get(key);
            if (StringUtils.isNotBlank(value)) {
                return (T) JSON.parseObject(value, clazz);
            }
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return null;
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            String value = jedis.get(key);
            if (StringUtils.isNotBlank(value)) {
                return JSON.parseArray(value, clazz);
            }
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return null;
    }

    @Override
    public boolean set(String key, Object value, int expiredTime) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            jedis.setex(key, expiredTime, JSON.toJSONString(value));
            return true;
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return false;
    }

    @Override
    public boolean delete(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            long ret = jedis.del(key.getBytes());
            return ret > 0;
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return false;
    }

    @Override
    public long incr(String key, int by, long defaultValue) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            return jedis.incrBy(key, by);
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public long decr(String key, int by, long defaultValue) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            return jedis.decrBy(key, by);
        } finally {
            returnJedis(jedis);
        }
    }

    /************** list操作 ********************************/
    /**
     * 向list中添加一个元素
     *
     * @param key
     * @param value
     * @throws
     * @description:
     */
    public void lpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            jedis.lpush(key, value);
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 返回并删除名称为key的list中的尾元素
     *
     * @param key
     * @return
     * @throws
     * @description:
     */
    public String rpop(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            return jedis.rpop(key);
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return null;
    }

    /**
     * 返回并删除名称为key的list中的头部元素
     *
     * @param key
     * @return
     * @throws
     * @description:
     */
    public String lpop(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            return jedis.lpop(key);
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return null;
    }

    /**
     * 取得长度
     *
     * @param key
     * @return
     * @throws
     * @description:
     */
    public long llen(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            return jedis.llen(key);
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return -1;
    }

    /**
     * 指定范围内的值
     *
     * @param key
     * @return
     * @throws
     * @description:
     */
    public List<String> getAllDataFormList(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            long length = jedis.llen(key);
            List<String> list = jedis.lrange(key, 0, length);
            return list;
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return null;
    }

    /************* end list操作 ********************************/

    /****************** 订阅消息 **************************************/
    /**
     * 定义推送
     *
     * @param pubsub
     * @param patterns
     * @throws
     * @description:
     */
    public void psubscribe(JedisPubSub pubsub, String[] patterns) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            jedis.psubscribe(pubsub, patterns);
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 推送
     *
     * @param key
     * @param value
     * @throws
     * @description:
     */
    public void publish(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            jedis.publish(key, value);
        } finally {
            returnJedis(jedis);
        }
    }

    /****************** end 订阅消息 **************************************/

    /**
     * 获取key对应的value值
     *
     * @param key
     * @return
     * @throws
     * @description:
     */
    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            return jedis.exists(key);
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 给一个key设置失效时间
     *
     * @param key
     * @param seconds
     * @throws
     * @description:
     */
    public void expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            jedis.expire(key, seconds);
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public Long hset(String key, String field, Object value) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            return jedis.hset(key, field, JSON.toJSONString(value));
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return 0l;
    }

    @Override
    public <T> T hget(String key, String field, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            String value = jedis.hget(key, field);
            if (StringUtils.isNotBlank(value)) {
                return (T) JSON.parseObject(value, clazz);
            }
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return null;
    }

    @Override
    public Long hdel(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = getJedisInstance();
            return jedis.hdel(key, field);
        } catch (RuntimeException e) {
            handleException(e, key);
        } finally {
            returnJedis(jedis);
        }
        return 0l;
    }

}
