# 项目目录结构说明

本文档详细说明香农之翼项目的目录结构。

## 顶层目录

```text
<project-root>/
├── backend/       # Spring Boot 后端服务
├── admin/         # Vue3 后台管理端
├── miniapp/       # uni-app 微信小程序端
├── docs/          # 项目文档
├── assets/        # 本地素材目录（不提交 GitHub）
├── package.ps1    # 打包上传脚本
└── README.md      # 项目说明
```

### 目录说明

| 目录 | 说明 |
|------|------|
| `assets/` | 本地素材目录，存放截图、临时资源等，不提交 GitHub，不进入上传包 |
| `docs/assets/` | 正式文档图片目录，如需提交文档图片请使用此目录 |
| `admin/src/assets` | 管理端源码资源，必须提交 |
| `miniapp/static` | 小程序源码资源，必须提交 |

## 后端目录结构

```text
backend/
├── dependencies/       # Maven 依赖版本管理（BOM）
├── framework/          # 基础框架 Starter 模块
├── server/             # Spring Boot 启动入口
├── modules/            # 业务模块
└── script/             # 辅助脚本（部署等）
```

### framework 模块

```text
framework/
├── common/                    # 公共工具类、常量、异常、响应结构
├── starter-web/               # Web 配置、全局异常、跨域
├── starter-security/          # 安全认证、JWT、权限
├── starter-mybatis/           # MyBatis Plus、分页、数据源
├── starter-redis/             # Redis、缓存
├── starter-mq/                # 消息队列
├── starter-job/               # 定时任务
├── starter-excel/             # Excel 导入导出
├── starter-monitor/           # 监控
├── starter-protection/        # 接口保护、限流
├── starter-websocket/         # WebSocket
├── starter-tenant/            # 多租户
├── starter-data-permission/   # 数据权限
├── starter-ip/                # IP 地址解析
└── starter-test/              # 测试基础设施
```

### modules 模块

每个业务领域分为 `-api`（接口定义、枚举、DTO）和 `-biz`（实现、Controller、Service、Mapper）：

```text
modules/
├── system-api/     # 系统管理 API
├── system-biz/     # 系统管理实现
├── infra-api/      # 基础设施 API
├── infra-biz/      # 基础设施实现
├── member-api/     # 会员 API
├── member-biz/     # 会员实现
├── product-api/    # 商品 API
├── product-biz/    # 商品实现
├── order-api/      # 订单 API
├── order-biz/      # 订单实现
├── store-api/      # 店铺 API
├── store-biz/      # 店铺实现
├── shop-api/       # 商店 API
├── shop-biz/       # 商店实现
├── delivery-api/   # 配送 API（香农之翼新增）
├── delivery-biz/   # 配送实现（香农之翼新增）
├── pay-api/        # 支付 API
├── pay-biz/        # 支付实现
├── coupon-api/     # 优惠券 API
├── coupon-biz/     # 优惠券实现
├── score-api/      # 积分 API（冻结）
├── score-biz/      # 积分实现（冻结）
├── express-api/    # 快递 API（冻结）
├── express-biz/    # 快递实现（冻结）
├── mp-api/         # 公众号 API（冻结）
├── mp-biz/         # 公众号实现（冻结）
├── message-api/    # 消息 API
└── message-biz/    # 消息实现
```

## 管理端目录结构

```text
admin/
├── build/              # 构建配置
│   └── vite/           # Vite 插件配置（源码，不是构建产物）
├── public/             # 静态资源
├── src/
│   ├── api/            # API 接口定义
│   ├── components/     # 公共组件
│   ├── hooks/          # 组合式函数
│   ├── layout/         # 布局组件
│   ├── router/         # 路由配置
│   ├── store/          # Pinia 状态管理
│   ├── styles/         # 样式文件
│   ├── types/          # TypeScript 类型定义
│   ├── utils/          # 工具函数
│   └── views/          # 页面组件
├── .env.dev            # 开发环境配置
├── .env.prod           # 生产环境配置
├── package.json
├── pnpm-lock.yaml
└── vite.config.ts
```

### views 页面结构

```text
views/
├── business/           # 业务管理页面
│   ├── products/       # 商品管理
│   ├── orders/         # 订单管理
│   ├── members/        # 用户管理
│   ├── coupons/        # 优惠券管理
│   ├── sites/          # 站点管理
│   └── operations/     # 运营管理
├── delivery/           # 配送管理页面
│   └── tasks/          # 配送任务
└── system/             # 系统管理页面
```

## 小程序目录结构

```text
miniapp/
├── api/                # API 接口
├── components/         # 公共组件
├── config/             # 配置文件
├── hooks/              # 组合式函数
├── pages/              # 主包页面
│   ├── index/          # 首页
│   ├── menu/           # 菜单
│   ├── cart/           # 购物车
│   ├── order/          # 订单
│   └── mine/           # 我的
├── pages/subpages/     # 分包页面
├── static/             # 静态资源
├── store/              # Pinia 状态管理
├── uni_modules/        # uni-app 插件
├── App.vue             # 应用入口
├── main.js             # 主入口
├── manifest.json       # 应用配置
├── pages.json          # 页面配置
└── uni.scss            # 全局样式变量
```

## 文档目录结构

```text
docs/
├── README.md               # 文档索引
├── localtest.md            # 本地启动与测试说明
├── admin-refactor-notes.md # 管理端结构重构说明
├── frozen-features.md      # MVP 冻结功能清单
├── mvp-admin-menu-hide.md  # 管理端菜单隐藏说明
└── project-structure.md    # 本文档
```

## 重要说明

### build 目录

`admin/build/vite` 是 Vite 配置源码，**不是构建产物**，不应被删除或排除。

### 冻结模块

以下模块已冻结，源码保留但不启用：

- score-api/score-biz（积分商城）
- express-api/express-biz（快递物流）
- mp-api/mp-biz（公众号）

### 命名约定

- 后端 Java 包名：`com.ordering`
- 后端启动类：`com.ordering.server.BackendApplication`
- 数据库表名前缀：`app_`
