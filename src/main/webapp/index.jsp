<%@page import="kr.ac.kopo.weather.api.IApi"%>
<%@page import="kr.ac.kopo.weather.api.ApiExplorer01"%>
<%@page import="kr.ac.kopo.weather.api.ApiExplorer02"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	IApi a = new ApiExplorer01();
	String s = a.api();
	pageContext.setAttribute("s", s);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Main Title</h2>
	<div>
		해야할 것: 
		<br>1. xml형태로 넘어온 값을 처리하기 (30%)
		<br>2. 최근 3일까지. 3일 이후는 중기예보 (V2)로 처리해야함 - 따로 부르거나 부르지 않거나 인터페이스 만들기
		<br>3. 파이팅.
	</div>
	<hr>
	<div>
		${ s }
	</div>
	<hr>
	<h3>MEMO</h3>
	include: https://myvelop.tistory.com/10
</body>
</html>