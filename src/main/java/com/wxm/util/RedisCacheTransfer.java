package com.wxm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-18 22:32
 */
public class RedisCacheTransfer {
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisCache.setRedisTemplate(redisTemplate);
    }
}
