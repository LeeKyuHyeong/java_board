<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css" type="text/css">
</head>
<body>
${logInfo }<a href="Logout.do">[로그아웃]</a>
<table width="600">
	<caption>게시판 리스트</caption>
	<tr>
		<td colspan="5" align="right">${pg } / ${pageCnt }</td>
	</tr>
	<tr>
		<th>글 번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.no}</td>
			<td><a href="BoardView.do?no=${dto.no}">${dto.title}</a></td>
			<td>${dto.memberDto.name}(${dto.memberDto.id })</td>
			<td>${dto.regdt}</td>
			<td align="right">${dto.readcnt}</td>
		</tr>
	</c:forEach>
</table><br/>
<table>
	<tr>
		<td>
			<c:if test="${startPage != 1}">
				<a href="BoardList.do?pg=${startPage-1}">이전</a>
			</c:if>
		</td>
		<td>
			<c:forEach var="p" begin="${startPage }" end="${endPage }">
				<c:if test="${pg == p}">
					${p }
				</c:if>
				<c:if test="${pg != p}">
					<a href="BoardList.do?pg=${p}">${p }</a>
				</c:if>
			</c:forEach>
		</td>
		<td>
			<c:if test="${endPage != pageCnt }">
				<a href="BoardList.do?pg=${endPage+1}">다음</a>		
			</c:if>
		</td>
	</tr>
</table>
<a href="BoardInsert.do">[글쓰기]</a>
</body>
</html>