package com.wx.happy.service;

import com.wx.happy.BaseTest;
import com.wx.happy.dto.ImageHolder;
import com.wx.happy.dto.ProductExecution;
import com.wx.happy.entity.Product;
import com.wx.happy.entity.ProductCategory;
import com.wx.happy.entity.Shop;
import com.wx.happy.enums.ProductStateEnum;
import com.wx.happy.exceptions.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    @Ignore
    public void testAddProduct()
            throws ShopOperationException, FileNotFoundException{
        Product product=new Product();
        Shop shop=new Shop();
        shop.setShopId(1L);
        ProductCategory pc=new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("testProduct");
        product.setPriority(20);
        product.setCreateTime(new Date());

        product.setProductDesc("Test");

        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        File thumbnailFile=new File("/root/图片/1.jpg");
        InputStream is=new FileInputStream(thumbnailFile);
        ImageHolder thumbnail=new ImageHolder(thumbnailFile.getName(),is);

        File productImg1=new File("/root/图片/2.jpg");
        InputStream is1=new FileInputStream(productImg1);

        File productImg2=new File("/root/图片/2.jpg");
        InputStream is2=new FileInputStream(productImg2);

        List<ImageHolder> productImgList=new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));

        ProductExecution pe=productService.addProduct(product,thumbnail,productImgList);

        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
    }



    @Test
    public void testModifyProduct() throws ShopOperationException
    ,FileNotFoundException{
        Product product=new Product();
        product.setProductId(1);
        Shop shop=new Shop();
        shop.setShopId(1L);
        ProductCategory pc=new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("testProduct1");
        product.setPriority(20);
        product.setCreateTime(new Date());

        product.setProductDesc("Test1");

        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        File thumbnailFile=new File("/root/图片/1.jpg");
        InputStream is=new FileInputStream(thumbnailFile);
        ImageHolder thumbnail=new ImageHolder(thumbnailFile.getName(),is);

        File productImg1=new File("/root/图片/2.jpg");
        InputStream is1=new FileInputStream(productImg1);

        File productImg2=new File("/root/图片/3.jpg");
        InputStream is2=new FileInputStream(productImg2);
        List<ImageHolder> productImgList=new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));
        ProductExecution pe=productService.modifyProduct(product,thumbnail,productImgList);

    }

}
