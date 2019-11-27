layui.define([ 'form' ], function(t) {
	layui.form.verify({
		username : [ /^[a-zA-Z]|[\u0391-\uFFE5]{1}([a-zA-Z0-9\u0391-\uFFE5]|[._]){4,12}$/, '账号只能以字母开头以字母，下划线，数字组成最少4位最大12位!' ],
		password : function(value, item){
			if(value!="" && !/^[\S]{6,12}$/.test(value)){
		    	return '密码必须6到12位，且不能出现空格!';
		    }
		},
		mobile : [ /(^(13|14|15|16|18|17)\d{9}$)/, '请填写正确的手机号码!' ],
		tel : [ /^\d{3,4}-?\d{7,9}$/, '请填写正确的固定电话号码!' ],
		integer:[/^(-)?\d+$/,'只能输入正负整数!'],
		phone : function(value, item) { // value：表单的值、item：表单的DOM对象
			 var mobile = /(^(13|14|15|18|17)\d{9}$)|(^0(([1,2]\d)|([3-9]\d{2}))\d{7,8}$)/;    
			    var tel = /^\d{3,4}-?\d{7,9}$/;   
			    if(!tel.test(value) || !mobile.test(value)){
			    	return '请填写正确的联系电话!';
			    }
		},
		idCard : function(value,item){
			var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
		    var sId = value;
		    var iSum = 0
		    var sBirthday,boolean=true;
		    if(!/^\d{17}(\d|x)$/i.test(sId))
		    	boolean = false;
		    sId=sId.replace(/x$/i,"a");
		    if(aCity[parseInt(sId.substr(0,2))]==null)
		    	boolean = false;
		    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
		    var d=new Date(sBirthday.replace(/-/g,"/"))
		    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))boolean = false;
		    for(var i = 17;i>=0;i --){
		        iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
		    }
		    if(iSum%11!=1) 
		    	boolean = false;
		    if(!boolean)
		    	return "请填写正确的身份证号码!";
		}//,url : [/^(?=^.{3,255}$)(http(s)?:\/\/)?(www\.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+(:\d+)*(\/\w+\.\w+)*([\?&]\w+=\w*)*$/,'请输入正确的URL！']
	});
});