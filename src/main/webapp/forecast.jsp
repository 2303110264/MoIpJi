<%@page import="kr.ac.kopo.weather.vo.UltraSrtFNcstVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.ac.kopo.weather.service.WeatherService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	String latX = (String)request.getParameter("latX");
	String lonY = (String)request.getParameter("lonY");

	WeatherService ws = new WeatherService();
	List<UltraSrtFNcstVO> flist = ws.getUltraSrtFcst(latX, lonY);
	
	pageContext.setAttribute("flist", flist);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forecast</title>
</head>
<body>
<!-- 페이지 이동 없이 jsp include부분만 바꿔도 될지도.
모른다고 생각했던 적이 있었다. javascript로 jsp를 손대지 못한다는 걸 늦게 알았다. -->
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
			<c:forEach items="${ flist }" var="v">
				<tr>
<!-- 
					<td>
						<a href="detail.jsp?no=${ b.getNo() }">
							<c:out value="${ b.getTitle() }"/>
						</a>
					</td>
 -->						
					<td>${ v.getX() }</td>
					<td>${ v.getY() }</td>				
					<td>${ v.getBaseDate() }</td>				
					<td>${ v.getBaseTime() }</td>				
					<td>${ v.getPTY() }</td>				
					<td>${ v.getRN1() }</td>				
					<td>${ v.getT1H() }</td>				
					<td>${ v.getREH() }</td>				
					<td>${ v.getWSD() }</td>				
					<td>${ v.getLGT() }</td>				
					<td>${ v.getSKY() }</td>				
				</tr>
			</c:forEach>
		</table>



</body>
	<jsp:include page = "./footer.jsp"></jsp:include>
</html>