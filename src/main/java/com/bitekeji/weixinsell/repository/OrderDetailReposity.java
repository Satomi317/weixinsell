package com.bitekeji.weixinsell.repository;

import com.bitekeji.weixinsell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuisama
 * @date 2018/8/8
 */
public interface OrderDetailReposity extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
}
