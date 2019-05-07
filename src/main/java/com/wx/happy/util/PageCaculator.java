package com.wx.happy.util;

public class PageCaculator {
    public static int caculatorRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
