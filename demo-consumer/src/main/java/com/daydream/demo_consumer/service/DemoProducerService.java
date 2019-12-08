package com.daydream.demo_consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/24
 * \* Time: 1:55
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@FeignClient(name = "demo-producer", fallback = DemoConsumerHystrix.class)
public interface DemoProducerService {
    @RequestMapping("/test")
    public String test();
}