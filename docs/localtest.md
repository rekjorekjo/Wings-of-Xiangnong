# 本地启动与测试说明

这份文档用于从“本机服务都没启动”的状态，把香农之翼本地环境跑起来，并做一轮基础检查。



[TOC]



当前项目目录：

```text
Wings/
├── backend   # Spring Boot 后端
├── admin     # Vue3 管理端
├── miniapp   # uni-app 小程序端
└── docs      # 项目文档
```

---

## 1. 本地环境约定

| 项目       | 当前约定                           |
| ---------- | ---------------------------------- |
| JDK        | 17                                 |
| Maven      | 3.8+                               |
| MySQL      | 8.x                                |
| Redis      | 127.0.0.1:6379                     |
| Node       | 20                                 |
| pnpm       | 10                                 |
| 数据库名   | `wings`               |
| 后端端口   | `18081`                            |
| 管理端端口 | `80`                               |
| 管理端地址 | `http://localhost:80`              |
| 后端地址   | `http://localhost:18081`           |
| 管理端 API | `http://localhost:18081/admin-api` |
| 小程序 API | `http://localhost:18081/app-api`   |

后端本地配置文件：

```text
backend/server/src/main/resources/application-local.yaml
```

当前数据库连接配置大致是：

```yaml
spring:
  datasource:
    dynamic:
      datasource:
        master:
          url: jdbc:mysql://127.0.0.1:3306/wings
          username: root
          password: root
  data:
    redis:
      host: 127.0.0.1
      port: 6379
      database: 0
```

如果你的 MySQL 用户名或密码不是 `root/root`，先改本机配置，不要提交个人密码。

---

## 2. 推荐启动顺序

按这个顺序来，出错时好定位：

```text
1. 启动 MySQL
2. 启动 Redis
3. 编译/启动 backend
4. 启动 admin
5. 登录管理端做页面检查
6. 需要时再打开 miniapp
```

---

## 3. 启动 MySQL

项目需要本地 MySQL 8，数据库名：

```text
wings
```

### 3.1 方式 A：MySQL 已安装成 Windows 服务

先查看服务名：

```powershell
Get-Service *mysql*
```

常见服务名可能是：

```text
MySQL
MySQL80
```

启动服务：

```powershell
net start MySQL80
```

或：

```powershell
net start mysql
```

如果提示服务已经启动，可以继续下一步。

检查 3306 端口：

```powershell
Test-NetConnection 127.0.0.1 -Port 3306
```

看到 `TcpTestSucceeded : True` 表示端口可连接。

### 3.2 方式 B：MySQL 是 zip 版，前台启动

进入你的 MySQL 安装目录。路径按你自己机器实际情况改：

```powershell
cd F:\mysql-8.x.x-winx64\bin
.\mysqld --console
```

这个窗口不要关。关掉窗口 MySQL 就停了。

如果你使用了自定义 `my.ini`，可以这样启动：

```powershell
.\mysqld --defaults-file=F:\mysql-8.x.x-winx64\my.ini --console
```

### 3.3 检查数据库

新开一个 PowerShell，连接 MySQL：

```powershell
mysql -u root -p
```

进入后执行：

```sql
SHOW DATABASES;
USE wings;
SHOW TABLES;
```

如果 `USE wings;` 报数据库不存在，说明还没导入数据库。

本项目当前不强制提交 `.sql` 文件，因为 `.gitignore` 忽略 `*.sql`，避免误提交数据库 dump 或敏感 SQL。数据库 dump 需要从本地已有备份或负责人提供的初始化文件导入。

---

## 4. 启动 Redis

后端默认连接：

```text
127.0.0.1:6379
```

### 4.1 方式 A：WSL 里直接启动 Redis

进入 WSL：

```powershell
wsl
```

启动 Redis：

```bash
redis-server
```

这个终端不要关。关掉 Redis 就停了。

新开一个 WSL 终端检查：

```bash
redis-cli ping
```

期望返回：

```text
PONG
```

### 4.2 方式 B：WSL Redis 服务方式启动

如果 Redis 安装成了 WSL 服务：

```bash
sudo service redis-server start
```

检查：

```bash
redis-cli ping
```

期望返回：

```text
PONG
```

### 4.3 Windows 侧检查 Redis 端口

在 Windows PowerShell 检查：

```powershell
Test-NetConnection 127.0.0.1 -Port 6379
```

看到 `TcpTestSucceeded : True` 表示 Windows 能连到 Redis。

如果 Windows 里装了 `redis-cli`，也可以直接：

```powershell
redis-cli ping
```

期望返回：

```text
PONG
```

---

## 5. 编译和启动后端

### 5.1 编译后端

```powershell
cd F:\projects\Wings\backend
mvn clean install -DskipTests
```

这里使用 `-DskipTests` 是正常的。当前测试用例可能卡住或依赖不完整，不作为本地启动前置条件。

### 5.2 用 IDEA 启动后端

推荐用 IDEA 启动。打开或重新导入：

```text
backend/pom.xml
```

启动类：

```text
backend/server/src/main/java/co/wings/wings/server/WingsServerApplication.java
```

主类名：

```text
com.wings.server.WingsServerApplication
```

Run Configuration 里注意：

```text
Use classpath of module: wings-server
```

启动成功后，后端地址是：

```text
http://localhost:18081
```

浏览器访问：

```text
http://localhost:18081
```

返回 `401` 是正常的，说明服务启动了，鉴权也生效了。

