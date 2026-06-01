# 香农之翼（Wings）

基于 wings 改造的东南大学九龙湖校区 DC 香农咖啡无人机配送 MVP。

## 目录结构

```
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

启动类：`com.wings.server.WingsServerApplication`

### 管理端

```bash
cd admin
pnpm install
pnpm dev
```

### 小程序

1. 使用 HBuilderX 打开 `miniapp`
2. 确认 `miniapp/config/index.js` 指向 `http://localhost:18081/app-api`

## 当前改造策略

- 第一阶段先做结构收敛和管理端 MVP
- 旧功能不物理删除
- 积分、余额、会员卡、公众号、收银台等功能先隐藏/冻结
- 后续商家需要时通过入口、菜单、配置开关恢复
- 订单和配送任务分离，不把无人机状态塞进订单表

## 来源说明

本项目基于 wings 改造，原项目地址：https://www.wings.co/

## 开源协议

MIT License
