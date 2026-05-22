# 本地启动说明

## 环境版本

- JDK 17
- Node 20
- pnpm 10
- MySQL 8
- Redis 127.0.0.1:6379

## 后端启动

```bash
cd backend
mvn clean install -DskipTests
```

启动类：`YshopServerApplication`

端口：18081

## 管理端启动

```bash
cd admin
pnpm install
pnpm dev
```

地址：http://localhost:80

## 小程序启动

1. HBuilderX 打开 `miniapp`
2. 确认 API 为 http://localhost:18081/app-api

## 常见问题

- 访问 http://localhost:18081 返回 401 是正常鉴权行为
- Maven test 卡住时使用 `-DskipTests`
- 管理端依赖缺失时重新 `pnpm install`
- Element Plus 组件缺失时再处理依赖，不要改源码绕过
- IDEA 找不到主类时重新导入 `backend/pom.xml`
