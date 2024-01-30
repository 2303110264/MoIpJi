<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
	String submitted = (String)request.getParameter("submitted");
	pageContext.setAttribute("submitted", submitted);
	
	request.setCharacterEncoding("utf-8");
	String location = (String)request.getParameter("location");
	pageContext.setAttribute("location", location);
%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a67c668a370d2a59b8fb993cbaf0456c&libraries=services"></script>
<script>
//위치 좌표값 받아오기
var chk = "${submitted}"
if(chk.length<2){
	
	navigator.geolocation.getCurrentPosition(function(pos) {
	var latitude = pos.coords.latitude;
    var longitude = pos.coords.longitude;
    
	const form = document.createElement('form'); 
	form.setAttribute('method', 'post'); 

/* 	
	const location = document.createElement('input');
	location.setAttribute('type','hidden');
	location.setAttribute('name', 'location');
	location.setAttribute('value', addr)
 */	
	const submitted = document.createElement('input'); 
	submitted.setAttribute('type', 'hidden'); 
	submitted.setAttribute('name', 'submitted'); 
	submitted.setAttribute('value', 'OK');
	
	const latX = document.createElement('input'); 
	latX.setAttribute('type', 'hidden'); 
	latX.setAttribute('name', 'latX');
	latX.setAttribute('value', latitude);
	
	const lonY = document.createElement('input');
	lonY.setAttribute('type', 'hidden');
	lonY.setAttribute('name', 'lonY');
	lonY.setAttribute('value', longitude);
	
	form.appendChild(latX);
	form.appendChild(lonY);
	form.appendChild(submitted);
	
	document.body.appendChild(form);
	
	
	function onSubmit(event){
		event.preventDefault();
	}
	form.addEventListener("submit", onSubmit)
	form.submit();      

});
	
}
</script>