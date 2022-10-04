<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String msg = exception.getMessage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=East+Sea+Dokdo&family=Hi+Melody&family=Jua&family=Lato:wght@700&family=Luckiest+Guy&family=Nanum+Brush+Script&family=Nanum+Gothic:wght@400;700;800&family=Nanum+Pen+Script&family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Quicksand:wght@300&family=Racing+Sans+One&family=Roboto+Mono:ital,wght@0,400;1,500&family=Shrikhand&family=Ultra&display=swap" rel="stylesheet">
</head>
<body>
	<p class="err-msg">
		요청 처리 과정에서 예외가 발생하였습니다.
		<br />
		빠른시간 내에 문제를 해결하도록 하겠습니다.
		<br />
		이용에 불편드려 죄송합니다.
	</p>
	<hr />
	<a href="<%= request.getContextPath()%>">
		<button id="goToMain">메인으로 이동하기</button>	
	</a>
</body>
</html>