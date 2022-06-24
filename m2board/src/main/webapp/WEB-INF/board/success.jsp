<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css" type="text/css">
</head>
<body>
<table border="1">
	<caption>회원 가입 성공</caption>
<tr>
	<th>아이디</th>
	<td>${memberDto.id}</td>
</tr>
<tr>
	<th>이름</th>
	<td>${memberDto.name}</td>
</tr>
<tr>
	<th>생일</th>
	<td>${memberDto.birth}</td>
</tr>
<tr>
	<th>전화</th>
	<td>${memberDto.phone}</td>
</tr>
<tr>
	<th>우편번호</th>
	<td>${memberDto.zipcode}</td>
</tr>
<tr>
	<th>주소</th>
	<td>${memberDto.address1}</td>
</tr>
<tr>
	<th>상세 주소</th>
	<td>${memberDto.address2}</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" value="로그인 이동" 
		onclick="location.href='Login.do';" />
	</td>
</tr>
</table>
</body>
</html>