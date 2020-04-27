package com.jiguiquan.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jiguiquan.springcloud.entities.CommonResult;
import com.jiguiquan.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author jiguiquan
 * @create 2020-04-27 21:53
 */
@RestController
public class CircleBreakerController {

    private static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", fallback = "handlerFallback",
            exceptionsToIgnore = {IllegalArgumentException.class, NullPointerException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+ id, CommonResult.class);
        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException，非法参数异常。。。。");
        }else if (result.getData() == null){
            throw new NullPointerException("NullPointerException, 该ID没有对应记录，空指针异常");
        }
        return result;
    }

    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException exception){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "blockHandler-sentinel限流，无此流水："+ exception.getMessage(), payment);
    }

    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "兜底异常handlerFallback，exception内容："+ e.getMessage(), payment);
    }
}
