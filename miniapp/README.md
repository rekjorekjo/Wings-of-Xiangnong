# 香农之翼小程序端

uni-app 微信小程序，用于用户点餐、订单查看、配送跟踪等。

## 技术栈

- uni-app
- Vue 3
- Pinia
- uni-ui / uv-ui

## 本地开发

### 使用微信开发者工具

1. 下载并安装 [微信开发者工具](https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html)
2. 使用微信开发者工具打开 `miniapp` 目录
3. 确认 `config/index.js` 中 API 指向 `http://localhost:18081/app-api`

### 使用 HBuilderX

1. 下载并安装 [HBuilderX](https://www.dcloud.io/hbuilderx.html)
2. 打开 HBuilderX，导入 `miniapp` 目录
3. 运行 -> 运行到小程序模拟器 -> 微信开发者工具

## 目录结构

```text
miniapp/
├── api/                # API 接口封装
├── components/         # 公共组件
├── config/             # 配置文件
│   ├── index.js        # 主配置（API 地址等）
│   ├── routes.js       # 路由配置
│   └── features.js     # 功能开关
├── hooks/              # 组合式函数
├── pages/              # 主包页面
│   ├── index/          # 首页
│   ├── menu/           # 菜单
│   ├── cart/           # 购物车
│   ├── order/          # 订单
│   └── mine/           # 我的
├── pages/subpages/     # 分包页面
│   ├── address/        # 地址管理
│   ├── orders/         # 订单详情
│   ├── pay/            # 支付
│   └── shop/           # 店铺
├── static/             # 静态资源
├── store/              # Pinia 状态管理
├── uni_modules/        # uni-app / uv-ui 组件依赖（不要手动乱删）
├── utils/              # 工具函数
├── App.vue             # 应用入口
├── main.js             # 主入口
├── manifest.json       # 应用配置
├── pages.json          # 页面配置
└── uni.scss            # 全局样式变量
```

## 本地接口配置

修改 `config/index.js`：

```javascript
export default {
  // 本地开发
  baseUrl: 'http://localhost:18081/app-api',
  
  // 生产环境
  // baseUrl: 'https://your-domain.com/app-api',
}
```

## 主要功能

### 首页

- 商品展示
- 分类导航
- 活动入口

### 菜单

- 商品列表
- 商品分类
- 商品详情

### 购物车

- 添加商品
- 修改数量
- 结算

### 订单

- 订单列表
- 订单详情
- 订单状态
- 配送跟踪（MVP 阶段）

### 我的

- 个人信息
- 收货地址
- 优惠券
- 余额（冻结）

## 开发约定

1. **API 层**：所有接口请求放在 `api/` 下
2. **页面**：主包页面放 `pages/`，分包页面放 `pages/subpages/`
3. **组件**：可复用组件放 `components/`
4. **状态管理**：全局状态使用 Pinia，放 `store/`
5. **工具函数**：放 `utils/`

## 常见问题

### 小程序 auth-session 登录失败

**原因**：测试号或 AppID/secret 不匹配会导致真实微信登录失败。

**解决**：local 阶段后续可使用 mock login。

## 冻结功能

以下功能已冻结，入口已隐藏：

- 积分商城
- 余额充值
- 会员卡

冻结功能不删除源码，后续如需启用只需恢复入口。

## 注意事项

1. `uni_modules` 是 uni-app / uv-ui 组件依赖，不要手动乱删
2. 小程序测试号可能无法完成真实微信登录，local 阶段可后续使用 mock login
