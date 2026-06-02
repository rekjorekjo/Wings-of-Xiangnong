# 香农之翼 - 校园无人机配送点餐系统

面向校园场景的点餐与无人机配送 MVP 系统。

## 项目简介

**项目名称**：香农之翼无人机点餐系统

**当前 MVP 重点**：
- 点餐运营
- 小程序下单
- 后台管理
- 配送任务展示

**无人机部分**：当前主要是配送任务与状态展示，真实无人机调度尚未完全接入。

## 仓库结构

```text
<project-root>/
├── backend/       # Spring Boot 后端服务
├── admin/         # Vue3 后台管理端
├── miniapp/       # uni-app 微信小程序端
├── docs/          # 项目文档
├── assets/        # 本地素材目录（不提交 GitHub）
└── package.ps1    # 打包上传脚本
```

### 目录说明

| 目录 | 说明 |
|------|------|
| `assets/` | 本地素材目录，存放截图、临时资源等，不提交 GitHub，不进入上传包 |
| `docs/assets/` | 正式文档图片目录，如需提交文档图片请使用此目录 |
| `admin/src/assets` | 管理端源码资源，必须提交 |
| `miniapp/static` | 小程序源码资源，必须提交 |

## 本地环境要求

| 环境 | 版本要求 |
|------|----------|
| JDK | 17 |
| Maven | 3.9+ |
| Node.js | 20+ |
| pnpm | 最新版 |
| MySQL | 8.x |
| Redis | 任意版本 |
| 微信开发者工具 | 最新版 |
| IDE | IDEA / VS Code 可选 |

## 本地启动顺序

1. **启动 MySQL** - 确保数据库 `app` 已创建
2. **启动 Redis** - 默认端口 6379
3. **启动后端** - 见下方后端启动命令
4. **启动管理端** - 见下方管理端启动命令
5. **打开小程序** - 使用微信开发者工具打开 `miniapp` 目录

## 启动命令

### 后端

```bash
cd backend
mvn -pl server -am -DskipTests clean compile
```

启动类：`com.ordering.server.BackendApplication`

IDEA 中直接运行 `BackendApplication.java` 即可。

### 管理端

```bash
cd admin
pnpm install
pnpm dev
```

### 小程序

1. 使用微信开发者工具打开 `miniapp` 目录
2. 确认 `miniapp/config/index.js` 中 API 指向 `http://localhost:18081/app-api`

## 常用端口

| 服务 | 端口 |
|------|------|
| 后端 | 18081 |
| 管理端 | 80（如被占用自动切到 81） |
| MySQL | 3306 |
| Redis | 6379 |

## 本地地址

| 服务 | 地址 |
|------|------|
| 后端根路径 | http://localhost:18081 |
| 管理端 | http://localhost:80 |
| 管理端 API | http://localhost:18081/admin-api |
| 小程序 API | http://localhost:18081/app-api |

## 本地数据库说明

- **数据库名**：`app`
- **表名前缀**：`app_`
- **配置文件**：`backend/server/src/main/resources/application-local.yaml`

如果从旧库迁移，需要保证 `app_store_shop` 等表存在。

## 当前开发约定

1. **不提交** `node_modules`、`target`、`dist`、`unpackage`
2. **admin 依赖安装和运行**要在同一环境执行，不要 Windows 安装后在 WSL 跑
3. **组员不要直接改 version1**，应从最新 version1 拉功能分支
4. **冻结功能**：积分商城、余额充值、会员卡、公众号等非核心功能已冻结，源码保留但不启用

## 架构原则

- 订单负责交易
- 配送任务负责履约
- 无人机状态、电量、轨迹、位置不写入订单表

## 开源协议

MIT License
