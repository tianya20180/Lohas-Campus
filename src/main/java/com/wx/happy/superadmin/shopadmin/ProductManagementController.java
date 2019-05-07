package com.wx.happy.superadmin.shopadmin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wx.happy.dto.ImageHolder;
import com.wx.happy.dto.ProductExecution;
import com.wx.happy.entity.Product;
import com.wx.happy.entity.ProductCategory;
import com.wx.happy.entity.Shop;
import com.wx.happy.enums.ProductStateEnum;
import com.wx.happy.exceptions.ProductOperationException;
import com.wx.happy.service.ProductCategoryService;
import com.wx.happy.service.ProductService;
import com.wx.happy.util.CodeUtil;
import com.wx.happy.util.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    private static final int IMAGEMAXCOUNT=6;

    @RequestMapping(value = "/getproductbyid",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object>getProductById(@RequestParam Long productId){

        Map<String,Object>modelMap=new HashMap<String, Object>();
        if (productId>-1){

            Product product=productService.getProductById(productId);

            if(product!=null){
                List<ProductCategory>productCategoryList=productCategoryService.getProductCategoryList(product.getShop().getShopId());
                modelMap.put("product",product);
                modelMap.put("productCategoryList",productCategoryList);
                modelMap.put("success",true);
            }
            else {
                modelMap.put("success",false);
                modelMap.put("errMsg","找不到该商品");
            }
        }
        else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty productId");
        }
        return modelMap;
    }


    @RequestMapping(value = "/addproduct",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object>addProduct(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();


        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("sceess",false);
            modelMap.put("errMsg","验证码输入错误");
            return modelMap;
        }

        ObjectMapper mapper=new ObjectMapper();
        Product product=null;
        String productStr= HttpServletUtil.getString(request,"productStr");
        MultipartHttpServletRequest multipartHttpServletRequest=null;//处理文件流
        ImageHolder thumbnail=null;
        List<ImageHolder> productImgList=new ArrayList<ImageHolder>();//保存商品详情图文件流列表
        CommonsMultipartResolver MultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());//获取文件流
        try {
            if(MultipartResolver.isMultipart(request)){//判断请求中是否存在文件流
                handleImg((MultipartHttpServletRequest) request, productImgList);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg","上传图片不能为空");
                return modelMap;
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }


        try {
           product=mapper.readValue(productStr,Product.class);

        } catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }

        if(product!=null&&thumbnail!=null&&productImgList.size()>0){
            try{
                Shop currentShop=(Shop)request.getSession().getAttribute("currentShop");
                Shop shop=new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);

                ProductExecution pe=productService.addProduct(product,thumbnail,productImgList);
                if(pe.getState()== ProductStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }


            }catch (ProductOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入商品信息");
        }

        return modelMap;
    }
    @RequestMapping(value = "/modifyproduct",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object>modifyProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        boolean statusChange = HttpServletUtil.getBoolean(request, "statusChange");
        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }

        ObjectMapper mapper = new ObjectMapper();

        Product product = null;
        String productStr = HttpServletUtil.getString(request, "productStr");
        MultipartHttpServletRequest multipartHttpServletRequest = null;//处理文件流
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();//保存商品详情图文件流列表
        CommonsMultipartResolver MultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());//获取文件流
        try {
            if (MultipartResolver.isMultipart(request)) {//判断请求中是否存在文件流
                handleImg((MultipartHttpServletRequest) request, productImgList);
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        try {
            product = mapper.readValue(productStr, Product.class);

        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        if (product != null) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);

                ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            }catch (ProductOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }


        }
        return modelMap;
    }
    private void handleImg(MultipartHttpServletRequest request, List<ImageHolder> productImgList) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest;
        ImageHolder thumbnail;
        multipartHttpServletRequest = request;
        CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
        thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());

        for (int i = 0; i < IMAGEMAXCOUNT; i++) {
            CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg" + i);
            if (productImgFile != null) {
                ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
                        productImgFile.getInputStream());
                productImgList.add(productImg);
            } else {
                break;
            }
        }
    }
    @RequestMapping(value = "/getproductlistbyshop",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object>getProductListByShop(HttpServletRequest request){
        Map<String,Object>modelMap=new HashMap<String,Object>();

        int pageIndex=HttpServletUtil.getInt(request,"pageIndex");
        int pageSize=HttpServletUtil.getInt(request,"pageSize");
        Shop currentShop=(Shop)request.getSession().getAttribute("currentShop");
        if((pageIndex>-1)&&(pageSize>-1)&&(currentShop!=null)&&(currentShop.getShopId()!=null)){
            long productCategoryId=HttpServletUtil.getLong(request,"productCategoryId");
            String productName=HttpServletUtil.getString(request,"productName");
            Product productCondition=compactProductCondition(currentShop.getShopId(),productCategoryId,productName);
            ProductExecution pe=productService.getProductList(productCondition,pageIndex,pageSize);
            modelMap.put("productList",pe.getProductList());
            modelMap.put("count",pe.getCount());
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or pageIndex or shopId");
        }

        return modelMap;


    }

    private Product compactProductCondition(Long shopId, long productCategoryId,String productName) {
            Product productCondition=new Product();
            Shop shop=new Shop();
            shop.setShopId(shopId);
            productCondition.setShop(shop);
            if(productCategoryId!=-1L){
                ProductCategory productCategory=new ProductCategory();
                productCategory.setProductCategoryId(productCategoryId);
                productCondition.setProductCategory(productCategory);
            }
            if(productName!=null){
                productCondition.setProductName(productName);
            }
            return productCondition;
    }

}
