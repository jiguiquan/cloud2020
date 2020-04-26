package com.jiguiquan.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey")
    public String testHotkey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "-------testHotkey";
    }

    public String deal_testHotkey(String p1, String p2, BlockException exception){
        return "-------deal testHotkey o(╥﹏╥)o";  //Sentinel的默认提示“Blocked by Sentinel(flow limiting)”
    }
}
