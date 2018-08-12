package com.bitekeji.weixinsell.service.impl;

import com.bitekeji.weixinsell.dto.CartDTO;
import com.bitekeji.weixinsell.entity.ProductInfo;
import com.bitekeji.weixinsell.enums.ExceptionEnum;
import com.bitekeji.weixinsell.exception.ProductNotFoundException;
import com.bitekeji.weixinsell.exception.ProductStockErrorException;
import com.bitekeji.weixinsell.repository.ProductInfoReposity;
import com.bitekeji.weixinsell.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author yuisama
 * @date 2018/8/3 17:25
 */
@Service
public class ProductInfoServiceImpl implements IProductService {
    @Autowired
    private ProductInfoReposity reposity;
    @Override
    public Optional<ProductInfo> findOne(String productId) {
        Optional<ProductInfo> productInfo =
                reposity.findById(productId);
        return productInfo;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return reposity.findByProductStatus(0);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return reposity.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return reposity.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfo = reposity.findById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new ProductNotFoundException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.get().getProductStock()
                    + cartDTO.getProductQuantity();
            productInfo.get().setProductStock(result);
            reposity.save(productInfo.get());
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfo = reposity.findById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new ProductNotFoundException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.get().getProductStock()
                    - cartDTO.getProductQuantity();
            if (result < 0 ) {
                throw new ProductStockErrorException(ExceptionEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.get().setProductStock(result);

            reposity.save(productInfo.get());
        }
    }
}
