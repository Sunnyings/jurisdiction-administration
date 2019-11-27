<#macro heisoon  js=[] css=[] title="黑源素|空姐配对管理系统" cache=false>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<#if !cache >
	<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate"> 
	<META HTTP-EQUIV="expires" CONTENT="0"> 
	</#if>
    <title>${title}</title>
	<link rel="stylesheet" href="${domain}/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${domain}/static/css/ui.css" media="all">
    <link rel="stylesheet" href="${domain}/static/build/css/app.css" media="all">
    <script src="${domain}/static/layui/layui.js"></script>
	
	<#list js as file>   		
	<script type="text/javascript" src="${file}"></script>
	</#list>
	<#list css as file>   		
	<link rel="stylesheet" type="text/css" href="${file}" />
	</#list>
	<script>
	var domain="${domain}";
	<#--
	<#if !isDeveloper>
    document.oncopy=function(){return false;};//禁止复制
    </#if>
	-->
	</script>
	<#--
	<style>
	.layui-table-tips-main{display:none}
	.layui-table-tips-c{display:none}
	</style>
	-->
	<style>
	.layui-table img {width: 40px;}
	</style>
</head>
<#nested>
</html>
</#macro>