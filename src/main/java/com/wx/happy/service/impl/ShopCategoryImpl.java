package com.wx.happy.service.impl;


import com.wx.happy.dao.ShopCategoryDao;
import com.wx.happy.entity.ShopCategory;
import com.wx.happy.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopCategoryImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
            return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
