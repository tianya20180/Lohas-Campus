package com.wx.happy.dao;

import com.wx.happy.BaseTest;
import com.wx.happy.entity.Area;
import com.wx.happy.entity.PersonInfo;
import com.wx.happy.entity.Shop;
import com.wx.happy.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    @Ignore
    public void testInsertShop(){
        Shop shop=new Shop();
        PersonInfo owener=new PersonInfo();
        Area area=new Area();

        ShopCategory shopCategory=new ShopCategory();
        owener.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(owener);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("wx");
        shop.setShopDesc("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setAdvice("shening");
        shop.setEnableStatus(1);

        int effectedNum=shopDao.insertShop(shop);
        assertEquals(1,effectedNum);

    }




    @Test
    public void testUpdateShop(){
        Shop shop=new Shop();
        shop.setShopId(1L);
        shop.setShopName("wx");
        shop.setShopDesc("乐活校园");
        shop.setPhone("testUpdate");
        shop.setShopImg("testUpdate");
        int effectedNum=shopDao.updateShop(shop);
        assertEquals(1,effectedNum);

    }
}
