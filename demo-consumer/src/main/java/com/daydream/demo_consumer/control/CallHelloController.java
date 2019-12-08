package com.daydream.demo_consumer.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/23
 * \* Time: 3:00
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class CallHelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/call")
    public String call() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("demo-producer");
        System.out.println("服务的地址: " + serviceInstance.getUri());
        System.out.println("服务名: " + serviceInstance.getServiceId());
        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/test", String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }
}