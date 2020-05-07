package com.jiguiquan.springcloud.alibaba.service;

import java.math.BigDecimal;

/**
 * @author jiguiquan
 * @create 2020-04-29 20:22
 */
public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
