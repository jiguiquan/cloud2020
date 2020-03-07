package com.jiguiquan.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jiguiquan
 * @create 2020-03-07 11:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private static final long serialVersionUID = -5011778013761932311L;
    private Long id;
    private String serial;
}
