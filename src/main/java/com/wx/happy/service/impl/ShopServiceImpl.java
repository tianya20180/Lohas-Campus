package com.wx.happy.service.impl;

import com.wx.happy.dao.ShopDao;
import com.wx.happy.dto.ShopExcution;
import com.wx.happy.entity.Shop;
import com.wx.happy.enums.ShopStateEnum;
import com.wx.happy.exceptions.ShopOperationException;
import com.wx.happy.service.ShopService;
import com.wx.happy.util.ImageUtil;
import com.wx.happy.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

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
              throw new ShopOperationException("addShop error"+e.getMessage());
          }
          return new ShopExcution(ShopStateEnum.CHECK,shop);

    }

    private void addShopImg(Shop shop, File shopImg) {
        //获取shop目录的相对值路径
        String dest= PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr= ImageUtil.generateThumbnail(shopImg,dest);
        shop.setShopImg(shopImgAddr);
    }

}
