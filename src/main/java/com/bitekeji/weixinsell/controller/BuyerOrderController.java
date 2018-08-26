package com.bitekeji.weixinsell.controller;

import com.bitekeji.weixinsell.VO.ResultVO;
import com.bitekeji.weixinsell.dto.OrderDTO;
import com.bitekeji.weixinsell.entity.OrderDetail;
import com.bitekeji.weixinsell.enums.ExceptionEnum;
import com.bitekeji.weixinsell.exception.OrderException;
import com.bitekeji.weixinsell.form.OrderDetailForm;
import com.bitekeji.weixinsell.form.OrderForm;
import com.bitekeji.weixinsell.form.OrderListForm;
import com.bitekeji.weixinsell.service.IBuyerService;
import com.bitekeji.weixinsell.service.IOrderService;
import com.bitekeji.weixinsell.util.OrderForm2OrderDTO;
import com.bitekeji.weixinsell.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuisama
 * @date 2018/8/12
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IBuyerService buyerService;
    /**
     * 创建订单Controller
     * @param orderForm 表单入参校验
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult) {
        // 创建订单入参有误
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确,orderForm={}",orderForm);
            throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // 将OrderForm转为OrderDTO
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new OrderException(ExceptionEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 查询订单列表Controller
     * @param orderListForm 表单入参校验
     * @param bindingResult
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@Valid OrderListForm orderListForm,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【查询订单列表】失败，openid为空");
            throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        PageRequest request = PageRequest.of(orderListForm.getPage(),orderListForm.getSize());
        Page<OrderDTO> orderDTOPage = orderService.findOrderList(
                orderListForm.getOpenid(),request);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 查询订单详情controller
     * @param orderDetailForm 订单详情表单校验
     * @param bindingResult
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@Valid OrderDetailForm orderDetailForm,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【查询订单详情】失败，参数不正确,orderDetailForm={}",orderDetailForm);
            throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO result = buyerService.findOneOrder(orderDetailForm.getOpenid(),
                orderDetailForm.getOrderId());
        return ResultVOUtil.success(result);
    }

    /**
     * 取消订单controller
     * @param orderDetailForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO<OrderDTO> cancle(@Valid OrderDetailForm orderDetailForm,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【取消订单】失败，参数不正确,orderDetailForm={}",orderDetailForm);
            throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO result = buyerService.cancleOrder(orderDetailForm.getOpenid(),
                orderDetailForm.getOrderId());
        return ResultVOUtil.success(result);
    }
}
