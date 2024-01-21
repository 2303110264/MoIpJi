<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	Date currentTime = new Date();
	String today = new SimpleDateFormat("yyyy년 MM월 dd일\nHH시 mm분").format(currentTime).toString();
	
	pageContext.setAttribute("today", today.replaceAll("\\r?\\n", "<br/>"));
%>
<h2>
	<a style="text-decoration:none; color:black" href="./index.jsp">
	MoIpJi
	</a>
</h2>
<h3>${ today }</h3>