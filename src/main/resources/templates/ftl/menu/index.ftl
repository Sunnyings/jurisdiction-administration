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
                  <div class="layui-card-header">菜单添加</div>
                  <div class="layui-card-body">
                  <form class="layui-form" action="">
	                  <table class="search-box">
			             <tr>
			                 <td> 
			                     <span style="width:200px;"> 
			                       <input type="text" id="param" name="param" placeholder="角色名称" autocomplete="off" class="layui-input">   
			                     </span> 
			                     <a id="add" class="layui-btn layui-btn-primary"> 
			                           <i class="layui-icon">&#xe654;</i> 添加菜单
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
  				<div class="layui-tab layui-tab-card">
					<ul class="layui-tab-title">
						<li class="layui-this">一级菜单</li>
						<li>二级菜单</li>
					</ul>
					<div class="layui-tab-content" style="height: 250px;">
						<div class="layui-tab-item">
							<form class="layui-form" action="">
								<table class="search-box">
									<tr>
										<td> 
										<div class="layui-form-item">
												<input type="text" name="menuName" id="menuName" required lay-verify="required" placeholder="请输入一级菜单名称" autocomplete="off" class="layui-input">    
										</div>
												<a lay-submit="" lay-filter="save" class="layui-btn layui-btn-primary"> 
												<i class="layui-icon">&#xe605;</i> 提交
											</a>
											</td> 
									</tr> 
								</table> 
							</form>
						</div>
						<div class="layui-tab-item">
							<form class="layui-form" action="">
								<table class="search-box">
									<tr>
										<td> 
										<div class="layui-form-item layui-show">
												<select name="pid"  id="menu" lay-verify="">
														<option value="">一级菜单请选择</option>
												</select>
										</div>
										<div class="layui-form-item">
												<input type="text" name="menuName"  id="menuName" required lay-verify="required" placeholder="请输入二级菜单名称" autocomplete="off" class="layui-input">    
										</div>
										<div class="layui-form-item">
												<input type="text" name="url"  id="url" required lay-verify="required" placeholder="请输入二级菜单地址" autocomplete="off" class="layui-input">    
										</div>
												<a lay-submit="" lay-filter="save" class="layui-btn layui-btn-primary"> 
												<i class="layui-icon">&#xe605;</i> 提交
											</a>
											</td> 
									</tr> 
								</table> 
								</form>
						</div>
					</div>
					</div>
  		</div>
     </div>
  <!--body end-->  
<script>
function renderForm(){
  layui.use('form', function(){
   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
   form.render();
  });
 }
layui.use(['table','form','laydate','element'], function(){
   var table = layui.table
   	  ,$ = layui.$
   	 ,list_index
   	  ,form=layui.form
	  ,element=layui.element
   	  ,laydate = layui.laydate;
   	  laydate.render({
      });
   	var tableIndex= table.render({
	      elem: '#t_info'
	      ,url:'${domain}/menu/list'
	      ,cols: [[
	         {type:'numbers', width:60, title: '序号' }
	        ,{type:'menuUuid', width:200, title: '菜单uuid' ,templet:function(d){return d.menuUuid;}}
	        ,{type:'levelMenu', width:200, title: '上级菜单名称' ,templet:function(d){return d.levelMenu;}}
	        ,{type:'menuName', width:200, title: '菜单名称' ,templet:function(d){return d.menuName;}}
	        ,{type:'url', width:200, title: '菜单地址' ,templet:function(d){ return d.url;}}
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
	$("#add").on("click",function(){
       list_index=layer.open({
		  type: 1,
		  title:"菜单添加",
		  area: ['600px', '400px'],
		  content: $('#openSaveModel'),
		  end:function(){
		  	 $(".openSaveModel").hide();
		   },
		  success: function(layero,index){
			  $.getJSON("${domain}/menu/getAllTopMenu",{},function(result){
			   	  $("#menu").find("option").remove();
				  $("#menu").append('<option value="">一级菜单请选择</option>');
				  $.each(result.data,function(i,item){
				  $("#menu").append('<option value='+item.id+'>'+item.menuName+'</option>');
				  renderForm();
     		 });
			  })
		  }
    });
	});
	 form.on('submit(save)', function(data){
		   $.getJSON("${domain}/menu/add"
		   ,data.field,function(result){
			   	   console.log(result)
				   layer.msg(result.msg);
				   layer.close(list_index);
				   table.reload("t_info");
				   layer.closeAll(); 
     		 });
		});
	});
</script>    
</body>
</@heisoon>