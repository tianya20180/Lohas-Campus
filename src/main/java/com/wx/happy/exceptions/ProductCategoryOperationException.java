package com.wx.happy.exceptions;

public class ProductCategoryOperationException extends RuntimeException {

    private static final long serialVersionUID=24238478932658454L;

    public ProductCategoryOperationException(String errMsg){
        super(errMsg);

    }
}
