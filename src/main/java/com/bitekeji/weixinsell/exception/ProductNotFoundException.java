package com.bitekeji.weixinsell.exception;

import com.bitekeji.weixinsell.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @author yuisama
 * @date 2018/8/9
 */
@Getter
public class ProductNotFoundException extends RuntimeException {
    private Integer code;

    public ProductNotFoundException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = code;
    }
}
