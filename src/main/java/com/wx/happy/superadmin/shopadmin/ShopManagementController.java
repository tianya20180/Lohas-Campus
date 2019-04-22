package com.wx.happy.superadmin.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wx.happy.dto.ShopExcution;
import com.wx.happy.entity.PersonInfo;
import com.wx.happy.entity.Shop;
import com.wx.happy.enums.ShopStateEnum;
import com.wx.happy.service.ShopService;
import com.wx.happy.util.HttpServletUtil;
import com.wx.happy.util.ImageUtil;
import com.wx.happy.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;



    @RequestMapping(value = "/registershp",method= RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request){
        //1.接收并转化响应的参数

       Map<String,Object>modelMap=new HashMap<String,Object>();
       String shopStr=HttpServletUtil.getString(request,"shopStr");
        ObjectMapper mapper=new ObjectMapper();
        Shop shop=null;
        try{
            shop=mapper.readValue(shopStr,Shop.class);
        }catch(Exception e){
            modelMap.put("suceess",false);
            modelMap.put("errorMsg",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg=null;
        CommonsMultipartResolver commonsMultipartResolver=new
                CommonsMultipartResolver(request.getSession().getServletContext());
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
            shopImg=(CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
        }else {
            modelMap.put("suceess",false);
            modelMap.put("errorMsg","上传图片不能为空");
        }
        //2.注册店铺
        if(shop!=null&&shopImg!=null){
            PersonInfo owner=new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
            File shopImgFile=new File(PathUtil.getImageBaesPath()+ImageUtil.getRamdomFileName());
            try {
                shopImgFile.createNewFile();
            } catch (IOException e) {
                modelMap.put("suceess",false);
                modelMap.put("errorMsg",e.getMessage());
                return modelMap;
            }
            try {
                inputStreamToFile(shopImg.getInputStream(),shopImgFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ShopExcution se= shopService.addShop(shop,shopImgFile);
            if(se.getState()== ShopStateEnum.CHECK.getState()){
                modelMap.put("success",true);
            }
            else{
                modelMap.put("success",false);
                modelMap.put("errMsg",se.getStateInfo());
            }
            return modelMap;
        }else{
            modelMap.put("suceess",false);
            modelMap.put("errorMsg","请输入店铺信息");
            return modelMap;
        }






        //3.返回结果
    }


    private static void inputStreamToFile(InputStream ins, File file){
        OutputStream os=null;
        try{
            os=new FileOutputStream(file);
            int bytesRead=0;
            byte[] buffer=new byte[1024];
            while((bytesRead=ins.read(buffer))!=-1){
                os.write(buffer,0,bytesRead);
            }
        }catch(Exception e){
            throw new RuntimeException("inputStreamToFile Exception"+e.getMessage());

        }finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (ins != null) {
                    ins.close();
                }
            }catch (IOException e){
                throw new RuntimeException("close IO Exception"+e.getMessage());

            }
        }




    }


}
