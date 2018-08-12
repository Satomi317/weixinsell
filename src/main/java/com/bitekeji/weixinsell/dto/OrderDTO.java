package com.bitekeji.weixinsell.dto;

import com.bitekeji.weixinsell.entity.OrderDetail;
import com.bitekeji.weixinsell.util.Date2LongUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用于在各层传输订单信息类
 * @author yuisama
 * @date 2018/8/9
 */
@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    @JsonSerialize(using = Date2LongUtil.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongUtil.class)
    private Date updateTime;
    List<OrderDetail> orderDetailList;
}
