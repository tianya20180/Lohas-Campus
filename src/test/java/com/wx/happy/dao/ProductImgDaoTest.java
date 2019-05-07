package com.wx.happy.dao;

import com.wx.happy.BaseTest;
import com.wx.happy.entity.Product;
import com.wx.happy.entity.ProductImg;
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
public class ProductImgDaoTest extends BaseTest {


    @Autowired
    private ProductImgDao productImgDao;

    @Test
    @Ignore
    public void testABatchInsertProductImg() throws Exception{
        ProductImg productImg1=new ProductImg();
        productImg1.setImgAddr("/root");
        productImg1.setImgDesc("1111");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(1L);


        ProductImg productImg2=new ProductImg();
        productImg2.setImgAddr("/root");
        productImg2.setImgDesc("1111");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(1L);


        List<ProductImg> productImgList=new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum=productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2,effectedNum);

    }


    @Test
    public void testDelete()throws Exception{
        long productId=1;
        int effectedNum=productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2,effectedNum);
    }


}
