<%@page import="kr.ac.kopo.weather.vo.UltraSrtFNcstVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.ac.kopo.weather.service.WeatherService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	String nx = (String)request.getParameter("latX");
	String ny = (String)request.getParameter("lonY");

	WeatherService ws = new WeatherService();
	List<UltraSrtFNcstVO> flist = ws.getWeatherList(nx, ny);
	
	pageContext.setAttribute("flist", flist);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 페이지 이동 없이 jsp include부분만 바꿔도 될지도. -->
	<jsp:include page = "location.jsp"></jsp:include>
	<jsp:include page = "./header.jsp"></jsp:include>
	<a href="./index.jsp">
		<button>현재</button>
	</a>
	
	<table border ="1" style="width:80%">
			<tr>
				<th width="5%">x</th>
				<th width="5%">y</th>
				<th width="10%">baseDate</th>
				<th width="10%">baseTime</th>
				<th width="10%">PTY</th>
				<th width="10%">RNI</th>
				<th width="10%">TIH</th>
				<th width="10%">REH</th>
				<th width="10%">WSD</th>
				<th width="10%">LGT</th>
				<th width="10%">SKY</th>
			</tr>
			<c:forEach items="${ flist }" var="vo">
				<tr>
<!-- 
					<td>
						<a href="detail.jsp?no=${ b.getNo() }">
							<c:out value="${ b.getTitle() }"/>
						</a>
					</td>
 -->						
					<td>${ vo.getX() }</td>
					<td>${ vo.getY() }</td>				
					<td>${ vo.getBaseDate() }</td>				
					<td>${ vo.getBaseTime() }</td>				
					<td>${ vo.getPTY() }</td>				
					<td>${ vo.getRN1() }</td>				
					<td>${ vo.getT1H() }</td>				
					<td>${ vo.getREH() }</td>				
					<td>${ vo.getWSD() }</td>				
					<td>${ vo.getLGT() }</td>				
					<td>${ vo.getSKY() }</td>				
				</tr>
			</c:forEach>
		</table>



</body>
	<jsp:include page = "./footer.jsp"></jsp:include>
</html>