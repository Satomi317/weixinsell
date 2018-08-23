package com.bitekeji.weixinsell.util;

import com.bitekeji.weixinsell.dto.OrderDTO;
import com.bitekeji.weixinsell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuisama
 * @date 2018/8/17 13:52
 */
public class OrderMaster2OrderDTO {

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        List<OrderDTO> orderDTOList =
                orderMasterList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
        return orderDTOList;
    }
}
