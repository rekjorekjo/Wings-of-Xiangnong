# 香农之翼管理端

Vue3 后台管理系统，用于商品管理、订单管理、配送任务管理等。

## 技术栈

- Vue 3
- TypeScript
- Element Plus
- Vite
- Pinia
- pnpm

## 重要说明

**必须在 Windows PowerShell 中安装和运行依赖，不要混用 WSL 和 Windows node_modules。**

如果混用会导致 Rollup 平台包缺失等问题。

## 本地启动

```bash
cd admin
pnpm install
pnpm dev
```

启动后访问：http://localhost:80（如端口被占用会自动切到 81）

### 构建生产版本

```bash
pnpm build
```

## 目录结构

```text
admin/
├── build/              # 构建配置
│   └── vite/           # Vite 插件配置（源码，不能删除）
├── public/             # 静态资源
│   └── UEditor/        # 旧富文本编辑器资源（当前先保留）
├── src/
│   ├── api/            # API 接口封装
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

## views 页面结构

```text
views/
├── Home/               # 首页
├── Login/              # 登录
├── Profile/            # 个人中心
├── business/           # 运营业务页面
│   ├── products/       # 商品管理
│   ├── orders/         # 订单管理
│   ├── members/        # 用户管理
│   ├── coupons/        # 优惠券管理
│   ├── sites/          # 站点管理
│   └── operations/     # 运营管理（广告、充值、服务）
├── delivery/           # 配送调度页面
│   ├── components/     # 配送相关组件
│   └── composables/    # 配送相关组合式函数
├── system/             # 系统设置
│   ├── menu/           # 菜单管理
│   ├── role/           # 角色管理
│   ├── dept/           # 部门管理
│   └── dict/           # 字典管理
├── infra/              # 基础设施与开发维护（部分功能冻结）
│   ├── codegen/        # 代码生成
│   ├── config/         # 配置管理
│   ├── file/           # 文件管理
│   └── job/            # 定时任务
├── express/            # 快递物流（冻结）
├── score/              # 积分商城（冻结）
├── mp/                 # 公众号（冻结）
└── message/            # 消息通知
```

## 环境配置

### 开发环境 (.env.dev)

```text
VITE_BASE_URL='http://localhost:18081'
VITE_API_URL=/admin-api
VITE_APP_CAPTCHA_ENABLE=false
```

### 生产环境 (.env.prod)

根据实际部署地址配置。

## 主要功能

### 运营中心

- **商品管理**：商品列表、分类、规格
- **订单管理**：订单列表、订单详情、订单状态
- **用户管理**：用户列表、用户详情
- **优惠券管理**：优惠券列表、发放
- **站点管理**：站点/店铺信息

### 配送调度

- **配送任务**：配送任务列表、状态跟踪
- **无人机状态**：无人机状态展示（MVP 阶段）

### 系统设置

- **用户管理**：管理员账号
- **角色管理**：角色权限
- **菜单管理**：菜单配置
- **字典管理**：数据字典

## 开发约定

1. **API 层**：所有接口请求放在 `src/api/` 下
2. **页面组件**：页面放在 `src/views/` 下，按业务模块分组
3. **公共组件**：可复用组件放在 `src/components/` 下
4. **状态管理**：全局状态使用 Pinia，放在 `src/store/` 下

## 常见问题

### Rollup missing @rollup/rollup-linux-x64-gnu

**原因**：Windows / WSL 混用 node_modules 导致平台包不匹配。

**解决**：删除 node_modules 后在当前运行环境重新 `pnpm install`。

### 端口 80 被占用

admin 会自动尝试 81，访问 http://localhost:81/。

## 冻结功能

以下功能已冻结，入口已隐藏：

- 积分商城（score）
- 余额充值
- 会员卡
- 公众号管理（mp）
- 快递物流（express）

冻结功能不删除源码，后续如需启用只需恢复菜单入口。

## 注意事项

1. `build/vite` 是 Vite 构建配置源码，不能删除，不能被 package.ps1 排除
2. `public/UEditor` 是旧富文本编辑器资源，当前先保留
