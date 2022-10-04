<%@page import="com.kh.icodi.cscenter.model.dto.CsCenterInquire"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	List <CsCenterInquire> list = (List<CsCenterInquire>)request.getAttribute("list");
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
	<h1>문의내역확인</h1>
	<table id="tbl-inquire-list">
	<th>문의명</th>
	<th>작성자</th>
	<th>등록일</th>
	<%for(CsCenterInquire c : list) {%>
		<tr>
			<td>
			<a href="<%=request.getContextPath()%>/csCenter/inquireView?no=<%= c.getNo()%>">
			<span id="csTitle">
				<%=c.getTitle() %>			
			</span>
			</a>
			</td>
			<td>
			<%=c.getMemberId() %>
			</td>
			<td>
				<%=c.getInquireDate() %>
			</td>	
					
		</tr>
		
	<%} %>
		</table>
		</main>
<%@include file="/WEB-INF/views/common/footer.jsp"%>