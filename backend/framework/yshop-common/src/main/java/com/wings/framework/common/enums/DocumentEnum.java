package com.wings.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文档地址
 *
 * @author wings
 */
@Getter
@AllArgsConstructor
public enum DocumentEnum {

    REDIS_INSTALL("https://gitee.com/zhijiantianya/wings-drink/issues/I4VCSJ", "Redis 安装文档"),
    TENANT("https://www.wings.co", "SaaS 多租户文档");

    private final String url;
    private final String memo;

}
