package com.ordering.module.order.dal.redis.order;

import com.ordering.framework.common.util.json.JsonUtils;
import com.ordering.framework.tenant.core.context.TenantContextHolder;
import com.ordering.module.member.controller.app.user.vo.AppUserOrderCountVo;
import com.ordering.module.order.controller.admin.storeorder.vo.ShoperOrderTimeDataVo;
import com.ordering.module.order.service.storeorder.dto.OrderTimeDataDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;

import static com.ordering.module.order.dal.redis.RedisKeyConstants.APP_ADMIN_ORDER_COUNT_CACHE_KEY;

/**
 * {@link AppUserOrderCountVo} 的 RedisDAO
 *
 * @author project team
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
        String redisKey = APP_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate();
        stringRedisTemplate.delete(redisKey);
    }


    private static String formatKey() {
        return String.format(APP_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate());
    }

}
