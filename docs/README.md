# 香农之翼项目文档

本目录包含项目的各类说明文档。

## 文档列表

### 开发指南

| 文档 | 说明 |
|------|------|
| [localtest.md](localtest.md) | 本地启动与测试说明 |
| [admin-refactor-notes.md](admin-refactor-notes.md) | 管理端结构重构说明 |
| [frozen-features.md](frozen-features.md) | MVP 冻结功能清单 |
| [mvp-admin-menu-hide.md](mvp-admin-menu-hide.md) | 管理端菜单隐藏说明 |

### 项目结构

| 文档 | 说明 |
|------|------|
| [project-structure.md](project-structure.md) | 项目目录结构说明 |

## 快速导航

### 新手入门

1. 先阅读 [项目根目录 README](../README.md) 了解项目概况
2. 阅读 [localtest.md](localtest.md) 配置本地环境
3. 按顺序启动 MySQL、Redis、后端、管理端

### 功能冻结

- 了解哪些功能被冻结：[frozen-features.md](frozen-features.md)
- 了解菜单隐藏策略：[mvp-admin-menu-hide.md](mvp-admin-menu-hide.md)

### 代码重构

- 管理端重构说明：[admin-refactor-notes.md](admin-refactor-notes.md)

## 文档维护

### 更新原则

1. 文档应与代码同步更新
2. 重要变更需记录在相关文档中
3. 冻结功能状态变更需更新 frozen-features.md

### 文档格式

- 使用 Markdown 格式
- 使用中文编写
- 代码块指定语言
- 表格使用标准格式

## 注意事项

1. 本地配置（数据库密码等）不要提交到仓库
2. 文档中的示例配置仅供参考，实际配置以本地环境为准
3. 冻结功能不要随意删除，详见 frozen-features.md
