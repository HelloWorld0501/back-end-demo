package com.daydream.demo_consumer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/12/8
 * \* Time: 0:08
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 用于解决No bean named 'configServerRetryInterceptor' available的坑 配合META-INF/spring.factories使用
 * \
 */

import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
        import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
        import org.springframework.context.annotation.Bean;
        import org.springframework.retry.interceptor.RetryOperationsInterceptor;

public class RetryConfiguration {
    /**
     * 解决No bean named 'configServerRetryInterceptor' available
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "configServerRetryInterceptor")
    public RetryOperationsInterceptor configServerRetryInterceptor() {
        return RetryInterceptorBuilder
                .stateless()
                .backOffOptions(1000,1.2,5000)
                .maxAttempts(10)
                .build();
    }

}