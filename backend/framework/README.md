# 后端框架模块

`framework` 目录放基础框架能力。这里不写业务流程，只封装通用技术能力，供 `modules` 和 `server` 使用。

## 模块列表

| 模块 | 职责 |
|------|------|
| common | 公共工具、常量、异常、统一响应结构 |
| starter-web | Web MVC、全局异常、跨域、响应包装、接口日志 |
| starter-security | Spring Security、Token 认证、权限校验 |
| starter-mybatis | MyBatis Plus、数据源、分页、通用 Mapper |
| starter-redis | Redis 连接、缓存、Redis 工具 |
| starter-mq | Redis Stream 等消息队列封装 |
| starter-job | Quartz 定时任务 |
| starter-excel | Excel 导入导出 |
| starter-monitor | 链路追踪、指标监控 |
| starter-protection | 限流、防重复提交、分布式锁 |
| starter-websocket | WebSocket 消息推送 |
| starter-tenant | 多租户上下文与数据隔离 |
| starter-data-permission | 数据权限注解与过滤 |
| starter-ip | IP 与地区解析 |
| starter-test | 后端测试基础设施 |

## 使用方式

在需要的模块 `pom.xml` 中引入对应依赖：

```xml
<dependency>
    <groupId>com.ordering</groupId>
    <artifactId>starter-web</artifactId>
</dependency>
```

## 维护原则

- 不在 framework 中写具体业务流程
- starter 只提供基础能力和自动配置
- 业务规则放在 modules 中
- 删除或重命名 starter 前，必须确认所有 pom 依赖和 AutoConfiguration 配置同步更新
