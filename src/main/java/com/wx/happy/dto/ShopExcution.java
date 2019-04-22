package com.wx.happy.dto;

import com.wx.happy.entity.Shop;
import com.wx.happy.enums.ShopStateEnum;

import java.util.List;

public class ShopExcution {

    private int state;
    private String stateInfo;
    private int count;
    private Shop shop;
    private List<Shop> shopList;
    public ShopExcution(){

    }
    //店铺操作失败使用的构造器
    public ShopExcution(ShopStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();

    }
    //店铺操作成功使用的构造器
    public ShopExcution(ShopStateEnum stateEnum,Shop shop){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.shop=shop;
    }
    ////店铺操作成功使用的构造器
    public ShopExcution(ShopStateEnum stateEnum,List<Shop>shopList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.shopList=shopList;
    }


///getter and setter

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }


}
