package com.jiguiquan.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对外返回的数据数据包装类
 * 使用了泛型T
 * @author jiguiquan
 * @create 2020-03-07 11:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;   //404
    private String message;  //success
    private T data;  //entity

    //没有data的情况
    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}