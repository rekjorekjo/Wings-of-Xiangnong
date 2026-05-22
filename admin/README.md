# 香农之翼管理端

香农之翼管理端，当前第一阶段主线。

## 技术栈

- Vue3
- Vite
- TypeScript
- Element Plus
- pnpm

## 启动方式

```bash
cd admin
pnpm install
pnpm dev
```

## 本地端口

http://localhost:80

## API 地址

`.env.dev` 中：

- `VITE_BASE_URL=http://localhost:18081`
- `VITE_API_URL=/admin-api`

## 当前结构改造进度

- `src/api/mall` 已改为 `src/api/business`
- `src/views/mall` 已改为 `src/views/business`
- `routerHelper` 兼容旧菜单 component 路径 `mall/...` 到 `business/...`
- delivery 管理页面已新增
- 管理端标题已改为香农之翼管理端

### 旧菜单 component 路径兼容

- 后端菜单 component 可能仍返回旧路径，例如 `mall/...`
- 前端通过 `src/utils/legacyComponentPath.ts` 做兼容映射
- 这只是前端组件解析兼容，不修改数据库菜单 path

## 重要边界

- 不直接改数据库 `system_menu`
- 不删除旧页面和旧功能
- 通过菜单隐藏/入口隐藏/配置开关控制 MVP 范围
- `views/business` 的 URL path 可能仍由后端菜单返回，不要随便改菜单 path

## 冻结功能说明

积分、余额、会员卡、公众号、收银台、多门店等功能当前不作为 MVP 主链路，但源码和模块保留。管理端通过菜单隐藏控制入口，详见 [docs/frozen-features.md](../docs/frozen-features.md)。

## delivery 页面说明

路径：`/delivery/tasks`

当前能力：

- 无人机状态 mock
- 配送任务 mock
- 筛选
- 详情抽屉
- 本地状态流转
- URL query 筛选

说明：当前是前端 mock，后续接 backend delivery API。
