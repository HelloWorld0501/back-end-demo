package com.daydream.demo_consumer.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/23
 * \* Time: 1:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class ServiceController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有服务
     *
     * @return
     */
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getInstances("demo-producer");
    }

    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancerClient.choose("demo-producer").getUri().toString();
    }

}