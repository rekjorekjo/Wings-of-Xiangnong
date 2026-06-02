package com.ordering.module.delivery.framework.web.config;

import com.ordering.module.delivery.framework.config.DeliveryProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DeliveryProperties.class)
public class DeliveryWebConfiguration {
}
