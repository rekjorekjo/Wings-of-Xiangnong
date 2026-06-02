package com.ordering.order.dal.redis.order;

import cn.hutool.core.util.IdUtil;
import com.ordering.framework.common.constant.ShopConstants;
import com.ordering.framework.common.util.json.JsonUtils;
import com.ordering.member.controller.app.user.vo.AppUserOrderCountVo;
import com.ordering.order.service.storeorder.dto.CacheDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.ordering.order.dal.redis.RedisKeyConstants.APP_ORDER_CACHE_KEY;
import static com.ordering.order.dal.redis.RedisKeyConstants.APP_ORDER_COUNT_CACHE_KEY;

/**
 * {@link AppUserOrderCountVo} 的 RedisDAO
 *
 * @author project team
 */
@Repository
public class AsyncOrderRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public AppUserOrderCountVo get(Long uid) {
        String redisKey = formatKey(""+uid);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), AppUserOrderCountVo.class);
    }

    public void set(AppUserOrderCountVo appUserOrderCountVo, Long uid) {
        String redisKey = formatKey("" + uid);
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(appUserOrderCountVo));
    }

    public void delete(Long uid) {
        String redisKey = formatKey(""+uid);
        stringRedisTemplate.delete(redisKey);
    }


    private static String formatKey(String key) {
        return String.format(APP_ORDER_COUNT_CACHE_KEY.getKeyTemplate(), key);
    }

}
