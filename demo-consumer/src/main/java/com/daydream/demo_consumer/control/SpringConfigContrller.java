package com.daydream.demo_consumer.control;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/12/6
 * \* Time: 22:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RefreshScope
@RestController
public class SpringConfigContrller {
    @Value("${hello}")
    private String hello;

    @RequestMapping("/hello")
    public String text() {
        return this.hello;
    }
}