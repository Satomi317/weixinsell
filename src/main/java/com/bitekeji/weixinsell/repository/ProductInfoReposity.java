package com.bitekeji.weixinsell.repository;

import com.bitekeji.weixinsell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuisama
 * @date 2018/8/3 16:20
 */
public interface ProductInfoReposity extends JpaRepository<ProductInfo,String> {
    /**
     * 根据商品状态一次查询所有商品
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
