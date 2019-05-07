package com.wx.happy.service.impl;

import com.wx.happy.dao.ShopDao;
import com.wx.happy.dto.ShopExcution;
import com.wx.happy.entity.Shop;
import com.wx.happy.enums.ShopStateEnum;
import com.wx.happy.exceptions.ShopOperationException;
import com.wx.happy.service.ShopService;
import com.wx.happy.util.ImageUtil;
import com.wx.happy.util.PageCaculator;
import com.wx.happy.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IndexedPropertyChangeEvent;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExcution addShop(Shop shop, File shopImg) {
        //空值判断
        if(shop==null){
              return new ShopExcution(ShopStateEnum.NULL_SHOP);
          }
          try{
              //给店铺信息 初始值
              shop.setEnableStatus(0);
              shop.setCreateTime(new Date());
              shop.setLastEditTime(new Date());
              int effectedNum=shopDao.insertShop(shop);
              if(effectedNum<=0){
                  throw new ShopOperationException("店铺创建失败");
              }
              else{
                //  System.out.println(shopImg);
                  if(shopImg!=null){
                      try {
                          addShopImg(shop, shopImg);
                      }
                      catch(Exception e){
                          throw new ShopOperationException("add shopImg error"+e.getMessage());
                      }
                      shop.getShopImg();
                  }
                  effectedNum=shopDao.updateShop(shop);
                  if(effectedNum<0){
                      throw new ShopOperationException("店铺创建失败");
                  }
              }
          }catch (Exception e){
              throw new ShopOperationException("add shop error"+e.getMessage());
          }
          return new ShopExcution(ShopStateEnum.CHECK,shop);

    }

    @Override
    public Shop getShopById(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    private void addShopImg(Shop shop, File shopImg) {
        //获取shop目录的相对值路径

        String dest = PathUtil.getShopImagePath(shop.getShopId());
        System.out.println(dest);
        String shopImgAddr= ImageUtil.generateThumbnail(shopImg,dest);
        System.out.println(shopImgAddr);
        shop.setShopImg(shopImgAddr);
    }

   public ShopExcution modifyShop(Shop shop, File shopImg)
       throws ShopOperationException {
           if (shop == null || shop.getShopId() == null) {
               return new ShopExcution(ShopStateEnum.CHECK.NULL_SHOP);
           }
           //1.判断是否需要处理图片
           else {
               try {
                   if (shopImg != null) {
                       Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                       if (tempShop.getShopImg() != null) {
                           ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                       }
                       addShopImg(shop, shopImg);
                   }
                   //2.更新店铺信息
                   shop.setLastEditTime(new Date());
                   int effectedNum = shopDao.updateShop(shop);
                   if (effectedNum <= 0) {
                       return new ShopExcution(ShopStateEnum.CHECK.INNER_ERROR);
                   } else {
                       shop = shopDao.queryByShopId(shop.getShopId());
                       return new ShopExcution(ShopStateEnum.SUCCESS, shop);
                   }
               }catch (Exception e){
                     throw new ShopOperationException("modify shop error"+e.getMessage());
               }

           }
       }

    @Override
    public ShopExcution getShopList(Shop shopCondition, int pageIndex, int pageSize) {

             int rowIndex= PageCaculator.caculatorRowIndex(pageIndex,pageSize);
             List<Shop> shopList=shopDao.queryShopList(shopCondition,rowIndex,pageSize);
             int count=shopDao.queryShopCount(shopCondition);
             ShopExcution se=new ShopExcution();
             if(shopList!=null){
                 se.setShopList(shopList);
                 se.setCount(count);
             }else{
                 se.setState(ShopStateEnum.INNER_ERROR.getState());
             }

             return se;

    }
}
