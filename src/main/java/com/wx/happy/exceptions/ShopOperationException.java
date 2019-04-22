package com.wx.happy.exceptions;

public class ShopOperationException extends RuntimeException {
    public  ShopOperationException(String errorMsg){
        super(errorMsg);
    }
}
