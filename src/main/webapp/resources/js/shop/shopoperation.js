$(function(){
  var shopId=getQueryString('shopid');
  var isEdit=shopId?true:false;

  var initUrl='/ssm/shopadmin/getshopinitinfo';
  var registerShopUrl='/ssm/shopadmin/registershop';
  var shopInfoUrl="ssm/shoapadmin/getshopbyid?shopid="+shopId;
  var editShopUrl="ssm/shoapadmin/modifyshop";

if(!isEdit){
    getShopInitInfo();
}else{
    getShopInfo(shopId);
}
    function getShopInfo(shopId) {
        $.getJSON(initUrl,function (data) {
          if(data.success) {
              var shop = data.shop;

              alert(shop.shopName);
              $('#shop-name').val(shop.shopName );
              $('#shop-addr').val(shop.shopAddr );
              $('#shop-phone').val(shop.phone );
              $('#shop-desc').val(shop.shopDesc );

              var shopCategory = '<option data-id='
                  + shop.shopCategory.shopCategoryId + '"selected>'
                  + shop.shopCategory.shopCategoryName + '';


              var tempAreaHtml = '';
              data.areaList.map(function (item, index) {
                  tempAreaHtml += '<option data-id="' + item.areaId + '">'
                      + item.areaName + '</option>';
              });

              $('#shop-category').html(shopCategory);
              $('#shop-category').attr('disabled','disabled');
              $('#area').html(tempAreaHtml);
              $("#area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");

          }
      });


  }
  function getShopInitInfo() {
      $.getJSON(initUrl,function (data) {
          if(data.success){
              var tempHtml='';
              var tempAreaHtml='';
              data.shopCategoryList.map(function (item,index){
                tempHtml+='<option data-id="'+item.shopCategoryId+'">'
                +item.shopCategoryName+'</option>';
              });
              data.areaList.map(function (item,index){
                  tempAreaHtml+='<option data-id="'+item.areaId+'">'
                      +item.areaName+'</option>';
              });
              $('#shop-category').html(tempHtml);
              $('#shop-area').html(tempAreaHtml);
          }

      });
  }


  $("#submit").click(function () {
      var shop={};
      if(isEdit){
          shop.shopId=shopId;
      }
      shop.shopName=$('#shop-name').val();
      shop.shopAddr=$('#shop-addr').val();
      shop.phone=$('#shop-phone').val();
      shop.shopDesc=$('#shop-desc').val();
      shop.shopCategory={
          shopCategoryId:$('#shop-category').find('option')
              .not(function () {
                  return !this.selected;
              }).data('id')
      };
      shop.area={
          areaId:$('#shop-area').find('option')
              .not(function () {
                  return !this.selected;
              }).data('id')
      };


      var shopImg=$('#shop-img')[0].files[0];
      var formData=new FormData();
      formData.append('shopImg',shopImg);
      formData.append('shopStr',JSON.stringify(shop));

      var verifyCode=$('#j_captcha').val();
      if(!verifyCode){
          alert('请输入验证码');
          return;
      }
      formData.append('verifyCode',verifyCode);
      $.ajax({
          url:(isEdit?editShopUrl:registerShopUrl),
          type:'POST',
          data:formData,
          contentType:false,
          processData:false,
          cache:false,
          success:function(data){
              if(data.success){
                  alert('提交成功');
              }else{
                  alert('提交失败!'+data.errorMsg);
              }
              $('#captcha_img').click();
          }

      });
  });
})