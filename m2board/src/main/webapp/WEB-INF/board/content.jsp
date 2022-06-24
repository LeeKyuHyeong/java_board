<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css" type="text/css">
</head>
<body>
<table width="700">
	<caption>글 상세보기</caption>
	<tr>
		<th>글 번호</th>
		<th>${boardDto.no }</th>
	</tr>
	<tr>
		<th>제목</th>
		<th>${boardDto.title }</th>
	</tr>
	<tr>
		<th>이름</th>
		<th>${boardDto.name }</th>
	</tr>
	<tr>
		<th>조회수</th>
		<th>${boardDto.readcount }</th>
	</tr>
	<tr>
		<th>작성시간</th>
		<th>${boardDto.writeday }</th>
	</tr>
	<tr>
		<th>내용</th>
		<th>${boardDto.content }</th>
	</tr>
</table> <br>
<a href="BoardList.do">[목록]</a>
<a href="BoardUpdate.do?no=${boardDto.no }">[수정]</a>
<a href="BoardDelete.do?no=${boardDto.no }">[삭제]</a>
</body>
</html>