package com.jiguiquan.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jiguiquan.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.jiguiquan.springcloud.entities.CommonResult;
import com.jiguiquan.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiguiquan
 * @create 2020-04-26 22:51
 */
@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK", new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")  //注意这里并没有指定 blockHandler方法
    public CommonResult byUrl(){
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200, "客户自定义限流处理", new Payment(2020L, "serial003"));
    }
}
