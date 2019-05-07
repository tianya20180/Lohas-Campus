package com.wx.happy.superadmin.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/shopadmin",method ={RequestMethod.GET})
public class shopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement(){
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanage",method = RequestMethod.GET)
    private String productCategoryManage(){
        return "shop/productcategorymanage";
    }

    @RequestMapping(value = "/productmanage")
    public String productManage(){
        return "shop/productmanage";
    }

    @RequestMapping(value = "/productedit")
    public String productEdit(){
        return "shop/productedit";
    }
}
