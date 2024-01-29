<%@page import="kr.ac.kopo.weather.service.WeatherService"%>
<%@page import="kr.ac.kopo.weather.vo.UltraSrtFNcstVO"%>
<%@page import="kr.ac.kopo.weather.dao.UltraSrtFNcstDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String nx = (String)request.getParameter("latX");
	String ny = (String)request.getParameter("lonY");

	WeatherService ws = new WeatherService();
	UltraSrtFNcstVO u = ws.getUltraSrtNcst(nx, ny);
	
	//String uPrint = u.print().replaceAll("\\r?\\n", "<br/>"); //개행문자 삽입
	//pageContext.setAttribute("uPrint", uPrint);	
	pageContext.setAttribute("u", u);
	pageContext.setAttribute("nx", nx);
	pageContext.setAttribute("ny", ny);
%>
<script>

</script>
<hr>
<div>
	지역 : ${ u.getX() }, ${ u.getY() }/${ nx } ${ ny }
	<br>날씨 : <img src="${ u.getPTY() }" width="50px"/>
	<br>기온 : ${ u.getT1H() }
	<br>습도 : ${ u.getREH() }
	<br>풍속 : ${ u.getWSD() }
</div>