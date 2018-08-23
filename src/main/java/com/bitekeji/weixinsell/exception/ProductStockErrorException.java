package com.bitekeji.weixinsell.exception;

import com.bitekeji.weixinsell.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @author yuisama
 * @date 2018/8/9
 */
@Getter
public class ProductStockErrorException extends RuntimeException {
    private Integer code;

    public ProductStockErrorException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }
}
