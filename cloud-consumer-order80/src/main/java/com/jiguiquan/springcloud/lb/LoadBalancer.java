package com.jiguiquan.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author jiguiquan
 * @create 2020-03-22 18:37
 */
public interface LoadBalancer {
    //根据传值，返回将要访问的ServiceInstance
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
