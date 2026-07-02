# 项目当前状态

本文档记录香农之翼项目的当前开发状态、已完成内容、未完成内容及重要约束。

## 1. 项目来源

本项目基于 **yshop-drink** 开源项目进行二次开发，面向校园/园区场景的点餐与无人机配送管理。

**核心改造方向：**
- 保留原有点餐业务能力
- 新增无人机配送履约模块（delivery）
- 调整管理端结构以适配新业务

## 2. 顶层结构

```text
<project-root>/
├── backend/       # Spring Boot 后端服务
├── admin/         # Vue3 后台管理端
├── miniapp/       # uni-app 微信小程序端
├── docs/          # 项目文档
├── assets/        # 本地素材目录（不提交 GitHub）
└── package.ps1    # 打包上传脚本
```

## 3. 已完成内容

### 顶层目录整理
- 清理根目录临时迁移脚本
- 统一 README、docs 文档格式
- 修复 .gitignore 和 package.ps1 排除规则

### 管理端结构重构
- `admin/src/api/mall` → `admin/src/api/business`
- `admin/src/views/mall` → `admin/src/views/business`
- 商品、订单、用户、优惠券、站点等模块路径收敛
- 动态菜单兼容旧 component 路径

### delivery 模块 MVP
- 新增 `admin/src/views/delivery` 配送管理页面
- 新增 `admin/src/api/delivery/index.ts` 配送 API 层
- 后端新增 delivery mock API（`/delivery/tasks`、`/delivery/drones`）
- 配送任务列表、筛选、详情抽屉
- 无人机状态展示

### 小程序配送进度
- 订单详情页展示配送进度

## 4. 未完成内容

### delivery 数据层
- delivery 数据库表设计
- 配送任务真实创建（与订单关联）
- 任务状态持久化

### 无人机真实接入
- 无人机设备对接
- 实时状态上报
- 电量、轨迹、位置数据

### 调度算法
- 自动任务分配
- 路径规划
- 多无人机调度

### 生产环境
- 生产环境配置
- 安全加固
- 监控告警

### 测试
- 自动化测试
- 接口测试
- 前端组件测试

## 5. 重要约束

### 架构原则
- **订单负责交易**：下单、支付、退款等交易行为
- **配送任务负责履约**：配送、到达、完成等履约行为
- **无人机状态不写入订单表**：电量、轨迹、位置等属于独立模型

### 功能冻结策略
- 旧 yshop-drink 功能暂时冻结，不直接删除
- 源码保留，数据库表保留，菜单隐藏
- 后续可通过恢复菜单入口启用

**当前冻结功能：**
- 积分商城（score）
- 余额充值/账单
- 会员卡
- 微信公众号（mp）
- 快递物流（express）
- 收银台/扫码枪
- 桌台点餐
- 多门店切换（冻结为单店模式）
- 多租户/SaaS（冻结为单租户模式）

### delivery 模块状态
- 当前 delivery 管理端页面请求后端 delivery mock API
- 后端数据由 `DeliveryMockDataProvider` 提供
- 尚未接入真实数据库和无人机系统
- 前端 `mock.ts` 仅作为离线演示备用

### 开发约定
- 不提交 `node_modules`、`target`、`dist`、`unpackage`
- admin 依赖安装和运行在同一环境（避免 Windows/WSL 混用）
- 不直接修改 `version1` 主分支
- 冻结功能不删除源码

## 6. 下一步优先级

1. delivery 数据库表设计与实现
2. 配送任务创建与订单关联
3. 管理端订单页增加跳转配送任务入口
4. 小程序订单详情配送进度细化
5. 清理冻结功能的残留入口和说明

## 7. 文档索引

| 文档 | 说明 |
|------|------|
| [README.md](../README.md) | 项目总览 |
| [project-structure.md](project-structure.md) | 目录结构说明 |
| [localtest.md](localtest.md) | 本地启动说明 |
| [frozen-features.md](frozen-features.md) | 冻结功能清单 |
| [admin-refactor-notes.md](admin-refactor-notes.md) | 管理端重构说明 |
| [mvp-admin-menu-hide.md](mvp-admin-menu-hide.md) | 菜单隐藏说明 |