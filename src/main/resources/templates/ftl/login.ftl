<#include "/common/common.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>  
    <link rel="stylesheet" href="${domain}/static/css/login.css" media="all"> 
    <link rel="stylesheet" href="${domain}/static/layui/css/font-awesome.css" media="all"> 
    <script src="${domain}/static/layui/layui.js"></script>
    <script src="${domain}/static/js/layui-verify.js"></script>
    <script src="${domain}/static/js/superCoolTypingEffect.js"></script>
    
    <script>
		if(top.location != self.location){
			parent.location.href ='${domain}/login';
		}
	</script>
</head>
<body>
<!--body star-->
<!--top star-->	
<div class="container">
	<div class="panel_w">
	
		<table class="top_banner_box" border="0" cellspacing="0" cellpadding="0">
			<tr>
		    	<th><div class="logo"></div></th>
		        <th><div class="logo_txt" id="logo_txt"></div></th>
		    </tr>
		</table>
	</div>
	<script>
		typing('黑源素®空美联盟后台管理系统', 'logo_txt', 120);
	</script>

	
	<!--top end-->	
	<!--login star-->
	
	
	<div class="loginPanel0" id="loginPanel0">
	</div>
	
	
	<div class="loginPanel" id="loginPanel">
		<form class="layui-form" action="/login" method="post">
		
		    <div class="login_box layui-form-item">
		        <h1>帐号登录</h1>
		        <span style="color:red;font-size:15px;"><#if error_msg?exists>${error_msg}</#if></span>
		        <input type="text" name="username" lay-verify="required|username" placeholder="请输入帐号" />
		        <input type="password" name="password" lay-verify="required|password" placeholder="请输入密码" class="mt_btn" />
		        <br>
		       <table class="mt_btn_box">
		        	<tr>
		        	<span style="color:red;font-size:15px;"><#if checkinEerror_msg?exists>${checkinEerror_msg}</#if></span>
		        		<th><input type="text" name="LOGIN_CODE" lay-verify="required" placeholder="请输入验证码" id="mt_btn_code" /></th>
		        		<th><img id="mt_btn_code_img" src="/login_code" onclick="this.src='/login_code'"/></th>
		        	</tr>
		        </table>
		        <a lay-submit lay-filter="login" type="button" class="mt30">登录</a>
		    </div>
		</form>
	</div>
	<!--login end-->
	<!--版权信息 star-->
	<p class="copyright" id="copyright"></p>
	<script>
		typing('2019年  玖花行电子  版权所有  湘ICP备12345678号', 'copyright', 50);
	</script>
	<!--版权信息 end-->
</div>
<script>
/*
$(".what").click(function(){
	$('.loginPanel').css('display', 'none');
});
*/


layui.use(['form','jquery'], function(){
	  var form = layui.form,$=layui.jquery;
	  
	 form.on('submit(login)', function(data){
	    $(".layui-form").submit();
	 });
	 $('#loginPanel0').fadeOut(1000);
});
</script>
</body>
</html>