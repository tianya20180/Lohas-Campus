package com.wx.happy.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
         //正确的验证码
        String verifyCodeExcepted=(String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //输入的验证码
        String verifyCodeActual=HttpServletUtil.getString(request,"verifyCode");
        if(verifyCodeActual==null||!verifyCodeActual.equals(verifyCodeExcepted)){
            return false;
        }
        return true;


    }



}
