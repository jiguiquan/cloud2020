package com.jiguiquan.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiguiquan
 * @create 2020-04-12 20:44
 */
@RestController
public class FlowLimitController {
    @GetMapping("testA")
    public String testA(){
        return "--------testA";
    }

    @GetMapping("testB")
    public String testB(){
        return "--------testB";
    }
}
