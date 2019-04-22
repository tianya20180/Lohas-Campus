package com.wx.happy.util;

public class PathUtil {
    private static String seperator=System.getProperty("file.seperator");
    public static String getImageBaesPath(){
        String os=System.getProperty("os.name");
        String basePath="";
        if(os.toLowerCase().startsWith("win")){
            basePath="D:/projectdev/image";
        }else {
            basePath="/root/图片";
        }
        basePath=basePath.replace("/",seperator);
        return basePath;
    }
    public static String getShopImagePath(long shopId){
        String imagePath="upload/item/shop/"+shopId;
        return imagePath.replace("/",seperator);
    }

}
