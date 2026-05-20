

## 平台简介

香农之翼 - 东南大学九龙湖校区 DC 香农咖啡无人机配送系统 MVP。

本项目基于 yshop-drink 点餐系统改造，原项目地址：https://www.yixiang.co/

采用前后端分离架构：Spring Boot 3 + Vue3 + uni-app，支持 H5 和微信小程序。

## 项目说明

```
    backend             Java 后端工程 (Spring Boot 3)
    admin               管理平台前端 (Vue3)
    miniapp             小程序端 (uni-app + Vue3)
```

## 本地快速启动

##### 1、环境要求

```
    jdk17
    mysql8
    redis6+
    node16+
    maven3.8+
```

##### 2、开发工具

```
    idea
    vscode
    hbuilder
```

##### 3、后端启动

-   3.1 请使用idea打开Java工程，自动会安装依赖
-   3.2 创建数据库且导入工程目录下sql/yixiang-drink.sql 文件
-   3.3 找到项目下的yshop-server 的yml,修改数据库相关信息和redis相关信息
-   3.4 工程下输入
    ``` 
    mvn clean install package '-Dmaven.test.skip=true
    ```
-   3.5 启动项目

##### 4、管理端启动

-   4.1 vscode 打开 admin 目录，输入命令: 
    ``` 
    pnpm install
    ```
-   4.2 配置 .env.dev 中的 API 地址
-   4.3 本地启动:
    ```
    npm run dev
    ```

##### 5、小程序端启动

-   5.1 hbuilder 导入 miniapp 项目
-   5.2 配置 config/index.js 中的 API 地址
-   5.3 配置 manifest.json 中的小程序 appid
-   5.4 运行小程序或 H5

## 技术栈

- Spring Boot 3
- Spring Security OAuth2
- MyBatis Plus
- Redis
- Vue3
- Element Plus
- uni-app (Vue3)

## 特别鸣谢

- yshop-drink: https://www.yixiang.co/
- ruoyi-vue-pro: https://gitee.com/zhijiantianya/ruoyi-vue-pro
- element-plus: https://element-plus.gitee.io/zh-CN/
- vue: https://cn.vuejs.org/
- uniapp: https://uniapp.dcloud.net.cn/

## 开源协议

本项目采用比 Apache 2.0 更宽松的 [MIT License](https://gitee.com/guchengwuyue/yshop-drink/blob/master/LICENSE) 开源协议，个人与企业可 100% 免费使用，不用保留类作者、Copyright 信息。
