package com.bitekeji.weixinsell.repository;

import com.bitekeji.weixinsell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuisama
 * @date 2018/8/3 13:05
 */
public interface ProductCategoryReposity extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
