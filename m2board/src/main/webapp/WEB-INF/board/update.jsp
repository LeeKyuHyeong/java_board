<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css" type="text/css">
</head>
<body>
<form action="BoardUpdateAction.do" method="post">
	<table>
		<caption>글 수정</caption>
		<tr>
			<th>번호</th>
			<td>${boardDto.no }<input type="hidden" name="no" required value="${boardDto.no }"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" required value="${boardDto.title }" /></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" value="${boardDto.name }" required/></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="password" placeholder="비밀번호" required/> <br>
				*처음 글 작성시 입력한 비밀번호를 입력해주세요
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea required rows="5" cols="50" name="content" placeholder="내용" required>${boardDto.content }
				</textarea>
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