package com.wx.happy.dao;

import com.wx.happy.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    List<ProductCategory> queryProductCategoryList(long shopId);
    int batchInsertProductCategory(List<ProductCategory>productCategoryList);
    int deleteProductCategory(@Param("productCategoryId") long productCategory,
                              @Param("shopId") long shopId);
}

