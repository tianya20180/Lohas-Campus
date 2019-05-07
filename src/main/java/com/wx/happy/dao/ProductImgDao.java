package com.wx.happy.dao;

import com.wx.happy.entity.Product;
import com.wx.happy.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    List<ProductImg> queryProductImgList(long productId);

    //批量添加商品详情图片
    int batchInsertProductImg(List<ProductImg>productImgList);

    int deleteProductImgByProductId(long productId);


}
