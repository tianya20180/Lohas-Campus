package com.wx.happy.service.impl;

import com.wx.happy.dao.ProductDao;
import com.wx.happy.dao.ProductImgDao;
import com.wx.happy.dto.ImageHolder;
import com.wx.happy.dto.ProductExecution;
import com.wx.happy.entity.Product;
import com.wx.happy.entity.ProductImg;
import com.wx.happy.enums.ProductStateEnum;
import com.wx.happy.exceptions.ProductOperationException;
import com.wx.happy.service.ProductService;
import com.wx.happy.util.ImageUtil;
import com.wx.happy.util.PageCaculator;
import com.wx.happy.util.PathUtil;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;


    /**
     *
     * 1.处理缩略图
     * 2.往tb_product写入商品信息
     * 3.结合productId批量处理商品详情图
     * 4.将商品详情图列表批量插入tb_product_img中
     *
     *
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {
          if(product!=null&&product.getShop()!=null&&product.getShop().getShopId()!=null){
              product.setCreateTime(new Date());
              product.setLastEditTime(new Date());
              product.setEnableStatus(1);
              if(thumbnail!=null){
                  Product tmepProduct=productDao.queryProductById(product.getProductId());
                  addTumbnail(product,thumbnail);
              }
              try{
                  int effectedNum=productDao.insertProduct(product);
                  if(effectedNum<0){
                      throw new ProductOperationException("创建商品失败");
                  }
              }catch (Exception e){
                  throw new ProductOperationException("创建商品失败"+e.toString());
              }
              if(productImgList!=null&&productImgList.size()>0){
                  addProductImgList(product,productImgList);
              }
               return new ProductExecution(ProductStateEnum.SUCCESS,product);
          }else{
              return new ProductExecution(ProductStateEnum.EMPTY);
          }

    }

    private void deleteProductImgList(Integer productId) {

        List<ProductImg>productImgList=productImgDao.queryProductImgList(productId);

        for(ProductImg productImg:productImgList){
            System.out.println("Imgaddr:"+productImg.getImgAddr());
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());

        }
        productImgDao.deleteProductImgByProductId(productId);

    }
    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImageList) throws ProductOperationException {
        if(product!=null&&product.getShop()!=null&&product.getShop().getShopId()!=null){
            product.setLastEditTime(new Date());

            if(thumbnail!=null){
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    System.out.println("Imgaddr:"+tempProduct.getImgAddr());
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addTumbnail(product,thumbnail);
            }
            if (productImageList != null && productImageList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImageList);
            }
            try{
                int effectedNum=productDao.updateProduct(product);
                if(effectedNum<0){
                    throw new ProductOperationException("更新商品失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("更新商品失败"+e.toString());
            }
            if(productImageList!=null&&productImageList.size()>0){
                addProductImgList(product,productImageList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else{
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex= PageCaculator.caculatorRowIndex(pageIndex,pageSize);
        List<Product>productList=productDao.queryProductList(productCondition,rowIndex,pageIndex);
        int count=productDao.queryProductCount(productCondition);
        ProductExecution pe=new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {

        String dest=PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg>productImgList=new ArrayList<ProductImg>();

        for(ImageHolder productImgHolder:productImgHolderList){

            String imgAddr=ImageUtil.generateNormalImg(productImgHolder,dest);
            ProductImg productImg=new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(Long.valueOf(product.getProductId()));
            productImg.setCreateTime(new Date());
            productImg.setImgDesc("test");

            productImgList.add(productImg);
        }
        if(productImgList.size()>0){
            try{
                int effectedNum=productImgDao.batchInsertProductImg(productImgList);
                if(effectedNum<=0){
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品详情图片失败"+e.toString());
            }





        }


    }

    private void addTumbnail(Product product, ImageHolder thumbnail) {
        String dest= PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr= ImageUtil.generateThumbnail(thumbnail,dest);
        product.setImgAddr(thumbnailAddr);
    }
}
