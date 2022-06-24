<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css" type="text/css">
</head>
<body>
<form action="BoardDeleteAction.do" method="post">
	<table>
		<caption>글 삭제</caption>
		<tr>
			<th>번호</th>
			<td>${no }<input type="hidden" name="no" required value="${no }"/></td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="password" placeholder="비밀번호" required/> <br>
				*처음 글 작성시 입력한 비밀번호를 입력해주세요
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="완료" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>