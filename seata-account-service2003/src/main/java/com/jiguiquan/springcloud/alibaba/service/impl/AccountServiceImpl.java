package com.jiguiquan.springcloud.alibaba.service.impl;

import com.jiguiquan.springcloud.alibaba.dao.AccountMapper;
import com.jiguiquan.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author jiguiquan
 * @create 2020-04-29 20:22
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------>account-service中扣除账户余额开始");
        accountMapper.decrease(userId, money);
        log.info("------>account-service中扣除账户余额结束");
    }
}
