# 香农之翼小程序端

面向用户的点单、下单、订单查看、配送进度查看端。

## 技术栈

- uni-app
- Vue3
- HBuilderX

## 启动方式

1. 用 HBuilderX 打开 `miniapp`
2. 检查 `manifest.json` AppID
3. 检查 `config/index.js` API 地址

## API 地址

http://localhost:18081/app-api

## 当前结构

- `pages/subpages` 是小程序二级页面目录
- 原 `pages/components/pages` 已重命名为 `pages/subpages`

## MVP 策略

- 首页、点餐、购物车、订单、我的保留
- 积分商城、余额、充值、会员卡、公众号、收银台等入口先隐藏
- 不删除页面
- 后续用配置开关或入口恢复

## 功能开关

`miniapp/config/features.js` 控制 MVP 阶段入口显示。

冻结功能不删除页面和接口，只隐藏入口。

后续恢复积分、余额、会员、多门店等功能时，优先检查 FEATURES 配置。

"我的服务"这类后端动态入口会根据 FEATURES 做前端兜底过滤，避免冻结功能在 MVP 阶段误展示。

## API 文件命名

`miniapp/api` 已按业务含义整理：

- products.js：商品/点餐商品
- orders.js：订单
- payment.js：支付
- sites.js：站点/门店列表
- ads.js：首页广告
- account.js：用户信息、我的服务、余额/充值冻结能力
- coupons.js：优惠券
- points.js：积分商城冻结能力
- address.js：地址
- auth.js：登录鉴权

本轮只改文件名和 import，接口 URL 与函数名保持兼容。

## 注意

- 不要修改 `uni_modules` 下第三方组件 README
- 不要删除冻结功能页面
