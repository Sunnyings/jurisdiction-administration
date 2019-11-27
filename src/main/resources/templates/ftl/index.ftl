<#include "/common/common.ftl">
<@heisoon css=["/static/layui/css/font-awesome.css"]>
<body>
	<!--body star-->
    <div class="layui-layout layui-layout-admin kit-layout-admin">
    	<!--header star-->
        <div class="layui-header hl-header">
            <div class="hl-logo">|&nbsp;黑源素</div>
            <ul class="hl-nav" kit-navbar>  
                <li>
                       <span>${USER_INFO_SESSION.account}${ROOT_INFO_SESSION.account}</span> 
                       <span class="hl-nav-more"></span> 
                </li>
                <li>
                    <a href="${domain}/logout"><i class="fa fa-sign-out" aria-hidden="true"></i>退出</a>
                </li>
            </ul> 
        </div>
        <!--header end-->
        
        <!--sidebox star-->
        <div class="layui-side layui-bg-black kit-side">
            <div class="layui-side-scroll">
                <!-- <div class="kit-side-fold"><i class="fa fa-list" aria-hidden="true"></i></div>  
                <!-- 左侧导航区域 star -->
                <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar lay-shrink="all">   
                	<#if result.map.tops?exists>
                	<#list result.map.tops as t>
                	 <li class="layui-nav-item">
                        <a class="" href="javascript:;"><i class="fa fa-calendar fa-fw" aria-hidden="true"></i><span>${t.menuName}</span></a>
                        <dl class="layui-nav-child"> 
                        <#if result.map.chidren?exists>
                        <#list result.map.chidren as c>
                        	<#if c.pid=t.id>
                            <dd>
                                <a href="javascript:;" data-url="${domain}${c.url}" data-icon="" data-title="${c.menuName}" kit-target data-id='${c_index}' lay-id='${c_index}'>
                                     <span>${c.menuName}</span>
                                </a>
                            </dd> 
                            </#if>
                         </#list>
                         </#if>
                        </dl>
                    </li>
                    </#list>
                	</#if>
                </ul>
            </div>
        </div>
        <!--sidebox end-->
        
        <!-- content star -->
        <div class="layui-body" id="container">   
            <div style="padding: 15px;">主体内容加载中,请稍等...</div>
        </div>
        <!-- content end -->
        
        <!-- <div class="layui-footer">copyright</div>   -->
      
    </div>
    <!-- body end -->
    <script src="/static/layui/layui.js"></script>
    <script>
        var message,element;
        layui.config({
            base: '/static/build/js/'
        }).use(['app', 'message','element'], function() {
            var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
            //将message设置为全局以便子页面调用
            message = layui.message;
            element = layui.element;
            //主入口
            app.set({
                type: 'iframe'
            }).init();           
        });
    </script>
</body>
</@heisoon>
