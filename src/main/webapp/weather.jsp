<%@page import="kr.ac.kopo.weather.vo.UltraSrtFNcstVO"%>
<%@page import="kr.ac.kopo.weather.dao.WeatherDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    WeatherDAO wd = new WeatherDAO();
	String x = "55";
	String y = "127";
	UltraSrtFNcstVO u = wd.xmlToUltraSrtVO(x,y);
	u = wd.readCode(u);
	
	u.setLocal("서울");
	String uPrint = u.print().replaceAll("\\r?\\n", "<br/>"); //개행문자 삽입
	
	pageContext.setAttribute("uPrint", uPrint);
	pageContext.setAttribute("u", u);
%>
<hr>
<div>
	지역 : ${ u.getLocal() }
	<br>날씨 : <img src="${ u.getPTY() }" width="50px"/>
	<br>기온 : ${ u.getT1H() }
	<br>습도 : ${ u.getREH() }
	<br>풍속 : ${ u.getWSD() }
</div>