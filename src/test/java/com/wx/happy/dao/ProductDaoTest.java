package com.wx.happy.dao;

import com.wx.happy.BaseTest;
import com.wx.happy.entity.Product;
import com.wx.happy.entity.ProductCategory;
import com.wx.happy.entity.ProductImg;
import com.wx.happy.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest  extends BaseTest {
        @Autowired
        private ProductDao productDao;
        @Autowired
        private ProductImgDao productImgDao;

        @Test
        @Ignore
        public void testAInsertProduct() throws Exception{

            Shop shop1 = new Shop();
            shop1.setShopId(1L);
            ProductCategory pc1 = new ProductCategory();
            pc1.setProductCategoryId(2L);
            Product product1 = new Product();
            product1.setProductName("测试1");
            product1.setProductDesc("测试Desc1");
            product1.setImgAddr("test1");
            product1.setPriority(0);
            product1.setEnableStatus(1);
            product1.setCreateTime(new Date());
            product1.setLastEditTime(new Date());
            product1.setShop(shop1);
            product1.setProductCategory(pc1);


            int effectedNum = productDao.insertProduct(product1);
            assertEquals(1, effectedNum);




        }
    @Test
    public void testCQueryProductByProductId() throws Exception {
        long productId = 1;
        Product product = productDao.queryProductById(productId);
        assertEquals(2, product.getProductImgList().size());

    }

    @Test
    @Ignore
    public void testDUpdateProduct() throws Exception {
        Product product = new Product();
        Shop shop=new Shop();
        shop.setShopId(1L);
        product.setShop(shop);
        product.setProductId(1);
        product.setProductName("第一个产品");
        int effectedNum = productDao.updateProduct(product);
        assertEquals(1, effectedNum);
    }



}
