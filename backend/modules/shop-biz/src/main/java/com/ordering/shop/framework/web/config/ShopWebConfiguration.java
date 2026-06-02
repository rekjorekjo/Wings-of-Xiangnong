package com.ordering.shop.framework.web.config;

import com.ordering.framework.swagger.config.SwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shop 模块的 web 组件的 Configuration
 *
 * @author project team
 */
@Configuration(proxyBeanMethods = false)
public class ShopWebConfiguration {

    /**
     * promotion 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi shopGroupedOpenApi() {
        return SwaggerAutoConfiguration.buildGroupedOpenApi("shop");
    }

}
