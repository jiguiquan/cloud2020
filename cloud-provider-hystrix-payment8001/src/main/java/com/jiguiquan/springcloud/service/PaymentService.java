package com.jiguiquan.springcloud.service;

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
}
