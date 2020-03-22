package com.jiguiquan.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author jiguiquan
 * @create 2020-03-22 22:10
 */
@Configuration
public class FeignConfig {
    //注意这里使用的Logger是 feign.Logger
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
