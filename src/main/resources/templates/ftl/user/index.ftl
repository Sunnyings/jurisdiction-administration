<#include "/common/common.ftl">
<@heisoon js=["${domain}/static/js/heisoon-ajax.js","${domain}/static/js/util.js?2.0"]>
<body>
  <!--body star-->  
  <div class="layui-fluid">
     <!--row star-->
     <div class="layui-row layui-col-space10">
          <div class="layui-col-lg12">
              <div class="layui-card">
              <br>
              <br>
                  <div class="layui-card-header">权限列表</div>
                  <div class="layui-card-body">
                  <form class="layui-form" action="">
	                  <table class="search-box">
			             <tr>
			                 <td> 
			                     <span style="width:200px;"> 
			                       <input type="text" id="param" name="param" placeholder="权限名称" autocomplete="off" class="layui-input">   
			                     </span> 
			                     <a id="add" class="layui-btn layui-btn-primary"> 
			                           <i class="layui-icon">&#xe654;</i> 添加用户
			                      </a>
			                      <a lay-submit="" lay-filter="query" class="layui-btn layui-btn-primary"> 
			                           <i class="layui-icon">&#xe615;</i> 查询
			                      </a> 
			                 </td> 
			             </tr> 
			         </table> 
			        </form>
                  <!--content star-->
                     <table id="t_info" lay-filter="t_info" class="layui-table">
                    </table>
                     <script type="text/html" id="barDemo">   
                          <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="offline">删除</a>
                  </script>
                  <!--content end-->
                  </div>
              </div>
          </div>
          <!--col end-->
      </div>  
      <!--row end-->   
  </div> 
  
  <div id="openSaveModel" class="openSaveModel" style="display:none;">
<div class="layui-card-body">
  				<form class="layui-form" action="">
	                  <table class="search-box">
			             <tr>
			                 <td style="width:100%;"> 
								<form class="layui-form" method="post">
									<div class="layui-form-item">  
										<div class="layui-input-inline">
											<input type="text" name="username" style="width:100%;" lay-verify="required|password" placeholder="请输入用户名" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">  
										<div class="layui-input-inline">
											<input type="password" name="password" style="width:100%;" lay-verify="required|password" placeholder="请输入密码" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">  
										<div class="layui-input-inline">
											<input type="text" name="telephone" style="width:100%;" lay-verify="required|password" placeholder="请输入手机号码" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
									
										<div class="layui-input-inline" id="role" style="width:100%;">
											 
										</div>
									</div>

									<div class="layui-form-item">
										<button class="layui-btn" lay-submit="" lay-filter="addUser">用户添加</button>
									</div>   
									</form>
								</td> 
			             </tr> 
			         </table> 
			        </form>
  	</div>
              </div>
  <!--body end-->  
<script>
 
layui.use(['table','form','laydate'], function(){
   var table = layui.table
   	  ,$ = layui.$
   	 ,list_index
   	  ,form=layui.form
   	  ,laydate = layui.laydate;
   	  laydate.render({
      });
   	var tableIndex= table.render({
	      elem: '#t_info'
	      ,url:'${domain}/user/list'
	      ,cols: [[
	         {type:'numbers', width:60, title: '序号' }
	        ,{type:'userUuid', width:200, title: '用户uuid' ,templet:function(d){return d.userUuid;}}
	        ,{type:'username', width:200, title: '用户名称' ,templet:function(d){return d.username;}}
	        <#--  ,{type:'userNickName', width:200, title: '用户别名' ,templet:function(d){ return d.userNickName}}
	       ,{type:'email', width:200, title: '邮箱' ,templet:function(d){ return d.email}}  -->
	       ,{type:'telephone', width:200, title: '手机号码' ,templet:function(d){ return d.telephone}}
	       <#--  ,{type:'image', width:200, title: '头像' ,templet:function(d){ return d.image}}  -->
	       ,{type:'lastIp', width:200, title: '上次登陆IP' ,templet:function(d){ return d.lastIp}}
	       ,{type:'lastTime', width:200, title: '上次登陆时间' ,templet:function(d){return dateToStr(d.lastTime); }}
	        ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:250}
	      ]]
	       ,page:true
	       ,done:function(){
		   }
	    	})
   form.on('submit(query)', function(data){
	 	table.reload("t_info", {
                where:data.field                	
                ,page:true
		 });
	    return false;
	});
	 table.on('tool(t_info)', function (obj) {
		
		$.getJSON("${domain}/user/del",
		{
			"userUuid":obj.data.userUuid,
		}
		   ,function(result){
				   layer.msg(result.msg);
				   table.reload("t_info");
     		 });
      });
	$("#add").on("click",function(){
					$.ajax({
						type:"GET",
						url:"${domain}/role/getRoleList",
						dataType:"json",
						contentType:"application/json",
						data:"",
						success:function(data){
							$("#role").html(" ")
							$("#role").append("请选择角色：");
						$(data.data).each(function(i,item){
							$("#role").append("<input type='checkbox' name='roleUuids' title='"+item.roleRname+"' value='"+item.roleUuid+"' lay-skin='primary'>");
						})
						form.render('checkbox');
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
							layer.alert("服务器连接异常，请检查网络状态",{icon: 2, title:'提示'});
						}
					});
       list_index=layer.open({
		  type: 1,
		  title:"用户添加",
		  area: ['400px', '400px'],
		  content: $('#openSaveModel'),
		  end:function(){
		  	 $(".openSaveModel").hide();
		   },
		  success: function(layero,index){
		  }
    });
	});
	 var form = layui.form,$=layui.jquery,layer=layui.layer;
	  form.on('submit(addUser)', function(data){
		  quotation = new Array();
		$("input:checkbox[name='roleUuids']:checked").each(function(){
			quotation.push($(this).val());
		});
		data.field.roleUuids=quotation;
	    $.ajax({
            type:"POST",
            url:"${domain}/user/addUser",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data.field),
            success:function(data){
            console.log(data);
                layer.alert(data.msg,{icon: 1, title:'提示'});
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

