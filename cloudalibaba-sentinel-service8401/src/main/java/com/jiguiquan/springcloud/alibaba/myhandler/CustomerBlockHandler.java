package com.jiguiquan.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jiguiquan.springcloud.entities.CommonResult;

/**
 * @author jiguiquan
 * @create 2020-04-26 23:21
 */
public class CustomerBlockHandler {
    //必须是static方法
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"按用户自定义， Global HandlerException-----1");
    }
    //做2个异常处理方法，是为了在业务中根据不同场景，选择使用不同的全局异常处理方法
    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444,"按用户自定义， Global HandlerException-----2");
    }
}
