package com.bitekeji.weixinsell.service;

import com.bitekeji.weixinsell.entity.SellerInfo;

/**
 * 卖家登录Service
 * @author yuisama
 * @date 2018/8/23 12:23
 */
public interface ISellerInfoService {
    SellerInfo findByUserName(String username);
}
