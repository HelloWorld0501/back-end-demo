package com.daydream.demo_consumer.control;

import com.daydream.demo_consumer.service.DemoProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/24
 * \* Time: 1:58
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class FeignHelloController {
    @Autowired
    private DemoProducerService demoProducerService;

    @RequestMapping("/feign/call")
    public String call() {
        return demoProducerService.test();
    }
}