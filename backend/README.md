# 香农之翼后端

## 后端职责

- 用户、商品、订单、支付、优惠券等原有能力
- 无人机配送履约能力（delivery 模块）
- 当前保留 wings 原模块，不做物理删除

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

启动类：`com.wings.server.WingsServerApplication`

## 本地配置

- 配置文件：`application-local.yaml`
- 端口：18081
- 数据库：wings
- Redis：127.0.0.1:6379

## 后端目录结构

```
backend/
├── dependencies/       # Maven 依赖版本管理（BOM）
├── framework/          # 基础框架 Starter 模块
│   ├── wings-common/   # 公共工具类、常量、异常、响应结构
│   ├── wings-spring-boot-starter-web/        # Web 配置、全局异常、跨域
│   ├── wings-spring-boot-starter-security/   # 安全认证、JWT、权限
│   ├── wings-spring-boot-starter-mybatis/    # MyBatis Plus、分页、数据源
│   ├── wings-spring-boot-starter-redis/      # Redis、缓存
│   ├── wings-spring-boot-starter-mq/         # 消息队列
│   ├── wings-spring-boot-starter-job/        # 定时任务
│   ├── wings-spring-boot-starter-excel/      # Excel 导入导出
│   ├── wings-spring-boot-starter-monitor/    # 监控
│   ├── wings-spring-boot-starter-protection/ # 接口保护、限流
│   ├── wings-spring-boot-starter-websocket/  # WebSocket
│   ├── wings-spring-boot-starter-biz-tenant/ # 多租户
│   ├── wings-spring-boot-starter-biz-data-permission/ # 数据权限
│   ├── wings-spring-boot-starter-biz-ip/     # IP 地址解析
│   └── wings-spring-boot-starter-test/       # 测试基础设施
├── server/             # Spring Boot 启动入口
│   └── src/main/java/co/wings/wings/server/
│       ├── WingsServerApplication.java  # 主启动类
│       └── controller/                  # 默认控制器
└── modules/            # 业务模块
    ├── business/       # 商品、订单、店铺等业务模块
    ├── delivery/       # 香农之翼新增配送履约模块（无人机配送）
    ├── system/         # 系统管理（用户、角色、菜单、字典等）
    ├── infra/          # 基础设施（文件、配置、代码生成等）
    ├── pay/            # 支付模块
    ├── member/         # 用户/会员底层能力
    ├── marketing/      # 营销模块
    ├── message/        # 消息模块
    ├── mp/             # 公众号（当前冻结）
    ├── score/          # 积分商城（当前冻结）
    └── express/        # 快递物流（当前冻结）
```

## 目录职责说明

### server

- **职责**：Spring Boot 应用启动入口
- **内容**：主启动类、默认控制器、全局配置
- **注意**：业务代码应放在 modules 中，不要在 server 中添加业务逻辑

### modules

- **职责**：业务模块集合
- **内容**：各业务领域的 Controller、Service、Mapper、Entity
- **原则**：模块之间低耦合，通过 API 接口交互

### framework

- **职责**：基础框架能力
- **内容**：Spring Boot Starter 封装
- **详见**：[framework/README.md](framework/README.md)

### delivery 模块

- **职责**：香农之翼新增的无人机配送履约能力
- **内容**：配送任务管理、无人机状态管理、配送调度
- **状态**：当前为 MVP 阶段，部分功能使用 mock 数据

## 后端命名说明

当前后端 Java package、Maven module 仍保留 `wings`/`com.wings` 命名。

这是为了降低 Spring/Maven/MyBatis 重命名风险。后续如需重命名，将单独作为高风险重构任务处理。

**当前阶段保留 wings / com.wings 命名，避免大规模 Maven artifactId 和包名迁移风险。**

## 架构原则

- 订单负责交易
- 配送任务负责履约
- 无人机状态、电量、轨迹、位置不要塞进订单表

## 部署脚本说明

backend/script 下的 Docker/部署脚本属于历史脚本，当前本地开发以 docs/localtest.md 和 IDEA 启动为准；正式部署前需要单独校准脚本。
