package com.jiguiquan.springcloud.service.impl;

import com.jiguiquan.springcloud.dao.PaymentDao;
import com.jiguiquan.springcloud.entities.Payment;
import com.jiguiquan.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiguiquan
 * @create 2020-03-07 11:35
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}
