package com.daydream.demo_consumer.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/25
 * \* Time: 2:44
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class DemoConsumerHystrix implements DemoProducerService {
    @Override
    @RequestMapping("/test")
    public String test() {
        return "服务调用失败!";
    }
}