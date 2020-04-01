package com.jiguiquan.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiguiquan
 * @create 2020-03-31 22:56
 */
@RestController
@EnableBinding(Sink.class)  //输出通道
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者1号——————>接收到的消息为：" + message.getPayload()+ "\t port：" + serverPort);
    }
}
