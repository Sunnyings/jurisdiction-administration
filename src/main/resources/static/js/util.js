//获取当前窗口的有效可视宽度和高度
var getPageSize = function() {
	var winW, winH;
	if (window.innerHeight) { // 所有非IE浏览器
		winW = window.innerWidth;
		winH = $(window).height();
	} else if (document.documentElement
			&& document.documentElement.clientHeight) { // IE 6 Strict Mode
		winW = document.documentElement.clientWidth;
		winH = document.documentElement.clientHeight;
	} else if (document.body) { // 其他浏览器
		winW = document.body.clientWidth;
		winH = document.body.clientHeight;
	}
	return {
		WinW : winW, // 真正反馈的宽度
		WinH : winH
	// 真正反馈的高度
	};
};

// 获取url上的参数
var getParam = function(paramName) {
	var href = window.location.href;
	var pattern = /(?:\w+?)=(?:[^&]+)/ig, matches, parames = {};
	matches = href.match(pattern);
	if (matches) {
		for (var i = 0; i < matches.length; i++) {
			var parame = matches[i].split("=");
			parames[parame[0]] = parame[1];
		}
		for ( var name in parames) {
			if (name == paramName) {
				return parames[name].replace(/#.*/, "");
			}
		}
	}
	return null;
};

String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.isEmpty = function() {
	return this.trim() == "";
};
String.prototype.ifEmpty = function(t) {
	return this.isEmpty() ? t : this;
};
String.prototype.empty2null = function() {
	return this.isEmpty() ? null : this;
};
// 判断一个对象是否为json对象
function isJson(obj) {
	var isjson = typeof (obj) == "object"
			&& Object.prototype.toString.call(obj).toLowerCase() == "[object object]"
			&& !obj.length;
	return isjson;
}
Date.prototype.format = function(format) {
    var o = {
            "M+" : this.getMonth() + 1, //month
            "d+" : this.getDate(), //day
            "h+" : this.getHours(), //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3), //quarter
            "S"  : this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for ( var k in o) if (new RegExp("(" + k + ")").test(format)) {
        format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]: ("00" + o[k]).substr(("" + o[k]).length));
    }
    return format;
}

var dateToStr=function dateToStr(v) {
	     var date = new Date(v);
	    var y = date.getFullYear();
	    var m = date.getMonth()+1;
	    m = m<10?'0'+m:m;
	    var d = date.getDate();
	    d = d<10?("0"+d):d;
	    var h = date.getHours();
	    h = h<10?("0"+h):h;
	    var M = date.getMinutes();
	    M = M<10?("0"+M):M;
	    var str = y+"-"+m+"-"+d+" "+h+":"+M;
	    return str;
	     }
	

Array.prototype.remove=function(from, to) {
	  var rest = this.slice((to || from) + 1 || this.length);
	  this.length = from < 0 ? this.length + from : from;
	  return this.push.apply(this, rest);
};
	
function rate(fz,fm){
	return (fz/fm*100).toFixed(2)+'%';
}

var two_places=function(value){
	if(value){
		value=Math.round(parseFloat(value)*100)/100;
		var s=value.toString().split(".");
		if(s.length==1){
			value=value.toString()+".00";
			return value;
		}
		if(s.length>1){
			if(s[1].length<2){
				value=value.toString()+"0";
			}
			return value;
		}
	}
	return '0.00';
};


var checkInRound=function(round){
	return round==1?'早上打卡':round==2?'中午打卡':round==3?'晚上打卡':'错误';
};