package com.jiguiquan.springcloud.alibaba.service.impl;

import com.jiguiquan.springcloud.alibaba.dao.StorageMapper;
import com.jiguiquan.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiguiquan
 * @create 2020-04-29 20:10
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
      log.info("------>storage-service中扣减库存开始");
      storageMapper.decrease(productId, count);
      log.info("------>storage-service中扣减库存结束");
    }
}
