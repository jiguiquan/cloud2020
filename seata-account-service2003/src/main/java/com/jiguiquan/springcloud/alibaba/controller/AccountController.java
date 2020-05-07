package com.jiguiquan.springcloud.alibaba.controller;

import com.jiguiquan.springcloud.alibaba.domain.CommonResult;
import com.jiguiquan.springcloud.alibaba.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author jiguiquan
 * @create 2020-04-29 20:25
 */
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户余额成功");
    }
}
