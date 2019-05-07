package com.wx.happy.exceptions;

public class ShopOperationException extends RuntimeException {
    private static final long serialVersionUID=2423847893265898L;


    public  ShopOperationException(String errorMsg){
        super(errorMsg);
    }
}
