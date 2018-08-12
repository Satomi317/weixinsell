package com.bitekeji.weixinsell.controller;

import com.bitekeji.weixinsell.VO.ProductInfoVO;
import com.bitekeji.weixinsell.VO.ProductVO;
import com.bitekeji.weixinsell.VO.ResultVO;
import com.bitekeji.weixinsell.entity.ProductCategory;
import com.bitekeji.weixinsell.entity.ProductInfo;
import com.bitekeji.weixinsell.service.IProductCategoryService;
import com.bitekeji.weixinsell.service.IProductService;
import com.bitekeji.weixinsell.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品控制层
 * @author yuisama
 * @date 2018/8/6 10:29
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductCategoryService productCategoryService;
    @GetMapping("/list")
    public ResultVO list() {
        //1. 查询所有上架的商品
        List<ProductInfo> productInfoLists = productService.findUpAll();

        //2. 查询类目(一次性查询)
//        List<Integer> categoryTypeList = new ArrayList<>();
//        // 传统方法
//        for (ProductInfo productInfo: productInfoLists) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
//        productCategoryService.findByCategoryTypeIn(categoryTypeList);
       // 精简做法
        List<Integer> categoryList =
                productInfoLists.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList =
                productCategoryService.findByCategoryTypeIn(categoryList);
        //3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfoLists) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOLists(productInfoVOS);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
