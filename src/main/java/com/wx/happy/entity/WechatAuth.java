package com.wx.happy.entity;

import java.util.Date;

public class WechatAuth {
    private Long wechatAuthId;
    private String openId;
    private Date cretaeTime;
    private PersonInfo personInfo;//

    public Long getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCretaeTime() {
        return cretaeTime;
    }

    public void setCretaeTime(Date cretaeTime) {
        this.cretaeTime = cretaeTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}
