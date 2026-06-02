# 香农之翼后端

Spring Boot 后端服务，提供点餐、配送、用户管理等核心 API。

## 后端概览

- **Spring Boot**：3.2.2
- **Java**：17
- **Maven**：多模块项目
- **启动类**：`com.ordering.server.BackendApplication`
- **Profile**：local（本地开发）

## 目录结构

```text
backend/
├── dependencies/       # Maven 依赖版本管理（BOM）
├── framework/          # 基础框架 Starter 模块
├── server/             # Spring Boot 启动入口
├── modules/            # 业务模块
└── script/             # 辅助脚本（部署等）
```

### 目录职责说明

| 目录 | 职责 |
|------|------|
| server | 启动入口，只放启动类和全局配置 |
| dependencies | 依赖版本管理（BOM） |
| framework | 基础 Starter 封装 |
| modules | 业务模块（-api / -biz 结构） |
| script | 部署脚本等辅助工具 |

## framework 模块说明

| 模块 | 职责 |
|------|------|
| common | 通用工具、异常、基础常量 |
| starter-web | Web MVC、统一返回、全局异常 |
| starter-security | 认证鉴权 |
| starter-redis | Redis、缓存 |
| starter-mybatis | MyBatis Plus |
| starter-job | 定时任务（local 默认禁用） |
| starter-mq | 消息队列能力 |
| starter-monitor | 监控 |
| starter-websocket | WebSocket |
| starter-excel | Excel 导入导出 |
| starter-tenant | 租户能力（当前项目不作为重点） |
| starter-data-permission | 数据权限 |
| starter-ip | IP 工具 |
| starter-protection | 限流、防护等 |
| starter-test | 测试支持 |

## modules 模块说明

每个业务领域分为 `-api`（接口定义、枚举、DTO）和 `-biz`（实现、Controller、Service、Mapper）。

| 模块 | 职责 |
|------|------|
| system-api / system-biz | 用户、权限、角色、菜单、登录 |
| infra-api / infra-biz | 文件、配置、日志、代码生成、基础设施 |
| member-api / member-biz | 小程序用户、地址、会员信息 |
| product-api / product-biz | 商品、分类、规格、评价等 |
| order-api / order-biz | 订单、下单、订单状态 |
| store-api / store-biz | 门店、站点 |
| shop-api / shop-biz | 店铺运营素材、广告、服务等 |
| delivery-api / delivery-biz | 配送任务、无人机配送状态、配送进度 |
| pay-api / pay-biz | 支付能力 |
| coupon-api / coupon-biz | 优惠券 |
| score-api / score-biz | 积分商城（冻结） |
| express-api / express-biz | 快递物流（冻结） |
| mp-api / mp-biz | 公众号（冻结） |
| message-api / message-biz | 消息通知 |

### delivery 模块

- **职责**：香农之翼新增的无人机配送履约能力
- **内容**：配送任务管理、无人机状态管理、配送调度
- **状态**：当前为 MVP 阶段，部分功能使用 mock 数据

## 本地启动

### 命令行编译

```bash
cd backend
mvn -pl server -am -DskipTests clean compile
```

### IDEA 启动

1. 用 IDEA 打开 `backend/pom.xml`
2. 等待 Maven 索引完成
3. 创建 Run Configuration：
   - **Main class**：`com.ordering.server.BackendApplication`
   - **Active profile**：`local`
   - **Working directory**：`backend`
4. 运行

启动成功后访问：http://localhost:18081

## 本地配置

- **配置文件**：`server/src/main/resources/application-local.yaml`
- **端口**：18081
- **数据库**：MySQL 127.0.0.1:3306/app
- **Redis**：127.0.0.1:6379

### 数据库配置示例

```yaml
spring:
  datasource:
    dynamic:
      datasource:
        master:
          url: jdbc:mysql://127.0.0.1:3306/app
          username: root
          password: root
  data:
    redis:
      host: 127.0.0.1
      port: 6379
```

## API 端点

- **管理端 API**：`http://localhost:18081/admin-api`
- **小程序 API**：`http://localhost:18081/app-api`

## 架构原则

- 订单负责交易
- 配送任务负责履约
- 无人机状态、电量、轨迹、位置不写入订单表

## 冻结功能

以下模块已冻结，源码保留但不启用：

- 积分商城（score-api/score-biz）
- 快递物流（express-api/express-biz）
- 公众号（mp-api/mp-biz）

冻结功能不删除源码，后续如需启用只需恢复配置和菜单入口。
