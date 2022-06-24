<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css" type="text/css" />
<script type="text/javascript">
function goUserRegist(){  //회원가입
	location.href='MemberRegister.do';
}
</script>
</head>
<body>
<form name="loginform" action="LoginAction.do" method="post">
<table class="login">
	<caption>로그인</caption>
<tr>
	<th>ID</th>
	<td>
		<input type="text" name="id" autofocus="autofocus" required="required" />
	</td>
</tr>
<tr>
	<th>PW</th>
	<td>
		<input type="password" name="password" required="required" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<input type="checkbox" name="setid" value="1" />id 저장
	</td>
</tr>
<tr>
	<td colspan="2">
		<input type="submit" value="확인" />
		<input type="button" value="회원가입" onclick="javascript:goUserRegist();" />
	</td>
</tr>	
</table>
</form>
</body>
</html>











