package com.ordering.module.express.dal.redis.express;

import com.ordering.framework.common.util.json.JsonUtils;
import com.ordering.framework.tenant.core.context.TenantContextHolder;
import com.ordering.module.express.kdniao.model.dto.KdniaoApiBaseDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;

import static com.ordering.module.express.dal.redis.RedisKeyConstants.APP_EXPRESS_CACHE_KEY;


/**
 * {@link KdniaoApiBaseDTO} 的 RedisDAO
 *
 * @author project team
 */
@Repository
public class ExpressRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public KdniaoApiBaseDTO get() {
        String redisKey = formatKey();
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), KdniaoApiBaseDTO.class);
    }

    public void set(KdniaoApiBaseDTO apiBaseDTO) {
        String redisKey = formatKey();
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(apiBaseDTO));
    }

    public void delete() {
        String redisKey = formatKey();
        stringRedisTemplate.delete(redisKey);
    }



    private static String formatKey() {
        return String.format(APP_EXPRESS_CACHE_KEY.getKeyTemplate());
    }

}
