package com.ordering.framework.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Application cache extension properties.
 */
@ConfigurationProperties("app.cache")
@Data
@Validated
public class AppCacheProperties {

    private static final Integer REDIS_SCAN_BATCH_SIZE_DEFAULT = 30;

    /**
     * Redis SCAN batch size used by RedisCacheWriter.
     */
    private Integer redisScanBatchSize = REDIS_SCAN_BATCH_SIZE_DEFAULT;

}
