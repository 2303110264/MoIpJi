<%@page import="kr.ac.kopo.weather.service.WeatherService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");
	String y = (String)request.getParameter("lonY");
	pageContext.setAttribute("y", y);
	String x = (String)request.getParameter("latX");
	pageContext.setAttribute("x", x);

	Date currentTime = new Date();
	String today = new SimpleDateFormat("yyyy년 MM월 dd일\nHH시 mm분").format(currentTime).toString();
	
	// javascripit로 시계 구현 (후순위)
	pageContext.setAttribute("today", today.replaceAll("\\r?\\n", "<br/>"));
	WeatherService s = new WeatherService();	
	String ss = s.getLocation(x, y);
	
	pageContext.setAttribute("ss", ss);
%>
<h3>${ today }</h3>
<h4>${ x }</h4>
<h4>${ ss }</h4>

