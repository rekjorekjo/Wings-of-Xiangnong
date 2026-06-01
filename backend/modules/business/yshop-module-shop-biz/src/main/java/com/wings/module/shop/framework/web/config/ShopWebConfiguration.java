package com.wings.module.shop.framework.web.config;

import com.wings.framework.swagger.config.WingsSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shop 模块的 web 组件的 Configuration
 *
 * @author wings
 */
@Configuration(proxyBeanMethods = false)
public class ShopWebConfiguration {

    /**
     * promotion 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi shopGroupedOpenApi() {
        return WingsSwaggerAutoConfiguration.buildGroupedOpenApi("shop");
    }

}
