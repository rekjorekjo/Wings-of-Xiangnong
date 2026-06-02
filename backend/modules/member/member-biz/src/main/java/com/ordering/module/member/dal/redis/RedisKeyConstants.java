package com.ordering.module.member.dal.redis;

import com.ordering.framework.redis.core.RedisKeyDefine;

import static com.ordering.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
 * @author project team
 */
public interface RedisKeyConstants {




    RedisKeyDefine APP_MINI_LOGIN_CACHE_KEY = new RedisKeyDefine("小程序登录session",
            "app_mini_login_cache:%s", // 参数为访问uid+key
            STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);




}
