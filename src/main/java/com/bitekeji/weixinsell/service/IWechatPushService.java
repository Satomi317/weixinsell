package com.bitekeji.weixinsell.service;

import com.bitekeji.weixinsell.dto.OrderDTO;

/**
 * @author yuisama
 * @date 2018/8/26 15:42
 */
public interface IWechatPushService {
    /**
     * 订单状态变更推送微信模板消息
     * @param orderDTO
     */
    void pushOrder(OrderDTO orderDTO);
}
