package com.jiguiquan.springcloud.alibaba.service;

/**
 * @author jiguiquan
 * @create 2020-04-29 20:09
 */
public interface StorageService {
    void  decrease(Long productId, Integer count);
}
