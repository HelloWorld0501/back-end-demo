package com.daydream.demo_consumer.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/23
 * \* Time: 3:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class RibbonHelloController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 默认轮询
     *
     * @return
     */
    @RequestMapping("/ribbon/call")
    public String call() {
        String callServiceResult = restTemplate.getForObject("http://demo-producer/test", String.class);
        return callServiceResult;
    }
}