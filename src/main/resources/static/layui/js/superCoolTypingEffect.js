
function typing(str, elementId, time){
	var i = 0;
	var divToDisplay = document.getElementById(elementId);
	
	var timer = setInterval(function(){
		divToDisplay.innerHTML = str.substring(0, i) + '_';
		i++;
		
		//alert("inner:"+divToDisplay.innerHTML.substring(0, i)+"\nstr:"+str);
		if(divToDisplay.innerHTML.substring(0, str.length) == str){
			divToDisplay.innerHTML = str;
			clearInterval(timer);
		}
	}, time);
}