package com.bitekeji.weixinsell.util;

import com.bitekeji.weixinsell.dto.OrderDTO;
import com.bitekeji.weixinsell.entity.OrderDetail;
import com.bitekeji.weixinsell.enums.ExceptionEnum;
import com.bitekeji.weixinsell.exception.OrderException;
import com.bitekeji.weixinsell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuisama
 * @date 2018/8/12
 */
@Slf4j
public class OrderForm2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        Gson gson = new Gson();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
           log.error("【对象转换】错误,string={}",orderForm.getItems());
           throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
