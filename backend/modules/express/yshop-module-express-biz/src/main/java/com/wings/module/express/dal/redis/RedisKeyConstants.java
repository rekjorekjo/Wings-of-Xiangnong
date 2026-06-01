package com.wings.module.express.dal.redis;

import com.wings.framework.redis.core.RedisKeyDefine;
import com.wings.module.express.kdniao.model.dto.KdniaoApiBaseDTO;

import static com.wings.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
 * @author wings
 */
public interface RedisKeyConstants {


    RedisKeyDefine WINGS_EXPRESS_CACHE_KEY = new RedisKeyDefine("快递鸟配置",
            "wings_express_cache:", //
            STRING, KdniaoApiBaseDTO.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);




}
