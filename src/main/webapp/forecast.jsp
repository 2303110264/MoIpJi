<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 페이지 이동 없이 jsp include부분만 바꿔도 될지도. -->
	<jsp:include page = "./header.jsp"></jsp:include>
	<a href="./index.jsp">
		<button>현재</button>
	</a>
	




	<jsp:include page = "./cloth.jsp"></jsp:include>
</body>
	<jsp:include page = "./footer.jsp"></jsp:include>
</html>