package com.bitekeji.weixinsell.repository;

import com.bitekeji.weixinsell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuisama
 * @date 2018/8/8
 */

public interface OrderMasterReposity extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
