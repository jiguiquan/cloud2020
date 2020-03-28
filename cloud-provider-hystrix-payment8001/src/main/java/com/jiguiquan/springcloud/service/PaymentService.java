package com.jiguiquan.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author jiguiquan
 * @create 2020-03-23 21:24
 */
@Service
public class PaymentService {

    //正常访问用的方法
    public String paymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " PaymentInfo_OK, id：" + id;
    }

    //指定服务降级兜底方法，并传参自身调用超时时间峰值为3秒
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " PaymentInfo_TimeOut, id：" + id + "  耗时5秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler, id：" + id + "  o(╥﹏╥)o";
    }


    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),   //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),  //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")  //失败率达到多少后跳闸
    })
    //**上面的翻译：在10秒钟的时间窗口期中，有10次请求，其他60%即6次是失败的，则断路器跳闸**
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id不能为负数，请稍后再试 o(╥﹏╥)o id为：" + id;
    }
}
