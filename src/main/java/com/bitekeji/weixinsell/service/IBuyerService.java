package com.bitekeji.weixinsell.service;

import com.bitekeji.weixinsell.dto.OrderDTO;

/**
 * 买家service
 * @author yuisama
 * @date 2018/8/12
 */
public interface IBuyerService {
    OrderDTO findOneOrder(String openid,String orderId);
    OrderDTO cancleOrder(String openid,String orderId);
}
