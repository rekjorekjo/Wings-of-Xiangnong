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

## 后端命名说明

当前后端 Java package、Maven module 仍保留 `yshop`/`co.yixiang` 命名。

这是为了降低 Spring/Maven/MyBatis 重命名风险。后续如需重命名，将单独作为高风险重构任务处理。

## 架构原则

- 订单负责交易
- 配送任务负责履约
- 无人机状态、电量、轨迹、位置不要塞进订单表
