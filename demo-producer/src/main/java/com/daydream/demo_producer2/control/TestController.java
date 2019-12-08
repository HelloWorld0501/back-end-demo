package com.daydream.demo_producer2.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/23
 * \* Time: 0:34
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "Test1";
    }
}