package com.wings.module.store.dal.redis;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static com.wings.module.store.dal.redis.RedisKeyConstants.WINGS_WEB_PRINT_TOKEN_KEY;


/**

 *
 * @author wings
 */
@Repository
public class PrintTokenRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get() {
        String redisKey = WINGS_WEB_PRINT_TOKEN_KEY.getKeyTemplate();
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public void set(String o) {
        String redisKey = WINGS_WEB_PRINT_TOKEN_KEY.getKeyTemplate();


        stringRedisTemplate.opsForValue().set(redisKey, o,2, TimeUnit.HOURS);
    }

    public void delete() {
        String redisKey = WINGS_WEB_PRINT_TOKEN_KEY.getKeyTemplate();
        stringRedisTemplate.delete(redisKey);
    }


//
//    private static String formatKey) {
//        return String.format(WINGS_ORDER_SALE_STATUS_KEY.getKeyTemplate(), key);
//    }

}
