package com.jiguiquan.springcloud.alibaba.service.impl;

import com.jiguiquan.springcloud.alibaba.dao.OrderMapper;
import com.jiguiquan.springcloud.alibaba.domain.Order;
import com.jiguiquan.springcloud.alibaba.service.AccountService;
import com.jiguiquan.springcloud.alibaba.service.OrderService;
import com.jiguiquan.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiguiquan
 * @create 2020-04-28 23:28
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    //发现的所有Exception，通通回滚
    @Override
    @GlobalTransactional(name = "jgq-create-order", rollbackFor = Exception.class)
    public Order create(Order order) {
        log.info("------>开始新建订单");
        order.setStatus(0);
        orderMapper.insert(order);
        System.out.println("此处已获得主键为："+order.getId());

        log.info("------>订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------>订单微服务开始调用库存，做扣减End");

        log.info("------>订单微服务开始调用账户，做扣减");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------>订单微服务开始调用账户，做扣减End");

        //修改原订单状态，从0到1
        log.info("------>修改订单状态开始");
        order.setStatus(1);
        orderMapper.updateByPrimaryKey(order);
        log.info("------>修改订单状态结束");
        return order;
    }
}
