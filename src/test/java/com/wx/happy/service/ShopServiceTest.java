package com.wx.happy.service;

import com.wx.happy.BaseTest;
import com.wx.happy.dto.ShopExcution;
import com.wx.happy.entity.Area;
import com.wx.happy.entity.PersonInfo;
import com.wx.happy.entity.Shop;
import com.wx.happy.entity.ShopCategory;
import com.wx.happy.enums.ShopStateEnum;
import com.wx.happy.exceptions.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    @Ignore
    public void testAddShop(){
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
        shop.setShopName("wx1");
        shop.setShopDesc("test1");
        shop.setPhone("test1");
        shop.setShopImg("test1");
        shop.setAdvice("shening");
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        File shopImg=new File("/root/图片/source.jpg");

        ShopExcution se=shopService.addShop(shop,shopImg);

        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());



    }

    @Test
    @Ignore
    public void testModifyShop() throws ShopOperationException, FileNotFoundException{
        Shop shop=new Shop();
        shop.setShopName("修改后的店铺名称");
        shop.setShopId(1L);
        File fileImg=new File("/root/图片/source.jpg");
        ShopExcution shopExcution=shopService.modifyShop(shop,fileImg);
        System.out.println("new Address"+shopExcution.getShop().getShopImg());
    }



    @Test
    public void testGetShopList(){
        Shop shopCondition=new Shop();
        ShopCategory  sc=new ShopCategory();
        sc.setShopCategoryId(1L);
        shopCondition.setShopCategory(sc);
        ShopExcution se=shopService.getShopList(shopCondition,1,3);
        System.out.println("list:"+se.getShopList().size());
        System.out.println("all:"+se.getShopList().size());

    }
}
