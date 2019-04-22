package com.wx.happy.util;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
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
        makeDirPath(targetAddr);
        String relativeAddr=targetAddr+realFileName+extension;
        logger.debug("current Addr:"+realFileName);
        File dest=new File(PathUtil.getImageBaesPath()+realFileName);
        logger.debug("current completeAdd"+PathUtil.getImageBaesPath()+relativeAddr);
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
        String realFilePath=PathUtil.getImageBaesPath()+targetAddr;
        File dirPath=new File(realFilePath);
        if(!dirPath.exists()){
            dirPath.mkdir();
        }
    }

    private static String getFileExtension(File cFile) {
        String originalFileName=cFile.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    private static String getRamdomFileName() {
         int rannum=r.nextInt(8999)+10000;
         String nowTimeStr=sDateFormat.format(new Date());
         return nowTimeStr+rannum;
    }



    public static void main(String[] args) throws IOException {
        String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("/root/图片/source.jpg")).size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/shuiyin.png")),
                0.25f).outputQuality(0.8f).toFile("/root/图片/new_source.jpg");




    }





}
