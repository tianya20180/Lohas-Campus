$(function () {
   var shopId=getQueryString('shopid');
   var shopInfoUrl='/ssm/shopadmin/getshopmanagementinfo?shopid='+shopId;
   alert(shopInfoUrl);
   $.getJSON(shopInfoUrl,function (data) {
       if(data.redirect){
           window.location.href=data.url;
       }else{
           if(data.shopId!=undefined&&data.shopId!=null){
               shopId=data.shopId;
           }
           $('#shopInfo').attr('href','/ssm/shopadmin/shopoperation?shopid='+shopId);
       }
   });
});