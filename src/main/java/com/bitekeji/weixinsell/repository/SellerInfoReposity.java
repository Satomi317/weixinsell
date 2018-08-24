package com.bitekeji.weixinsell.repository;

import com.bitekeji.weixinsell.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuisama
 * @date 2018/8/23 12:03
 */
public interface SellerInfoReposity extends JpaRepository<SellerInfo,String> {
    SellerInfo findByUsername(String username);
}
