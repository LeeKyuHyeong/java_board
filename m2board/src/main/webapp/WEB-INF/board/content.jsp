<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css" type="text/css">
<script type="text/javascript">
function confirm_delete() {
	if(confirm('정말 삭제하시겠습니까?')){
		location.href = "BoardDeleteAction.do?no=${boardDto.no }";
	} else {
		return;
	}
}
</script>
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
		<th>${boardDto.memberDto.name }</th>
	</tr>
	<tr>
		<th>조회수</th>
		<th>${boardDto.readcnt }</th>
	</tr>
	<tr>
		<th>작성시간</th>
		<th>${boardDto.regdt }</th>
	</tr>
	<tr>
		<th>내용</th>
		<th>${boardDto.content }</th>
	</tr>
</table> <br>
<a href="BoardList.do">[목록]</a>
<c:if test="${boardDto.memberDto.id == sessionScope.userInfo.id }">
	<a href="BoardUpdate.do?no=${boardDto.no }">[수정]</a>
	<a href="javascript:onclick=(confirm_delete)" >[삭제]</a>
</c:if>
</body>
</html>