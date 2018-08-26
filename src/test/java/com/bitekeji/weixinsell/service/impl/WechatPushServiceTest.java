package com.bitekeji.weixinsell.service.impl;

import com.bitekeji.weixinsell.dto.OrderDTO;
import com.bitekeji.weixinsell.service.IOrderService;
import com.bitekeji.weixinsell.service.IWechatPushService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author yuisama
 * @date 2018/8/26 15:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WechatPushServiceTest {
    @Autowired
    private IWechatPushService wechatPushService;

    @Autowired
    private IOrderService orderService;

    @Test
    public void orderStatus() throws Exception {

        OrderDTO orderDTO = orderService.findOne("1534843769105612103");
        wechatPushService.pushOrder(orderDTO);
    }
}