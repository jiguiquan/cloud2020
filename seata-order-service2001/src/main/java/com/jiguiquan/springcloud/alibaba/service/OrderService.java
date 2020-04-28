package com.jiguiquan.springcloud.alibaba.service;

import com.jiguiquan.springcloud.alibaba.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author jiguiquan
 * @create 2020-04-28 23:28
 */
@FeignClient(value = "seata-account-service")
public interface OrderService {
    Order create(Order order);
}
