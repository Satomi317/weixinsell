package com.bitekeji.weixinsell.service.impl;

import com.bitekeji.weixinsell.config.WechatAccountConfig;
import com.bitekeji.weixinsell.dto.OrderDTO;
import com.bitekeji.weixinsell.enums.OrderStatusEnum;
import com.bitekeji.weixinsell.service.IWechatPushService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuisama
 * @date 2018/8/26 15:44
 */
@Service
@Slf4j
public class WechatPushService implements IWechatPushService {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void pushOrder(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId());
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        Integer orderStatus = orderDTO.getOrderStatus();
        String orderStatusStr;
        if (orderStatus.equals(0)) {
            orderStatusStr = OrderStatusEnum.NEW.getMsg();
        }else if (orderStatus.equals(1)) {
            orderStatusStr = OrderStatusEnum.FINISHED.getMsg();
        }else {
            orderStatusStr = OrderStatusEnum.CANCEL.getMsg();
        }

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","请及时关注订单状态"),
                // 订单id
                new WxMpTemplateData("keyword1",orderDTO.getOrderId()),
                // 订单总金额
                new WxMpTemplateData("keyword2",orderDTO.getOrderAmount().toString()),
                // 订单状态
                new WxMpTemplateData("keyword3",orderStatusStr),
                // 买家地址
                new WxMpTemplateData("keyword4",orderDTO.getBuyerAddress()),
                // 买家手机号
                new WxMpTemplateData("keyword5",orderDTO.getBuyerPhone()),
                new WxMpTemplateData("remark","欢迎您再次光临小店")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板推送】失败,{}",e);
        }
    }
}
