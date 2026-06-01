package com.wings.module.order.dal.redis.order;

import com.wings.framework.common.util.json.JsonUtils;
import com.wings.framework.tenant.core.context.TenantContextHolder;
import com.wings.module.member.controller.app.user.vo.AppUserOrderCountVo;
import com.wings.module.order.controller.admin.storeorder.vo.ShoperOrderTimeDataVo;
import com.wings.module.order.service.storeorder.dto.OrderTimeDataDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;

import static com.wings.module.order.dal.redis.RedisKeyConstants.WINGS_ADMIN_ORDER_COUNT_CACHE_KEY;

/**
 * {@link AppUserOrderCountVo} 的 RedisDAO
 *
 * @author wings
 */
@Repository
public class AsyncCountRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public OrderTimeDataDto get() {
        String redisKey = formatKey();
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), OrderTimeDataDto.class);
    }

    public void set(OrderTimeDataDto orderTimeDataDto) {
        String redisKey = formatKey();
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(orderTimeDataDto));
    }

    public void delete(Long uid) {
        String redisKey = WINGS_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate();
        stringRedisTemplate.delete(redisKey);
    }


    private static String formatKey() {
        return String.format(WINGS_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate());
    }

}
