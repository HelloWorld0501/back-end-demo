package com.daydream.demo_producer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProducerApplication.class, args);
    }

}
