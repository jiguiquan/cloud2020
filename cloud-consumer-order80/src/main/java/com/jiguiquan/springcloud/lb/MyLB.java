package com.jiguiquan.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jiguiquan
 * @create 2020-03-22 18:40
 */
@Component
public class MyLB implements LoadBalancer {
    //可以模仿轮询算法RoundRobinRule的写法，需要一个atomicInteger原子整醒了变量
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //自旋锁 + CAS 线程安全的方式获得下一个计数值
    public final int getAndIncrement(){
        int current;
        int next;
        //自旋锁，同RoundRobinRule
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current,next));  //CAS基础，期望值是current，修改值为next，如果current被修改过，那么就自旋
        System.out.println("*******第几次访问，次数next: " + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
