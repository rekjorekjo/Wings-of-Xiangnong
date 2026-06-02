package com.ordering.module.express.dal.redis;

import com.ordering.framework.redis.core.RedisKeyDefine;
import com.ordering.module.express.kdniao.model.dto.KdniaoApiBaseDTO;

import static com.ordering.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
 * @author project team
 */
public interface RedisKeyConstants {


    RedisKeyDefine APP_EXPRESS_CACHE_KEY = new RedisKeyDefine("快递鸟配置",
            "app_express_cache:", //
            STRING, KdniaoApiBaseDTO.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);




}
