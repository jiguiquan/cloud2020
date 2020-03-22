package com.jiguiquan.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author jiguiquan
 * @create 2020-03-07 14:02
 */
@Configuration
public class ApplicationContextConfig {
    //@LoadBalanced注解赋予RestTemplate一个默认的负载均衡能力
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
