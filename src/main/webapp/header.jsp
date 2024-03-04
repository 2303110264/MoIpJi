<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="kr.ac.kopo.member.vo.MemberVO"%>
<%@page import="kr.ac.kopo.weather.service.WeatherService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	
	MemberVO member = (MemberVO)session.getAttribute("member");
	//로그인 X
	if(member==null){
		member = new MemberVO();
		member.setMType("-1");
	}

	//메뉴리스트
	HashMap<String, String> menu = new LinkedHashMap<>();

	String requestURI = request.getRequestURI();
	if (requestURI.equals("/MoIpJi/forecast.jsp")) {
		menu.put("./index.jsp", "Nowcast");
	} else {
		menu.put("./forecast.jsp", "Forecast");
	}

	//사용자 체크
	switch(member.getMType()){
	case "-1":
		menu.put("./user/login.jsp", "Login");
		break;
	case "1":
		menu.put("404", "Admin");
	case "0":
		menu.put("../log/logList.jsp", "Log");
		menu.put("./logoutProcess.jsp", "Logout");
	default:
	}
	
	pageContext.setAttribute("menu", menu);
		
	pageContext.setAttribute("member", member);
%>
<nav class="dropdownmenu">
  <ul class="mainmenu">
   	<c:forEach items="${ menu }" var="m">
   		<li><a href="${ m.key }">${ m.value }</a></li>
	</c:forEach>
      <!-- 
      <ul id="submenu">
        <li><a href="#">ASS</a></li>
        <li><a href="#">BSS</a></li>
        <li><a href="#">CSS</a></li>
      </ul>
       -->
    <li><a href="#">Contact Me</a></li>
    <!-- 
     -->
  </ul>
</nav>
<!-- 
<a href="./forecast.jsp">
		<button>예보</button>
	</a>
	<a href="./user/login.jsp">
		<button>로그인</button>
	</a>

 -->
