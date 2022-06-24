<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css" type="text/css">
</head>
<body>
<form method="post" action="BoardInsertAction.do">
	<table>
		<caption>글 쓰기</caption>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" autofocus required placeholder="제목" /></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" placeholder="이름" required/></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="password" placeholder="비밀번호" required/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea required rows="5" cols="50" name="content" placeholder="내용" required></textarea>
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