### 5.3 如果 IDEA 找不到主类

常见错误：

```text
ClassNotFoundException: com.wings.server.WingsServerApplication
```

处理方式：

```text
1. 删除旧 Run Configuration
2. 重新导入 backend/pom.xml
3. 等 Maven 重新索引
4. 右键 WingsServerApplication.java 启动
5. 确认 Use classpath of module 是 wings-server
```

---

## 6. 启动管理端 admin

```powershell
cd F:\projects\Wings\admin
pnpm install
pnpm dev
```

管理端地址：

```text
http://localhost:80
```

关键配置在：

```text
admin/.env.dev
```

应包含：

```text
VITE_BASE_URL='http://localhost:18081'
VITE_API_URL=/admin-api
VITE_APP_CAPTCHA_ENABLE=false
```

### 6.1 admin 依赖缺失

如果报 `esbuild` 或其它依赖找不到：

```powershell
cd F:\projects\Wings\admin
pnpm install
pnpm dev
```

如果仍然缺 `esbuild`：

```powershell
pnpm add -D esbuild
pnpm dev
```

如果 Element Plus 组件缺失：

```powershell
pnpm add element-plus@latest
pnpm dev
```

### 6.2 端口 80 被占用

检查占用：

```powershell
netstat -ano | findstr :80
```

看到 PID 后，可以在任务管理器里确认进程。必要时结束进程：

```powershell
taskkill /PID <PID> /F
```

不要为了临时测试随便提交端口改动。

---

## 7. 启动小程序 miniapp

小程序端当前不是 admin 重构的必测项，但后续联调下单链路需要它。

使用 HBuilderX 打开：

```text
miniapp
```

检查配置：

```text
miniapp/config/index.js
```

本地 API 应指向：

```text
http://localhost:18081/app-api
```

如果小程序端请求失败，先确认 backend 已启动，并且 `http://localhost:18081` 返回 401。

---

## 8. 本轮重点测试页面

这几轮主要改的是 admin 的结构、命名和 delivery 页面，所以优先测管理端。

登录 admin 后按顺序检查：

```text
1. 首页
2. 商品管理
3. 商品分类
4. 订单管理
5. 用户管理
6. 优惠券
7. 站点/店铺相关页面
8. 配送管理 -> 配送任务
```

重点观察：

```text
1. 页面是否白屏
2. 控制台是否有 module not found
3. 动态菜单是否能打开旧 business 页面
4. 配送任务页是否能打开 /delivery/tasks
5. 商品、订单页面是否因为路径重命名打不开
```

如果某些菜单被隐藏，不要为了测试临时改菜单。记录“未验证，菜单隐藏”即可。

---

## 9. 可选构建测试

admin 能启动后，可以试：

```powershell
cd F:\projects\Wings\admin
pnpm build
```

如果 build 失败，先记录第一段错误，不要直接大改。  
很多 build 问题可能是旧项目遗留的类型或依赖问题，需要单独判断。

---

## 10. 常见问题速查

### 10.1 后端根路径返回 401

正常。说明后端服务已启动，鉴权生效。

### 10.2 Maven 测试卡住

使用：

```powershell
mvn clean install -DskipTests
```

### 10.3 MySQL 连接失败

检查：

```powershell
Test-NetConnection 127.0.0.1 -Port 3306
mysql -u root -p
```

确认：

```text
1. MySQL 已启动
2. 数据库 wings 存在
3. application-local.yaml 的 username/password 和本机一致
```

### 10.4 Redis 连接失败

检查：

```powershell
Test-NetConnection 127.0.0.1 -Port 6379
```

WSL 中检查：

```bash
redis-cli ping
```

应返回：

```text
PONG
```

### 10.5 后端 18081 启动失败

检查端口占用：

```powershell
netstat -ano | findstr :18081
```

如果被占用，先确认是不是旧 Java 进程。必要时结束旧进程：

```powershell
taskkill /PID <PID> /F
```

### 10.6 admin 登录接口失败

先确认后端：

```text
http://localhost:18081
```

能返回 401。

再确认 `admin/.env.dev`：

```text
VITE_BASE_URL='http://localhost:18081'
VITE_API_URL=/admin-api
```

### 10.7 IDEA 启动类找不到

重新导入：

```text
backend/pom.xml
```

然后重新创建 Run Configuration。  
不要沿用目录重命名前的旧启动配置。

### 10.8 pnpm dev 没加载 .env.dev

当前 `admin/package.json` 的 dev 脚本应使用：

```text
vite --mode dev
```

启动命令：

```powershell
pnpm dev
```

---

## 11. 当前不要做的事

本地测试时不要顺手做这些：

```text
1. 不要删除冻结功能模块
2. 不要删除数据库表
3. 不要删除 system_menu
4. 不要修改 shopId / storeId
5. 不要把无人机状态塞进订单表
6. 不要为了测试随手改业务代码
7. 不要提交本地数据库 dump
8. 不要提交个人环境密码
```

---

## 12. 报错时怎么贴给我

报错时直接贴这些信息：

```text
1. 当前启动的是 MySQL / Redis / backend / admin / miniapp 哪一步
2. 执行的命令
3. 第一段完整报错
4. 如果是 admin 页面问题，贴页面路径和浏览器控制台第一段错误
5. 如果是后端问题，贴启动日志里第一个 Caused by
```

不要贴整屏重复日志。第一段错误通常最有用。

