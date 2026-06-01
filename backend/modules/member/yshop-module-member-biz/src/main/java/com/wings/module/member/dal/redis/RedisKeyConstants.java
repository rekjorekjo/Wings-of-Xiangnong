package com.wings.module.member.dal.redis;

import com.wings.framework.redis.core.RedisKeyDefine;

import static com.wings.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
 * @author wings
 */
public interface RedisKeyConstants {




    RedisKeyDefine WINGS_MINI_LOGIN_CACHE_KEY = new RedisKeyDefine("小程序登录session",
            "wings_mini_login_cache:%s", // 参数为访问uid+key
            STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);




}
