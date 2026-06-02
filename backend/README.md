# 后端

## 职责

- 提供管理端与小程序端所需 REST API
- 承载用户、商品、订单、支付、优惠券等基础业务能力
- 承载配送任务、无人机状态、配送轨迹等履约能力
- 当前 MVP 阶段：非核心模块保留代码，但前端入口冻结

## 技术栈

- Java 17
- Spring Boot 3
- MyBatis Plus
- MySQL 8
- Redis

## 启动方式

```bash
cd backend
mvn clean install -DskipTests
```

启动类：`com.ordering.server.BackendApplication`

## 本地配置

- 配置文件：`backend/server/src/main/resources/application-local.yaml`
- 端口：18081
- 数据库：app
- Redis：127.0.0.1:6379

## 目录结构

```text
backend/
├── dependencies/       # Maven 依赖版本管理
├── framework/          # 基础框架能力
│   ├── common/                 # 公共工具、常量、异常、响应结构
│   ├── starter-web/            # Web、异常处理、跨域、响应包装
│   ├── starter-security/       # 登录认证、权限校验
│   ├── starter-mybatis/        # MyBatis Plus、数据源、分页
│   ├── starter-redis/          # Redis、缓存
│   ├── starter-mq/             # 消息队列
│   ├── starter-job/            # 定时任务
│   ├── starter-excel/          # Excel 导入导出
│   ├── starter-monitor/        # 监控链路
│   ├── starter-protection/     # 限流、防重复提交
│   ├── starter-websocket/      # WebSocket
│   ├── starter-tenant/         # 多租户
│   ├── starter-data-permission/# 数据权限
│   ├── starter-ip/             # IP 与地区解析
│   └── starter-test/           # 测试基础设施
├── server/             # Spring Boot 启动入口
│   └── src/main/java/com/ordering/server/
│       ├── BackendApplication.java
│       └── controller/
└── modules/            # 业务模块
    ├── business/       # 商品、订单、店铺等点餐业务
    ├── delivery/       # 配送履约
    ├── system/         # 系统管理
    ├── infra/          # 文件、配置、代码生成等基础设施
    ├── pay/            # 支付
    ├── member/         # 用户/会员底层能力
    ├── marketing/      # 优惠券等营销能力
    ├── message/        # 消息通知
    ├── mp/             # 公众号，当前冻结
    ├── score/          # 积分商城，当前冻结
    └── express/        # 快递物流，当前冻结
```

## 模块边界

- `server` 只做启动入口，不放业务逻辑
- `modules` 放业务代码
- `framework` 放可复用基础能力
- 订单模块负责交易状态
- 配送模块负责履约状态
- 无人机状态、电量、轨迹、位置独立管理，不塞进订单表

## 包名与配置前缀

- Java 根包名：`com.ordering`
- Maven groupId：`com.ordering`
- Spring 配置前缀：`app`

`app` 仅作为内部配置前缀使用，不表示业务品牌。

## 部署脚本说明

`backend/script` 下的脚本来自历史工程，本地开发以 `docs/localtest.md` 和 IDEA 启动为准；正式部署前需要单独校准。
