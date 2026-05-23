# 香农之翼后端

## 后端职责

- 用户、商品、订单、支付、优惠券等原有能力
- 后续新增 delivery 独立模块
- 当前保留 yshop 原模块，不做物理删除

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

启动类：`co.yixiang.yshop.server.YshopServerApplication`

## 本地配置

- 配置文件：`application-local.yaml`
- 端口：18081
- 数据库：yixiang-drink-open
- Redis：127.0.0.1:6379

## 当前后端目录结构

```
backend/
├── dependencies/       # Maven 依赖版本管理
├── framework/          # 公共框架能力
├── server/             # Spring Boot 启动模块
└── modules/
    ├── business/       # 商品、订单、店铺等业务模块（原 yshop-module-mall）
    ├── system/         # 系统管理
    ├── infra/          # 基础设施
    ├── pay/            # 支付
    ├── member/         # 用户/会员底层能力
    ├── marketing/      # 营销
    ├── message/        # 消息
    ├── delivery/       # 无人机配送（MVP mock）
    ├── mp/             # 公众号（当前冻结）
    ├── score/          # 积分商城（当前冻结）
    └── express/        # 快递物流（当前冻结）
```

Java package 和 Maven artifactId 仍保留 yshop/co.yixiang 命名，属于兼容保留，不在本轮修改范围。

## 后端命名说明

当前后端 Java package、Maven module 仍保留 `yshop`/`co.yixiang` 命名。

这是为了降低 Spring/Maven/MyBatis 重命名风险。后续如需重命名，将单独作为高风险重构任务处理。

## 架构原则

- 订单负责交易
- 配送任务负责履约
- 无人机状态、电量、轨迹、位置不要塞进订单表

## 部署脚本说明

backend/script 下的 Docker/部署脚本属于历史脚本，当前本地开发以 docs/localtest.md 和 IDEA 启动为准；正式部署前需要单独校准脚本。
