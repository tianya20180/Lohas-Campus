package com.wx.happy.service.impl;

import com.wx.happy.dao.ProductCategoryDao;
import com.wx.happy.dao.ProductDao;
import com.wx.happy.dto.ProductCategoryExecution;
import com.wx.happy.entity.ProductCategory;
import com.wx.happy.enums.ProductCategoryStateEnum;
import com.wx.happy.exceptions.ProductCategoryOperationException;
import com.wx.happy.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;

    public ProductCategoryServiceImpl(){

    }
    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if(productCategoryList!=null&&productCategoryList.size()>0){
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum < 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new ProductCategoryOperationException("batchAddProductCategory error:"+e.getMessage());
            }
        }else{
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }

    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        //先将此商品类别下的商品id置未空

        try{
            int effectedNum=productDao.updateProductCategoryToNull(productCategoryId);
            if(effectedNum<0){
                throw new ProductCategoryOperationException("商品类别更新失败");
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategoryError"+e.getMessage());
        }

        try{
            int effectedNum=productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if(effectedNum<=0){
                throw new ProductCategoryOperationException("删除失败");
            }else{
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        }catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategoryError:" + e.getMessage());
        }
    }
}
