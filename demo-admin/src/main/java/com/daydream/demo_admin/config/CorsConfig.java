package com.daydream.demo_admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/11
 * \* Time: 8:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 跨域配置
 * \
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许跨域的路径
        registry.addMapping("/")
                //允许跨域的源
                .allowedOrigins("*")
                //一看就懂
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                //浏览器预检查间隔时间
                .maxAge(172000)
                //允许头部设置
                .allowedHeaders("*")
                //是否发送cookie
                .allowCredentials(true);
    }
}