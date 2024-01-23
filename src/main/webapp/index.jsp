<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 무한 submit 방지용
	request.setCharacterEncoding("utf-8");
	String submitted = (String)request.getParameter("submitted");
	pageContext.setAttribute("submitted", submitted);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MoIpJi-오늘 뭐 입지?</title>
</head>
<script type="text/javascript">
	var check = "${submitted}";
	
</script>
<body>
	<!-- 
	 -->
	<jsp:include page = "location.jsp"></jsp:include>
	<jsp:include page = "header.jsp"></jsp:include>
	<a href="./forecast.jsp">
		<button onclick="NcstToFcst()">예보</button>
	</a>
	
	<hr>
	<div>
		해야할 것: 
		<br>1. xml형태로 넘어온 값을 처리하기 
		<input type="checkbox" checked="checked"/>
		(끝나고 시간 남으면 json도 해볼것)
		<br>1-2. 날씨코드 해석해서 출력하기
		<input type="checkbox" checked="checked"/>
		<br>2. 위치값 받아와야지!!!
		<input type="checkbox" checked="checked"/>
		<br>3. 예보값 받아와서 1~1-2 과정 진행하기
		<br>4. 회원가입과 로그인 구현
		<br>?. 최근 3일까지. 3일 이후는 중기예보 (V2)로 처리해야함 
		<br>&nbsp;- 불러오는 값이 달라서 메서드 분리해야함
		<br>0. 파이팅.
	</div>
	<jsp:include page = "weather.jsp"></jsp:include>
	<jsp:include page = "cloth.jsp"></jsp:include>
	<hr>
	<h3>MEMO</h3>
	카카오톡으로 로그인하기: https://developers.kakao.com/tool/demo/login/login
	<br>사용된 아이콘: https://www.flaticon.com/
</body>
<jsp:include page = "footer.jsp"></jsp:include>
</html>