package com.ordering.module.member.dal.redis.order;

import com.ordering.framework.common.util.json.JsonUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;


import static com.ordering.module.member.dal.redis.RedisKeyConstants.APP_MINI_LOGIN_CACHE_KEY;


/**
 *
 * @author project team
 */
@Repository
public class MiniRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get(String key) {
        String redisKey = formatKey(key);
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public String set(String str,String key) {
        String redisKey = formatKey(key);
        stringRedisTemplate.opsForValue().set(redisKey, str);
        return key;
    }

    public void delete(String key) {
        String redisKey = formatKey(key);
        stringRedisTemplate.delete(redisKey);
    }



    private static String formatKey(String key) {
        return String.format(APP_MINI_LOGIN_CACHE_KEY.getKeyTemplate(), key);
    }

}
