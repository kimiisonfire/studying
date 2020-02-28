<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script>
window.onload = function(){
	var num = "12345678";
	alert(num.format());	// 1234.56.78
}

String.prototype.format = function() {
	
	var num = this;
	var reg = /(\d{4})(\d{2})(\d{2})/;
	
	while ( reg.test(num) ) {
		num = num.replace(reg, '$1' + '.' + '$2' + '.' + '$3');
	}	
	
	return num;
}
</script> 


<h1>prototype.format</h1>  
<pre>
String.prototype.xxx => String 객체에 xxx라는 속성을 추가 
</pre>
<hr color="lightgrey">
<pre>
ex)
String.prototype.format = function() {
	
	var num = this;
	var reg = /(\d{4})(\d{2})(\d{2})/;
	
	while ( reg.test(num) ) {
		num = num.replace(reg, '$1' + '.' + '$2' + '.' + '$3');
	}	
	
	return num;
}

window.onload = function(){
	var num = "12345678";
	alert(num.format());	// 1234.56.78
}
</pre>


   

