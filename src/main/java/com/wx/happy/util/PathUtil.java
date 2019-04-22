package com.wx.happy.util;

public class PathUtil {
    private static String seperator=System.getProperty("file.separator");
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
        String imagePath="";
        try {
             imagePath = "upload/item/shop/" + shopId;
        }
        catch (Exception e){
           e.printStackTrace();
        }
        return imagePath;
    }

}
