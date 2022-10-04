<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/csCenter.css" />

</head>
<body>
<main>
<h1>SUPPORT CENTER</h1>
	<hr />
	
<%if(loginMember == null ){%>
	<script>location.href="<%=request.getContextPath()%>/member/memberLogin"</script>
<%}else{ %>
<form action="<%= request.getContextPath() %>/csCenter/inquireEnroll" method ="POST" name ="inquireFrm">
	<h2>1:1문의하기</h2>
	<div class="alert">아이코디 다국어 고객지원: 영어, 중국어, 일본어, 한국어로 문의 가능합니다</div>
	<div class="alert">아이코디는 여러분의 소리에 집중하고 만족스러운 서비스 경험을 드리기 위해 항상 노력하고 있습니다. 
	1:1 문의는 이러한 노력의 일환이며 매일 수 백개의 문의에 100% 답변드리기 위해 전담하는 직원들이 있습니다. 상담 직원도 누군가의 소중한 가족입니다. 
	게시판을 통한 욕설이나 인격 모독은 삼가 해 주세요.</div>
	<br />
	<table id="tbl-inquire">
		<tr>
		<th>아이디</th>
		<td><input type="text" name="memberId" value="<%=loginMember.getMemberId()%>" readonly/></td>
		</tr>
		<tr>
		<th>제목</th>
		<td><input type="text" required name="title"/></td>
		</tr>
		<tr>
		<th>카테고리</th>
		<td>
		<select name="selectType" id="">
		<option value="A">선택</option>
		<option value="B">상품</option>
		<option value="C">배송</option>
		<option value="D">교환</option>
		<option value="E">환불</option>
		<option value="F">기타</option>
		</select>
		</td>
		</tr>
		<td colspan="2"><textarea class="content" name="content" placeholder="문의내용을 입력해주세요" required></textarea></td>
	</table>
	<br />
	<div>
		<ol>
			<li>※ 주말 및 공휴일에는 고객센터 답변이 지연 될 수 있습니다.</li>
			<li>※ 고객센터 운영시간 : 월-금: 11AM~6PM.</li>
		</ol>
	</div>
		<input type="submit" value="문의하기"/>
</form>
<%} %>
<br />
<br />
<br />
<br />
<br />
</main>
<%@include file="/WEB-INF/views/common/footer.jsp"%>