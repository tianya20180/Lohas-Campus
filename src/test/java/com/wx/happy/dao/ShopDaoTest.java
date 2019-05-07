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

import java.util.List;

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
        shop.setShopName("wx4");
        shop.setShopDesc("test4");
        shop.setPhone("test2");
        shop.setShopImg("test4");
        shop.setAdvice("shening");
        shop.setEnableStatus(1);

        int effectedNum=shopDao.insertShop(shop);
        assertEquals(1,effectedNum);

    }




    @Test
    @Ignore
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

    @Test
    @Ignore
    public void testQueryShopById(){
        long shopId=1;
        Shop shop=shopDao.queryByShopId(shopId);
        System.out.println("AreaId"+shop.getArea().getAreaId());
        System.out.println("AreaName"+shop.getArea().getAreaName());
    }

    @Test
    @Ignore
    public void testQueryShopList(){
        Shop shopCondition=new Shop();
        PersonInfo owner=new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList=shopDao.queryShopList(shopCondition,0,3);
        System.out.println("daxiao"+shopList.size());
    }



}
