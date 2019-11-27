<#include "/common/common.ftl">
<@heisoon js=["${domain}/static/js/heisoon-ajax.js","${domain}/static/js/heisoon.msg.js"]>
<body>
  <!--body star-->  
  <div class="layui-fluid">
     <!--row star-->
     <div class="layui-row layui-col-space10">
          <!--col star-->
          <div class="layui-col-lg12">
              <div class="layui-card">
                  <div class="layui-card-header">系统首页</div>
                  <div class="layui-card-body">
                  <!--content star-->
                      <table class="home-table">
                           <tr>
                              <td>
                                  <span>基本信息</span>
                              </td>
                           </tr>
                           <tr>
                              <td>
                                  <span>帐号登录：</span><span>${USER_INFO_SESSION.account}${ROOT_INFO_SESSION.account}</span>
                                  <!-- <span class="ml">所属上级：</span><span>管理员</span> -->
                              </td>
                           </tr>
                           <tr>
                              <td>
                                   <span>上次登录时间：</span><span><#if USER_INFO_SESSION.lastTime?exists>${USER_INFO_SESSION.lastTime?string("yyyy-MM-dd HH:mm:ss")}<#else>--</#if><#if ROOT_INFO_SESSION.lastTime?exists>${ROOT_INFO_SESSION.lastTime?string("yyyy-MM-dd HH:mm:ss")}<#else>--</#if></span>
                                  <span class="ml">上次登录IP：</span><span><#if USER_INFO_SESSION.lastIp?exists>${USER_INFO_SESSION.lastIp}<#else>--</#if><#if ROOT_INFO_SESSION.lastIp?exists>${ROOT_INFO_SESSION.lastIp}<#else>--</#if></span>
                                   <#--  <span class="ml">上次登录城市：</span><span><#if USER_INFO_SESSION.lastCity?exists>${USER_INFO_SESSION.lastCity}<#else>--</#if></span>  -->
                              </td>
                           </tr>
                      </table>  
                      <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                          <legend>修改密码</legend>
                      </fieldset>
                      <form class="layui-form" method="post">
                      <div class="layui-form-item">  
                          <div class="layui-input-inline">
                             <input type="password" name="oldPass" lay-verify="required|password" placeholder="请输入原密码" class="layui-input">
                          </div>
                      </div>
                      <div class="layui-form-item">  
                          <div class="layui-input-inline">
                             <input type="password" name="newPass" lay-verify="required|password" placeholder="请输入新密码" class="layui-input">
                          </div>
                      </div>
                      <div class="layui-form-item">  
                          <div class="layui-input-inline">
                             <input type="password" name="newPass1" lay-verify="required|password" placeholder="请输入确认密码" class="layui-input">
                          </div>
                      </div> 
                      <div class="layui-form-item">
                        <button class="layui-btn" lay-submit="" lay-filter="updatePass">修改密码</button>
                      </div>   
                      </form>
                  <!--content end-->
                  </div>
              </div>
          </div>
          <!--col end-->
      </div>  
      <!--row end-->   
  </div>    
  <!--body end-->  
<script>
layui.use(['form','jquery'], function(){
	  var form = layui.form,$=layui.jquery,layer=layui.layer;
	  form.on('submit(updatePass)', function(data){
	  	if(data.field.newPass!=data.field.newPass1){
	  		layer.msg('两次输入的密码不一致', {
			  icon: 2
			});
	  		return false;
	  	}
	    $.ajax({
            type:"POST",
            url:"${domain}/sys/updatePassword",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data.field),
            success:function(data){
            console.log(data);
                debugger;
                layer.alert(data.msg,{icon: 1, title:'提示',cancel: function(){
	   					if(data.state=="SUCCESS"){
		                	location.href='${base}/logout';
		                }
					}}
				,function(i){
                	location.href='${base}/logout';
                });
                $('.layui-form')[0].reset();
　　		 		form.render();
            },
	        error: function(XMLHttpRequest, textStatus, errorThrown){
	        	layer.alert("服务器连接异常，请检查网络状态",{icon: 2, title:'提示'});
	        }
	    });
	    return false;
	 });
});
</script>
</body>
</@heisoon>
