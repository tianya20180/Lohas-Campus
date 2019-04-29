package com.wx.happy.service.impl;

import com.wx.happy.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductCategoryService {

    /**
     * 查询某个店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

}
