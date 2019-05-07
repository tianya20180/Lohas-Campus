package com.wx.happy.service;

import com.wx.happy.dto.ShopExcution;
import com.wx.happy.entity.Shop;
import com.wx.happy.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExcution addShop(Shop shop, File shopImg);
    Shop  getShopById(long shopId);
    ShopExcution modifyShop(Shop shop, File shopImg) throws ShopOperationException;
    ShopExcution getShopList(Shop ShopCondition,int pageIndex,int PageSize);
}
