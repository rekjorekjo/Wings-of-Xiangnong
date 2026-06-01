# 后端框架模块说明

本目录包含香农之翼后端的基础框架能力，以 Spring Boot Starter 形式提供。

## 模块列表

### wings-common

公共工具模块，包含：
- 通用工具类（日期、字符串、集合等）
- 通用常量和枚举
- 通用异常定义
- 通用响应结构（CommonResult 等）

### wings-spring-boot-starter-web

Web 模块，包含：
- Spring MVC 配置
- 全局异常处理
- 跨域配置
- 请求日志
- API 响应包装

### wings-spring-boot-starter-security

安全模块，包含：
- Spring Security 配置
- JWT Token 认证
- 权限校验
- 登录/登出逻辑

### wings-spring-boot-starter-mybatis

MyBatis 模块，包含：
- MyBatis Plus 配置
- 分页插件
- 数据源配置
- 通用 Mapper

### wings-spring-boot-starter-redis

Redis 模块，包含：
- Redis 连接配置
- RedisTemplate 封装
- 缓存工具类

### wings-spring-boot-starter-mq

消息队列模块，包含：
- Redis Stream 消息队列
- 消息生产者和消费者配置

### wings-spring-boot-starter-job

定时任务模块，包含：
- Quartz 配置
- 定时任务管理

### wings-spring-boot-starter-excel

Excel 模块，包含：
- EasyExcel 配置
- Excel 导入导出工具

### wings-spring-boot-starter-monitor

监控模块，包含：
- Spring Boot Admin 配置
- Actuator 端点配置

### wings-spring-boot-starter-protection

保护模块，包含：
- 接口限流
- 防重复提交

### wings-spring-boot-starter-websocket

WebSocket 模块，包含：
- WebSocket 配置
- 消息推送能力

### wings-spring-boot-starter-biz-tenant

多租户模块，包含：
- 租户上下文
- 租户数据隔离

### wings-spring-boot-starter-biz-data-permission

数据权限模块，包含：
- 数据权限注解
- 数据权限过滤

### wings-spring-boot-starter-biz-ip

IP 地址模块，包含：
- IP 地址解析
- 地区数据

### wings-spring-boot-starter-test

测试模块，包含：
- 测试基础设施
- Mock 工具

## 使用方式

各模块通过 Maven 依赖引入，在 `pom.xml` 中添加：

```xml
<dependency>
    <groupId>com.wings</groupId>
    <artifactId>wings-spring-boot-starter-xxx</artifactId>
</dependency>
```

## 注意事项

- 当前阶段保留 `wings`/`com.wings` 命名，避免大规模 Maven artifactId 和包名迁移风险
- 各模块通过 Spring Boot AutoConfiguration 自动装配
- 不要随意删除或重命名模块
