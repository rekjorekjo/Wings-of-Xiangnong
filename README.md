# 校园无人机配送点餐系统

面向校园场景的点餐与配送 MVP。项目由三个端组成：后端、管理端、小程序端。

## 目录结构

```text
backend     Spring Boot 3 后端
admin       Vue3 管理端
miniapp     uni-app 小程序端
docs        项目文档
```

## 本地环境

- JDK 17
- Maven 3.8+
- MySQL 8
- Redis 127.0.0.1:6379
- Node 20
- pnpm 10

## 本地地址

| 服务 | 地址 |
|------|------|
| 后端 | http://localhost:18081 |
| 管理端 | http://localhost:80 |
| 小程序 API | http://localhost:18081/app-api |
| 管理端 API | http://localhost:18081/admin-api |

## 启动命令

### 后端

```bash
cd backend
mvn clean install -DskipTests
```

启动类：`com.ordering.server.BackendApplication`

### 管理端

```bash
cd admin
pnpm install
pnpm dev
```

### 小程序

1. 使用 HBuilderX 打开 `miniapp`
2. 确认 `miniapp/config/index.js` 指向 `http://localhost:18081/app-api`

## 当前策略

- 先完成基础点餐、后台运营、配送任务 MVP
- 冻结但暂不物理删除积分、余额、会员卡、公众号、收银台等非核心功能
- 订单只负责交易，配送任务负责履约
- 无人机状态、电量、轨迹、位置不写入订单表

## 开源协议

MIT License
