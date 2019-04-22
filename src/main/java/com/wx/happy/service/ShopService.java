package com.wx.happy.service;

import com.wx.happy.dto.ShopExcution;
import com.wx.happy.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExcution addShop(Shop shop, File shopImg);
}
