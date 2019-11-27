layui.define(['jquery','layer'],function(t){
(function($) {
	var $_ajax = $.ajax
		,layer = layui.layer;
	$.ajax = function(options) {
		var originalSuccess, mySuccess, success_context
		,originalBeforeSend,myBeforeSend;
		if (options.success) {
			originalSuccess = options.success;
			success_context = options.context ? options.context : $;
			mySuccess = function(data) {
				if (data.code!=0) {
					if(data.uri){
						layui.layer.alert(data.msg,{icon: 2, title:'提示'},function(t){
							location.href =domain+data.uri;
							layer.close(t);
						})
						
					}else{
						layui.layer.alert(data.msg,{icon: 2, title:'提示'});
					}
					layer.closeAll('loading');
					return;
				}
				originalSuccess.apply(success_context, arguments);
			};
			options.success = mySuccess;
		}
		$_ajax.apply($, arguments);
	};
	$.ajaxSetup({
        beforeSend:function(XMLHttpRequest){
        	layer.load();
        }
		,complete: function (XHR, TS) {
		  	layer.closeAll('loading');
        }
    });
})(layui.jquery);
});