package com.jiguiquan.springcloud;

import com.jiguiquan.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author jiguiquan
 * @create 2020-03-07 13:55
 */
@SpringBootApplication
@EnableEurekaClient
//即MySelfRule策略只对CLOUD-PAYMENT-SERVICE服务的调用生效
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
