package com.bitekeji.weixinsell.service.impl;

import com.bitekeji.weixinsell.dto.OrderDTO;
import com.bitekeji.weixinsell.enums.ExceptionEnum;
import com.bitekeji.weixinsell.exception.OrderException;
import com.bitekeji.weixinsell.service.IBuyerService;
import com.bitekeji.weixinsell.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuisama
 * @date 2018/8/12
 */
@Service
@Slf4j
public class BuyerServiceImpl implements IBuyerService {
    @Autowired
    private IOrderService orderService;
    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancleOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到该订单,orderId={},openid={}"
            ,orderId,openid);
            throw new OrderException(ExceptionEnum.ORDER_NOT_EXISTS);
        }
        return orderService.cancleOrder(orderDTO);
    }
    private OrderDTO checkOrderOwner(String openid,String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        // 查询者与创建订单者不一致
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单】错误，订单openid与查询者不一致!openid={},orderDTO={}"
                    ,openid,orderDTO);
            throw new OrderException(ExceptionEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
