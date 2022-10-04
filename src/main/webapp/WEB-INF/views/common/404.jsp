<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	//page지시어 isErrorPage="true"로 지정한 경우 -> 기본값은 false
	//발생한 예외객체 선언없이 접근 가능.
	//404(err-code)의 경우 exception이 null이므로 msg코드에서 Null오류가 생길수도 있다
	//String msg = exception.getMessage();
	int statusCode = response.getStatus(); //404
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=East+Sea+Dokdo&family=Hi+Melody&family=Jua&family=Lato:wght@700&family=Luckiest+Guy&family=Nanum+Brush+Script&family=Nanum+Gothic:wght@400;700;800&family=Nanum+Pen+Script&family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Quicksand:wght@300&family=Racing+Sans+One&family=Roboto+Mono:ital,wght@0,400;1,500&family=Shrikhand&family=Ultra&display=swap" rel="stylesheet">
</head>
<body>
	<p class="err-msg">
		 <span class="errStrong">요청하신 페이지는 존재하지 않습니다 :</span>
		 <br />
		 주소를 올바르게 입력했는 지 확인부탁드리겠습니다.
	</p>
	<hr />
	<a href=""<%= request.getContextPath()%>"">	
		<button id="goToMain">메인으로</button>
	</a>
	<a href="javascript:history.back()">
		<button id="goToBack">뒤로가기</button>
	</a>
</body>
</html>