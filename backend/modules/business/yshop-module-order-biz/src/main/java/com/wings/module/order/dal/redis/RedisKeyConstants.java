package com.wings.module.order.dal.redis;

import com.wings.framework.redis.core.RedisKeyDefine;
import com.wings.module.order.service.storeorder.dto.CacheDto;

import static com.wings.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
 * @author wings
 */
public interface RedisKeyConstants {


    RedisKeyDefine WINGS_ORDER_CACHE_KEY = new RedisKeyDefine("确认订单数据缓存",
            "wings_order_cache:%s", // 参数为访问uid+key
            STRING, CacheDto.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefine WINGS_ORDER_SALE_STATUS_KEY = new RedisKeyDefine("售后订单数据缓存",
            "wings_after_order_cache:%s", // 参数为访问uid+key
            STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefine WINGS_ORDER_COUNT_CACHE_KEY = new RedisKeyDefine("统计订单数据缓存",
            "wings_order_count_cache:%s", // 参数为访问uid
            STRING, CacheDto.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);

    RedisKeyDefine WINGS_ADMIN_ORDER_COUNT_CACHE_KEY = new RedisKeyDefine("后台统计订单数据缓存",
            "wings_admin_order_count_cache:", // 参数为访问uid
            STRING, CacheDto.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);

    RedisKeyDefine WINGS_WEB_PRINT_MECHINE_KEY = new RedisKeyDefine("打印机token",
            "wings_web_print_machine_cache:%s", // 参数为访问shopid
            STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);


}
