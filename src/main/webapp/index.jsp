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
	UltraSrtFNcstVO u = wd.xmlToUltraSrtVO(x,y);
	u = wd.readCode(u);
	
	u.setLocal("서울");
	String uPrint = u.print().replaceAll("\\r?\\n", "<br/>"); //개행문자 삽입
	
	pageContext.setAttribute("uPrint", uPrint);
	pageContext.setAttribute("u", u);
	pageContext.setAttribute("today", today.replaceAll("\\r?\\n", "<br/>"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MoIpJi-오늘 뭐 입지?</title>
</head>
<body>
	<h2>MoIpJi</h2>
	<h3>${ today }</h3>
	<hr>
	<div>
		해야할 것: 
		<br>1. xml형태로 넘어온 값을 처리하기 
		<input type="checkbox" checked="checked"/>
		<br>1-2. 날씨코드 해석해서 출력하기
		<br>2. 최근 3일까지. 3일 이후는 중기예보 (V2)로 처리해야함 
		<br>&nbsp;- 불러오는 값이 달라서 메서드 분리해야함
		<br>3. 파이팅.
	</div>
	<hr>
	<div>
		${ uPrint }
	</div>
	<hr>
	<h3>MEMO</h3>
	include: https://myvelop.tistory.com/10
</body>
</html>