package com.wings.module.order.dal.redis.order;

import cn.hutool.core.util.IdUtil;
import com.wings.framework.common.constant.ShopConstants;
import com.wings.framework.common.util.json.JsonUtils;
import com.wings.module.order.service.storeorder.dto.CacheDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.wings.module.order.dal.redis.RedisKeyConstants.WINGS_ORDER_CACHE_KEY;

/**
 * {@link CacheDto} 的 RedisDAO
 *
 * @author wings
 */
@Repository
public class OrderRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public CacheDto get(String key,Long uid) {
        String redisKey = formatKey(key+uid);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), CacheDto.class);
    }

    public String set(CacheDto cacheDto,Long uid) {
        String key = IdUtil.simpleUUID();
        String redisKey = formatKey(key + uid);
        long time =  ShopConstants.WINGS_ORDER_CACHE_TIME;
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(cacheDto), time, TimeUnit.SECONDS);
        return key;
    }

    public void delete(String key,Long uid) {
        String redisKey = formatKey(key+uid);
        stringRedisTemplate.delete(redisKey);
    }



    private static String formatKey(String key) {
        return String.format(WINGS_ORDER_CACHE_KEY.getKeyTemplate(), key);
    }

}
