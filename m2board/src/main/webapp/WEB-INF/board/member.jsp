<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css" type="text/css" />
<script src="js/ajax.js"></script>
</head>
<body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
//-1:시스템오류, 0:사용가능 1:사용불가능
var user_id_count = -1;

var user_id = '';

var openZipcode = function() {
    new daum.Postcode({
        oncomplete: function(data) {
        	var f = document.regist;
        	f.zipcode.value = data.zonecode;
        	f.address1.value = data.roadAddress;            
        }
    }).open();
}

function validate() {
	var f = document.regist;
	
	if(f.user_id.value.length < 3 || f.user_id.value.length > 12) {
		alert('아이디는 3글자 이상 12글자 이하로 작성해주세요.');
		f.user_id.focus();
		return;
	}
	
	if(f.user_name.value.length < 2 || f.user_name.value.length > 6) {
		alert('이름은 2글자 이상 6글자 이하로 작성해주세요.');
		f.user_name.focus();
		return;
	}
	
	if(f.user_pw.value.length < 4 || f.user_pw.value.length > 20) {
		alert('비밀번호는 4글자 이상 20글자 이하로 작성해주세요.');
		f.user_pw.focus();
		return;
	}
	
	if(f.user_pw.value != f.user_pw2.value) {
		alert('비밀번호가 일치하지 않습니다.');
		f.user_pw.focus();
		return;
	}
	
	if(f.user_birth.value == ''){
		alert('생년월일을 입력해주세요.');
		f.user_birth.focus();
		return;		
	}
	
	if(f.user_phone.value == ''){
		alert('전화번호를 입력해주세요.');
		f.user_phone.focus();
		return;		
	}
	
	if(f.zipcode.value == ''){
		alert('우편번호를 입력해주세요.');
		f.zipcode.focus();
		return;		
	}
	
	if(f.address1.value == ''){
		alert('주소를 입력해주세요.');
		f.address1.focus();
		return;		
	}
	
	if(f.address2.value == ''){
		alert('상세주소를 입력해주세요.');
		f.address2.focus();
		return;		
	}
	
	if(user_id_count != 0){
		alert('존재하는 아이디입니다.');
		f.user_id.focus();
		return;		
	}
	
	f.method = 'POST';
	f.action = 'MemberRegisterAction.do';
	f.submit();
}

function change_user_id() {
	
	var f = document.regist;
	user_id = f.user_id.value;
	
	if(user_id.length < 3 || user_id.length > 12) {
		document.getElementById('check_id_result').innerHTML = '아이디는 3글자 이상 12글자 이하로 작성하세요.';
		f.user_id.focus();
		return;
	} 
	
	new Request("CheckID.do", "user_id=" + user_id, callback, 'POST');	
}

function callback(xhr) {
	if(xhr.readyState == 4){
		if(xhr.status == 200) {
			user_id_count = parseInt(xhr.responseText.trim());
			//alert('user_id_count : ' + user_id_count);
			var result = document.getElementById('check_id_result');
			if(user_id_count == 1) {
				//document.getElementById('check_id_result').innerText = "중복된 아이디가 존재합니다."
				result.innerHTML = "<font color='red'>" + user_id + "는 사용중인 아이디입니다."
				return;
			} else if(user_id_count == -1) {
				result.innerHTML = "DB오류가 있습니다."
			} else {
				result.innerHTML = "<font color='green'>" + user_id + "는 사용가능한 아이디입니다."
			}
		}
	}
	xhr.send(null);
}
</script>
<form name="regist" method="post">
<table border="1">
	<caption>회원 가입</caption>
<tr>
	<th>아이디</th>
	<td><input type="text" name="user_id" id="user_id" onkeyup="change_user_id()"/>
		<div style="margin-top:5px;" id="check_id_result">영문+숫자 3~12문자로 작성하세요.</div>
	</td>
</tr>
<tr>
	<th>이름</th>
	<td><input type="text" name="user_name" id="user_name" />2~6문자</td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="user_pw" id="user_pw" />4~20문자</td> 
</tr>
<tr>
	<th>비밀번호 확인</th>
	<td><input type="password" name="user_pw2" id="user_pw2" /></td>
</tr>
<tr>
	<th>생일</th>
	<td><input type="date" name="user_birth" id="user_birth" /></td>
</tr>
<tr>
	<th>전화</th>
	<td><input type="text" name="user_phone" id="user_phone" /></td>
</tr>
<tr>
	<th>우편번호</th>
	<td>
		<input type="text" name="zipcode" readonly="readonly"
		onclick="alert('검색버튼을 이용하여 입력하세요');" 
		style="width:55px;"/>
		<input type="button" value="검색" name="btnZipcode"
		onclick="openZipcode()"
		/>
	</td>
</tr>
<tr>
	<th>주소</th>
	<td>
		<input type="text" name="address1" style="width:250px;"/><br/>
	</td>
</tr>
<tr>
	<th>상세 주소</th>
	<td>
		<input type="text" name="address2" /><br/>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" value="완료" onclick="validate()" />
	</td>
</tr>
</table>
</form>
</body>
</html>