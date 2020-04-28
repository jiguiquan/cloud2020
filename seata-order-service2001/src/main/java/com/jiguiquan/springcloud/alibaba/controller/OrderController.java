package com.jiguiquan.springcloud.alibaba.controller;

import com.jiguiquan.springcloud.alibaba.domain.CommonResult;
import com.jiguiquan.springcloud.alibaba.domain.Order;
import com.jiguiquan.springcloud.alibaba.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiguiquan
 * @create 2020-04-28 23:58
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/create")
    public CommonResult<Order> create(Order order){
        Order result = orderService.create(order);
        return new CommonResult<>(200, "创建订单成功", result);
    }
}
