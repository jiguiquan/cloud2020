package com.jiguiquan.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @author jiguiquan
 * @create 2020-03-24 21:37
 */
@Service
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "------PaymentFallbackService-paymentInfo_OK  o(╥﹏╥)o---";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        //如果失败就会进入方法体，实现服务降级
        return "------PaymentFallbackService-paymentInfo_TimeOut  o(╥﹏╥)o---";
    }
}
