<%@page import="kr.ac.kopo.member.service.MemberService"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.ac.kopo.member.vo.MemberVO" %>
<%

	//한글 깨짐 방지
	request.setCharacterEncoding("utf-8");
	// 파라미터 추출(id, pw)
	String id = (String)request.getParameter("id");
	String mail = (String)request.getParameter("mail");
	String pw = (String)request.getParameter("pw");
	String nick = (String)request.getParameter("nickname");
	String gender = (String)request.getParameter("gender");
	String birth = (String)request.getParameter("birthday");
	
	MemberService ms = new MemberService();
	
	String salt = ms.createSalt();
	pw = ms.Hashing(pw, salt);
	MemberVO m = new MemberVO(id, mail, pw, nick, gender, birth, salt);
	
	boolean joinCheck = ms.join(m);
	if(joinCheck){
		session.setAttribute("member", m);
	}
	pageContext.setAttribute("joinCheck", joinCheck);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loading...</title>
<script>
	let a = ${ joinCheck }
	if(a){
		alert('환영합니다!\n가입이 완료되었습니다.')
		location.href = "./login.jsp"
	}else{
		alert('문제가 발생했습니다.\n가입을 다시 진행해주세요.')
		location.href = "./login.jsp"
	}
</script>
</head>
<body>
</body>
</html>