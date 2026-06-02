package com.ordering.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 * @author project team
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${app.info.base-package}
@SpringBootApplication(scanBasePackages = {"${app.info.base-package}.server", "${app.info.base-package}.module"})
public class BackendApplication {

    public static void main(String[] args){

        SpringApplication.run(BackendApplication.class, args);


    }

}
