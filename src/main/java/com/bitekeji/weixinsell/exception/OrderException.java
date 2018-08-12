package com.bitekeji.weixinsell.exception;

import com.bitekeji.weixinsell.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @author yuisama
 * @date 2018/8/10
 */
@Getter
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = code;
    }
    public OrderException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }
}
