package com.jiguiquan.springcloud.service.impl;

import com.jiguiquan.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author jiguiquan
 * @create 2020-03-31 21:45
 */
@EnableBinding(Source.class)  //消息的推送管道（接收管道）
public class IMessageProviderImpl implements IMessageProvider {
    @Resource
    private MessageChannel output;   //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*********serial：" + serial);
        return serial;
    }
}
