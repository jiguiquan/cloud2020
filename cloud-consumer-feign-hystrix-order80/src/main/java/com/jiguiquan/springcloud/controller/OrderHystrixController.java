package com.jiguiquan.springcloud.controller;

import com.jiguiquan.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiguiquan
 * @create 2020-03-23 21:53
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        int i = 1/0;  //这条是为了强行报错，进入全局降级方法
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "我是消费者Order80，对方Payment8001系统繁忙，请10秒钟后再试 o(╥﹏╥)o";
    }

    public String payment_Global_FallbackMethod(){
        return "Global降级处理信息，请稍后再试 o(╥﹏╥)o";
    }
}
