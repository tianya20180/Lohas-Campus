package com.wx.happy.enums;

public enum ShopStateEnum {
    CHECK(0,"审核中"),
    OFFLINE(-1,"非法店铺"),
    SUCCESS(1,"操作成功"),
    PASS(2,""),
    NULL_SHOPID(-1002,"ShopID null"),
    INNER_ERROR(-1001,"内部系统错误"),
    NULL_SHOP(-100,"Shop NULL");
    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }



    private int state;
    private String stateInfo;
    private ShopStateEnum(int state,String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;

    }
    public static ShopStateEnum stateOf(int state){
        for(ShopStateEnum stateEnum:values()){
            if(stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return null;
    }

}
