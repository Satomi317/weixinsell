package com.bitekeji.weixinsell.service.impl;

import com.bitekeji.weixinsell.entity.ProductCategory;
import com.bitekeji.weixinsell.repository.ProductCategoryReposity;
import com.bitekeji.weixinsell.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 商品类目表Service实现类
 * @author yuisama
 * @date 2018/8/3 15:16
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
    @Autowired
    private ProductCategoryReposity reposity;

    @Override
    public Optional<ProductCategory> findOne(Integer categoryId) {
        return reposity.findById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return reposity.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return reposity.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return reposity.save(productCategory);
    }
}
