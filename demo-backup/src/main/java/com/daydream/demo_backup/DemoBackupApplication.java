package com.daydream.demo_backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/19
 * \* Time: 0:47
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.daydream.demo_backup"})
public class DemoBackupApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoBackupApplication.class, args);
    }
}