package com.wx.happy.util;

import com.wx.happy.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r=new Random();
    private static Logger logger= LoggerFactory.getLogger(ImageUtil.class);

    public static File transferCommonsToFile(CommonsMultipartFile cFile){
        File newFile=new File(cFile.getOriginalFilename());
        try {

            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }


    public static String generateThumbnail(File thumbnail,String targetAddr){
        String realFileName=getRamdomFileName();
        String extension=getFileExtension(thumbnail);
        System.out.println(realFileName+" "+extension);
        try {
            System.out.println(targetAddr);
            makeDirPath(targetAddr);
        }
        catch (Exception e){
            System.out.println("make path error");
            e.printStackTrace();
        }
        String relativeAddr=targetAddr+"/"+realFileName+extension;
        logger.debug("current Addr:"+realFileName);
        File dest=new File(PathUtil.getImageBaesPath()+"/"+relativeAddr);
        logger.debug("current completeAdd"+PathUtil.getImageBaesPath()+"/"+relativeAddr);
        try{
            Thumbnails.of(thumbnail).size(200,200)
            .outputQuality(0.8f).toFile(dest);
        }catch(IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }

        return relativeAddr;
    }

    private static void makeDirPath(String targetAddr) {
        String realFilePath=PathUtil.getImageBaesPath()+"/"+targetAddr;
        File dirPath=new File(realFilePath);
        if(!dirPath.exists()){
            System.out.println(dirPath.mkdirs());
        }
    }

    private static String getFileExtension(File cFile) {
        String originalFileName=cFile.getName();
        return ".jpg";
    }

    public static String getRamdomFileName() {
         int rannum=r.nextInt(8999)+10000;
         String nowTimeStr=sDateFormat.format(new Date());
         return nowTimeStr+rannum;
    }



    public static void main(String[] args) throws IOException {
        String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("/root/图片/source.jpg")).size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/shuiyin.png")),
                0.25f).outputQuality(0.8f).toFile("/root/图片/new_source.jpg");




    }


/**
 *
 * 如果是文件则删除文件 如果是目录则删除目录以及目录下 的 所有文件
 *
 * */
public static void deleteFileOrPath(String storePath){
File fileOrPath=new File(PathUtil.getImageBaesPath()+"/"+storePath);
System.out.println("delete file:"+fileOrPath);
if(fileOrPath.exists()){
    if(fileOrPath.isDirectory()){
        File files[]= fileOrPath.listFiles();
        for(int i=0;i<files.length;i++){
            files[i].delete();
        }

    }
    fileOrPath.delete();
}





}



   // public static String generateThumbnail(ImageHolder imageHolder,String targertAddr){



    //}

    public static String generateThumbnail(ImageHolder thumbnail,String targetAddr){
        String realFileName=getRamdomFileName();
        String extension=".jpg";
        System.out.println(realFileName+" "+extension);
        try {
            System.out.println(targetAddr);
            makeDirPath(targetAddr);
        }
        catch (Exception e){
            System.out.println("make path error");
            e.printStackTrace();
        }
        String relativeAddr=targetAddr+"/"+realFileName+extension;
        logger.debug("current Addr:"+realFileName);
        File dest=new File(PathUtil.getImageBaesPath()+"/"+relativeAddr);
        logger.debug("current completeAdd"+PathUtil.getImageBaesPath()+"/"+relativeAddr);
        try{
            Thumbnails.of(thumbnail.getImage()).size(200,200)
                    .outputQuality(0.8f).toFile(dest);
        }catch(IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }

        return relativeAddr;
    }

    public static String generateNormalImg(ImageHolder thumbnail,String targetAddr){
        String realFileName=getRamdomFileName();
        String extension=".jpg";
        System.out.println(realFileName+" "+extension);
        try {
            System.out.println(targetAddr);
            makeDirPath(targetAddr);
        }
        catch (Exception e){
            System.out.println("make path error");
            e.printStackTrace();
        }
        String relativeAddr=targetAddr+"/"+realFileName+extension;
        logger.debug("current Addr:"+realFileName);
        File dest=new File(PathUtil.getImageBaesPath()+"/"+relativeAddr);
        logger.debug("current completeAdd"+PathUtil.getImageBaesPath()+"/"+relativeAddr);
        try{
            Thumbnails.of(thumbnail.getImage()).size(337,600)
                    .outputQuality(0.9f).toFile(dest);
        }catch(IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }

        return relativeAddr;
    }

}
