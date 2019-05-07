package com.wx.happy.exceptions;

public class ProductOperationException  extends RuntimeException{
    private static final long serialVersionUID=2423847893265834L;


    public  ProductOperationException(String errorMsg) {
        super(errorMsg);
    }
}
