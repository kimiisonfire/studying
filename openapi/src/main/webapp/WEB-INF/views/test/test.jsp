<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <script src="<%=request.getContextPath() %>/resources/jquery.min.js" ></script>
</head>

<h1>푸라닭 가만안둬</h1>
<div id="tablediv" style="witdh:500px;height:500px;background-color:#f5f5f5;">
	
</div>


<script>
$(function(){
	// list 가져와서 뿌리기
	
	var data = {"jmCd" : "1320"};
	
	$.ajax({
		url  : "jmcdList",
		type : "get",
		async : false,
		data : data, 
		dataType : "json",
		success  : function(res) {
			var list = $(res.buffer).find("item").get();
			
			tag = "<p>시험명 "+ $(list[i]).find('jmfldnm').text() +"</p>";
			for (var i=0; i<list.length; i++ ) {
				tag += "<p>시험 회차 "+ $(list[i]).find('implplannm').text() +"</p>";
				tag += "<p>필기원서접수시작일자  "+ $(list[i]).find('docregstartdt').text() +"</p>";
				tag += "<p>필기원서접수종료일자  "+ $(list[i]).find('docregenddt').text() +"</p>";
				tag += "<br/>";
			}
			$('body #tablediv').append(tag);
		},
		error : function(error) {
			alert('error!');
		}
	})
})
</script>

