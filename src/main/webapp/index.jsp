<%@page import="kr.ac.kopo.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO)session.getAttribute("member");
	//로그인 X
	if(member==null){
		member = new MemberVO();
		member.setMType("-1");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MoIpJi-오늘 뭐 입지?</title>
<link rel="stylesheet" href="./css/imshi.css" />
<link rel="stylesheet" href="./css/clock.css">
</head>
<body>
	<jsp:include page = "location.jsp"></jsp:include>
	<div id="clock">
	<jsp:include page = "./header/clock.jsp"></jsp:include>
	</div>
	<jsp:include page = "header.jsp"></jsp:include>
	<!--
	-->
	<jsp:include page = "weather.jsp"></jsp:include>
	<jsp:include page = "cloth.jsp"></jsp:include>
	<hr>
	<h3>MEMO</h3>
	카카오톡으로 로그인하기: https://developers.kakao.com/tool/demo/login/login
	<br>사용된 아이콘: https://www.flaticon.com/
</body>
<jsp:include page = "footer.jsp"></jsp:include>
</html>