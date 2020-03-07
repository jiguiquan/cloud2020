package com.jiguiquan.springcloud.controller;

import com.jiguiquan.springcloud.entities.CommonResult;
import com.jiguiquan.springcloud.entities.Payment;
import com.jiguiquan.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiguiquan
 * @create 2020-03-07 11:40
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果为：" + result);

        if (result > 0){
            return new CommonResult(200, "插入数据库成功", result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果为："+payment);

        if (payment != null){
            return new CommonResult<>(200, "查询成功", payment);
        } else {
            return new CommonResult<>(444, "没有对应记录， 查询id为："+id, null);
        }
    }
}
