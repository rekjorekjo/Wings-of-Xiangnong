package co.yixiang.yshop.module.delivery.framework.web.config;

import co.yixiang.yshop.module.delivery.framework.config.DeliveryProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DeliveryProperties.class)
public class DeliveryWebConfiguration {
}
