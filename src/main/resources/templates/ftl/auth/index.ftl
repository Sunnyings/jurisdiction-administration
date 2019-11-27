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
			                           <i class="layui-icon">&#xe654;</i> 添加权限
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
			                 <td> 
			                 <div class="layui-form-item">
						  			<input type="text" name="resourcesName" id="resourcesName" required lay-verify="required" placeholder="权限名称" autocomplete="off" class="layui-input">    
						  	</div>
						  	<div class="layui-form-item">
						  			<input type="text" name="resourcesUrl"  id="resourcesUrl" required lay-verify="required" placeholder="权限地址" autocomplete="off" class="layui-input">    
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
	      ,url:'${domain}/auth/list'
	      ,cols: [[
	         {type:'numbers', width:60, title: '序号' }
	        ,{type:'resourcesName', width:200, title: '权限名称' ,templet:function(d){return d.resourcesName;}}
	        ,{type:'resourcesUrl', width:200, title: '权限地址' ,templet:function(d){return d.resourcesUrl;}}
	        ,{type:'creationTime', width:200, title: '创建时间' ,templet:function(d){ return dateToStr(d.creationTime);}}
	        ,{type:'updateTime', width:200, title: '修改时间' ,templet:function(d){return dateToStr(d.updateTime);}}
	        ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:250}
	      ]]
	       ,page:true
	       ,done:function(){
		   }
	    	})

			 //监听工具条
     table.on('tool(t_info)', function (obj) {
		
		$.getJSON("${domain}/auth/del",
		{
			"resourcesUrl":obj.data.resourcesUrl,
			"resourcesUuid":obj.data.resourcesUuid
		}
		   ,function(result){
				   layer.msg(result.msg);
				   table.reload("t_info");
     		 });
      });
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
		  title:"权限添加",
		  area: ['300px', '300px'],
		  content: $('#openSaveModel'),
		  end:function(){
		  	 $(".openSaveModel").hide();
		   },
		  success: function(layero,index){
		  }
    });
	});
	 form.on('submit(save)', function(data){
		   $.getJSON("${domain}/auth/add",
				   {
			   	 "resourcesName":data.field.resourcesName
			    ,"resourcesUrl":data.field.resourcesUrl
				   }
		   ,function(result){
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

