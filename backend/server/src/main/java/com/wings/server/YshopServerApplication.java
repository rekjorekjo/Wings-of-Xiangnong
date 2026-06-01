package com.wings.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 * @author wings
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${wings.info.base-package}
@SpringBootApplication(scanBasePackages = {"${wings.info.base-package}.server", "${wings.info.base-package}.module"})
public class WingsServerApplication {

    public static void main(String[] args){

        SpringApplication.run(WingsServerApplication.class, args);


    }

}
