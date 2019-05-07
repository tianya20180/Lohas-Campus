package com.wx.happy.service;

import com.wx.happy.dto.ImageHolder;
import com.wx.happy.dto.ProductExecution;
import com.wx.happy.entity.Product;
import com.wx.happy.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {


    ProductExecution addProduct(Product product,
                                ImageHolder thumbnail,
                                List<ImageHolder>productImgList) throws ProductOperationException;


    Product getProductById(long productId);

    ProductExecution modifyProduct(Product product,ImageHolder thumbnail,
                                   List<ImageHolder> productImageList)throws ProductOperationException;


    ProductExecution getProductList(Product productCondition,
                                    int pageIndex,int pageSize);


}
