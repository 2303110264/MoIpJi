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
	String loc = ws.getLocation(nx, ny);
	
	pageContext.setAttribute("u", u);
	pageContext.setAttribute("loc", loc);
	
%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a67c668a370d2a59b8fb993cbaf0456c&libraries=services">
</script>
<div class="weatherdiv">
<hr>
	${ loc }
	<br><img src="${ u.getPTY() }" width="50px"/>
	<br>기온 : ${ u.getT1H() }
	<br>습도 : ${ u.getREH() }
	<br>풍속 : ${ u.getWSD() }
</div>