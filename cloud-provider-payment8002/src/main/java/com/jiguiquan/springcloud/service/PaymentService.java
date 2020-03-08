package com.jiguiquan.springcloud.service;

import com.jiguiquan.springcloud.entities.Payment;

/**
 * @author jiguiquan
 * @create 2020-03-07 11:35
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(Long id);
}
