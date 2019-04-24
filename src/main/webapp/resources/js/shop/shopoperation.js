$(function(){
  var initUrl='/ssm/shopadmin/getshopinitinfo';
  var registerShopUrl='/ssm/shopadmin/registershop';
  getShopInitInfo();
  alert(initUrl);
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
          url:registerShopUrl,
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