package com.wx.happy.dao;

import com.wx.happy.BaseTest;
import com.wx.happy.entity.Area;
import com.wx.happy.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> List=shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(1,List.size());
    }


}
