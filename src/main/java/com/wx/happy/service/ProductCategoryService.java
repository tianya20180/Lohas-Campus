package com.wx.happy.service;

import com.wx.happy.dto.ProductCategoryExecution;
import com.wx.happy.entity.ProductCategory;
import com.wx.happy.exceptions.ProductCategoryOperationException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductCategoryService {

    /**
     * 查询某个店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory>productCategoryList)
            throws ProductCategoryOperationException;

    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId) throws ProductCategoryOperationException;

}
