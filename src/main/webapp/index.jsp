<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@page import="kr.ac.kopo.weather.vo.UltraSrtFNcstVO"%>
<%@page import="kr.ac.kopo.weather.dao.WeatherDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	Date currentTime = new Date();
	String today = new SimpleDateFormat("yyyy년 MM월 dd일\nHH시 mm분").format(currentTime).toString();
	WeatherDAO wd = new WeatherDAO();

	String x = "55";
	String y = "127";
	UltraSrtFNcstVO u = wd.GPT(x,y);
	pageContext.setAttribute("u", u);
	pageContext.setAttribute("today", today);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Main Title</h1>
	<h3>${ today }</h3>
	<hr>
	<div>
		해야할 것: 
		<br>1. xml형태로 넘어온 값을 처리하기 (80%)
		<br>2. 최근 3일까지. 3일 이후는 중기예보 (V2)로 처리해야함 - 따로 부르거나 부르지 않거나 인터페이스 만들기
		<br>3. 파이팅.
	</div>
	<hr>
	<div>
		${ u.toString() }
	</div>
	<hr>
	<h3>MEMO</h3>
	include: https://myvelop.tistory.com/10
</body>
</html>