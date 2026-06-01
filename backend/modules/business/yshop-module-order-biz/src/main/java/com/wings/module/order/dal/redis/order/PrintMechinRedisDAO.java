package com.wings.module.order.dal.redis.order;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.wings.module.order.dal.redis.RedisKeyConstants.WINGS_WEB_PRINT_MECHINE_KEY;
import static com.wings.module.store.dal.redis.RedisKeyConstants.WINGS_WEB_PRINT_TOKEN_KEY;


/**

 *
 * @author wings
 */
@Repository
public class PrintMechinRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get(Long shopId) {
        String redisKey = formatKey(shopId);
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public void set(Long shopId,String o) {
        String redisKey = formatKey(shopId);
        stringRedisTemplate.opsForValue().set(redisKey, o);
    }

    public void delete(Long shopId) {
        String redisKey = formatKey(shopId);
        stringRedisTemplate.delete(redisKey);
    }



    private static String formatKey(Long shopId) {
        return String.format(WINGS_WEB_PRINT_MECHINE_KEY.getKeyTemplate(), shopId);
    }

}
