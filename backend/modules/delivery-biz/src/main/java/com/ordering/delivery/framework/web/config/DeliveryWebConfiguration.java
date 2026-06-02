package com.ordering.delivery.framework.web.config;

import com.ordering.delivery.framework.config.DeliveryProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DeliveryProperties.class)
public class DeliveryWebConfiguration {
}
