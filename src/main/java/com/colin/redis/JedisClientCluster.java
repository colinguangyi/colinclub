package com.colin.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * @author zhaolz
 * @create 2017-08-29
 */
@Component
public class JedisClientCluster {

    @Autowired
    JedisCluster jedisCluster;

    public String set(String key, String value){
        return jedisCluster.set(key, value);
    }

    public String get(String key){
        return jedisCluster.get(key);
    }
}
