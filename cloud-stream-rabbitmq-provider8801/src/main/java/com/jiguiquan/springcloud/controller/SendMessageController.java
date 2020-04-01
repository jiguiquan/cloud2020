package com.jiguiquan.springcloud.controller;

import com.jiguiquan.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jiguiquan
 * @create 2020-03-31 22:09
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider provider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return provider.send();
    }
}
