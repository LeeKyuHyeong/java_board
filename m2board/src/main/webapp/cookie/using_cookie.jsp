<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>쿠키 값 확인</h1>
<%
	Cookie[] cookies = request.getCookies();		//배열 형태로 가져와서 쓴다.

	if(cookies != null){
		for(int i = 0; i < cookies.length; i++) {
			out.println("쿠키 이름 : " + cookies[i].getName() + ", ");
			out.println("쿠키 값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "<br> ");
		}
	}
%>
</body>
</html>