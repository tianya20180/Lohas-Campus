package com.wx.happy.dto;

import com.wx.happy.entity.ProductCategory;
import com.wx.happy.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    //结果状态
    private int state;
    //状态标示
    private String stateInfo;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    private List<ProductCategory>productCategoryList;

    public ProductCategoryExecution(){

    }
    //fail constructor
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }
    //success constructor
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,List<ProductCategory>productCategoryList ){

        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.productCategoryList=productCategoryList;
    }




}
