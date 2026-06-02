package com.ordering.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文档地址
 *
 * @author project team
 */
@Getter
@AllArgsConstructor
public enum DocumentEnum {

    REDIS_INSTALL("https://gitee.com/zhijiantianya/app_db/issues/I4VCSJ", "Redis 安装文档"),
    TENANT("http://localhost:80", "SaaS 多租户文档");

    private final String url;
    private final String memo;

}
