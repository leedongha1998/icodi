<%@page import="com.kh.icodi.cscenter.model.dto.CsCenterInquireAnswer"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.icodi.member.model.dto.MemberRole"%>
<%@page import="com.kh.icodi.cscenter.model.dto.CsCenterInquire"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/common/header.jsp" %>
<% 
	CsCenterInquire csCenterInquire = (CsCenterInquire)request.getAttribute("csCenterInquire");
	List<CsCenterInquireAnswer> answerList = (List<CsCenterInquireAnswer>)request.getAttribute("answerList");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/csCenter.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<main>
	<div class="main-content-height">
	<div id="titleContainer">
		<h1>SUPPORT CENTER</h1>
	<hr />
	</div>
	<h2 id="inquireView-h2">문의내용확인</h2>
	<table id="tbl-inquireView">
		<tr>
			<th>등록일</th>
			<td class="first"><%=csCenterInquire.getInquireDate() %></td>
		</tr>
		<tr>
			<th>답변여부</th>
			<td>
			<% if(answerList.isEmpty()){ %>
				X
			<%} else{%>
				O
			<%} %>
			</td>
		</tr>
		<tr>
			<th>문의내용</th>
			<td><%= csCenterInquire.getContent() %></td>
		</tr>
		<tr>
			<th>답변내용</th>
			<td name="answerContent"></td>
		</tr>
		<tr>
			<th>답변작성자</th>
			<td name="answerWriter"></td>
		</tr>
		<tr>
			<th>답변일</th>
			<td name="answerDate"></td>
		</tr>
	</table>
	
	
	
	<%if(loginMember != null && loginMember.getMemberRole() == MemberRole.A ){ %>
		<form name="inquireAnswerFrm" action="<%=request.getContextPath()%>/inquireAnswerEnroll" method="POST">
			<input type="hidden" name="inquireNo" value="<%=csCenterInquire.getNo()%>"/>
			<input type="hidden" name="answerNo" value=""/>
			<input type="hidden" name="answerWriter" value="<%=loginMember != null && loginMember.getMemberRole() == MemberRole.A ? loginMember.getMemberId() : ""%>"/>
			<textarea name="answerContent"  cols="60" rows="3" value="문의답변을 입력하세요" required></textarea>
			<button id="btn-answer-enroll">등록</button>
		</form>
	<%} %>
	
	<script>
	<%
		if(answerList == null || answerList.isEmpty()){
	%>
		document.querySelector("[name=answerContent]").innerHtml = "응답없음";

	<%} 
		else{
			for(CsCenterInquireAnswer as : answerList){
		%>
		document.querySelector("[name=answerWriter]").innerHTML = "<%=as.getAnswerWriter()%>";
		document.querySelector("[name=answerContent]").innerHTML = "<%=as.getAnswerContent()%>";
		document.querySelector("[name=answerDate]").innerHTML = "<%=as.getAnswerDate()%>";
	</script>
	
		<%if(loginMember != null && loginMember.getMemberRole() == MemberRole.A){ %>
		<form id="inquireDelFrm" action="<%=request.getContextPath()%>/inquireAnswerDelete" method="POST">
			<input type="hidden" name="inquireNo" value="<%=as.getInquireNo() %>" />
			<input type="hidden" name="answerNo" value=<%=as.getAnswerNo() %> />
			<input id="inquireDel" type="submit" value="문의답변삭제" onclick="location.href='<%=request.getContextPath()%>/inquireAnswerDelete'"/>
			<div>
		<p>주말 및 공휴일에는 고객센터 답변이 지연 될 수 있습니다.</p>
		<p>고객센터 운영시간 : 월-금: 11AM~6PM.</p>
	</div>
		</form>
		<%} %>		
	<% 	}
	}%>
	</div>
	</main>
	<script>
	document.inquireAnswerFrm.onsubmit = (e) =>{
		if(document.querySelector("[name=answerWriter]").innerHTML != null){
			alert('이미 다른 응답이 있습니다.');
			return false;
		}
	}
	</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>