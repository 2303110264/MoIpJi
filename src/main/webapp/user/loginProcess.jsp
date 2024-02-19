<%@page import="java.net.URLEncoder"%>
<%@page import="kr.ac.kopo.member.vo.MemberVO"%>
<%@page import="kr.ac.kopo.member.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//한글 깨짐 방지
	request.setCharacterEncoding("utf-8");
	// 파라미터 추출(id, pw)
	String id = (String)request.getParameter("id");
	String pw = (String)request.getParameter("password");
	String rememberMe = request.getParameter("rememberMe");
	
	MemberService ms = new MemberService();

	boolean passwordCheck = ms.passwordCheck(id, pw);
	if(passwordCheck){
		MemberVO m = ms.login(id);
		session.setAttribute("member", m);
		if(rememberMe!=null){
			//쿠키 생성-등록
			String cookieID = URLEncoder.encode(id, "utf-8");
			rememberMe = URLEncoder.encode(rememberMe, "utf-8");
			Cookie c = new Cookie(rememberMe, cookieID);
			c.setMaxAge(24*3600); //24시간
			response.addCookie(c);
		}
		response.sendRedirect("../index.jsp");
	}
	pageContext.setAttribute("passwordCheck", passwordCheck);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login...</title>
</head>
<script>
	let a = ${ passwordCheck }
	if(a){
		alert('환영합니다!\n로그인이 완료되었습니다.')
		location.href = "./login.jsp"
	}else{
		alert('아이디 또는 비밀번호가 일치하지 않습니다.')
		location.href = "./login.jsp"
	}
</script>
<body>

</body>
</html>