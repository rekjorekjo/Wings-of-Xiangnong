# 管理端结构重构说明

## 1. 当前管理端目录状态

**核心目录：**

- `admin/src/api/business` - 商品、订单、用户、优惠券、站点等原点餐业务 API
- `admin/src/views/business` - 商品、订单、用户、优惠券、站点等原点餐业务管理页面
- `admin/src/views/delivery` - 香农之翼新增配送管理 MVP 页面

**职责划分：**
- `business`：原 app 点餐业务管理模块
- `delivery`：香农之翼新增无人机配送管理

## 2. 已完成的重命名

**目录重命名：**

| 旧路径 | 新路径 |
|--------|--------|
| `admin/src/api/mall` | `admin/src/api/business` |
| `admin/src/views/mall` | `admin/src/views/business` |
| `business/product/storeProduct` | `business/products/items` |
| `business/order/storeOrder` | `business/orders` |
| `business/store/shop` | `business/sites` |
| `business/coupon` | `business/coupons` |
| `business/member/user` | `business/members/users` |
| `business/shop/ads` | `business/operations/ads` |
| `business/shop/recharge` | `business/operations/recharge` |
| `business/shop/service` | `business/operations/services` |
| `business/shop/storeProductRule` | `business/products/rules` |

**API 层重命名：**

| 旧路径 | 新路径 |
|--------|--------|
| `products/storeProductRelation` | `products/relations` |
| `products/storeProductReply` | `products/reviews` |
| `productRules` | `products/rules` |

## 3. 动态菜单兼容

后端数据库 `system_menu.component` 可能仍然返回旧路径，例如：

```
mall/product/storeProduct/index
mall/order/storeOrder/index
mall/coupon/index
```

前端通过 `admin/src/utils/legacyComponentPath.ts` 把旧 component 映射到新 views/business 路径。

**重要：**
- 这只影响前端组件解析
- 不改菜单 URL path
- 不改数据库
- 不改权限逻辑

## 4. 为什么保留旧 API alias

`admin/src/api/business` 中保留旧函数和旧类型 alias，例如：

```typescript
// 函数 alias
export const getStoreOrderPage = getOrderPage
export const getShopList = getSiteList

// 类型 alias
export type StoreOrderVO = OrderVO
export type ShopVO = SiteVO
```

**原因：**
- 降低一次性迁移风险
- 保持旧调用方兼容
- 后续逐步迁移，不一次性删除

**禁止：** 不要在没有全量搜索和验证前删除旧 alias。

## 5. shop/store 命名暂时保留的原因

虽然展示层叫"站点"，但底层仍然存在：

- `shopId` 字段
- `storeId` 字段
- `getShopList` 函数 alias
- `ShopVO` 类型 alias
- `store/shop` 历史接口路径

**原因：**
- 商品、订单、优惠券、配送费等可能依赖 shop/store 字段
- 数据库字段未改
- 后端接口未改
- 现在只做前端结构收敛，不做数据库/后端字段重命名

**禁止：** 不要把 `shopId`/`storeId` 机械替换成 `siteId`。

## 6. delivery 模块当前状态

**当前管理端已有：**

- `admin/src/views/delivery` - 配送管理页面
- `admin/src/api/delivery` - 配送 API 层

**能力：**
- 无人机状态 mock
- 配送任务 mock
- 筛选功能
- 详情抽屉
- 本地状态流转
- URL query 筛选

**当前仍是前端 mock。**

后续接 backend delivery API 时优先替换 `admin/src/api/delivery/index.ts`，页面层不要直接依赖 `mock.ts`。

## 7. 后续改动原则

**允许：**
- 可以继续清理 admin 内部命名
- 可以优化前端代码结构

**禁止：**
- 不要直接删除旧功能模块
- 不要删除积分、余额、会员、公众号等旧功能源码
- 不要删除数据库表
- 不要改 `system_menu` 数据，除非单独任务明确要求
- 后端 `app`/`com.ordering` 命名暂时保留

**设计原则：**
- 订单负责交易
- 配送任务负责履约
- 无人机状态不要塞进订单表

## 8. 下一步建议

1. 继续清理 admin 中 `score`/`mp`/`express` 等冻结功能的入口和说明
2. 为 delivery 设计后端独立模块
3. 小程序订单详情接配送进度
4. 管理端订单页增加跳转配送任务的入口
5. 梳理哪些旧菜单是隐藏而不是删除
