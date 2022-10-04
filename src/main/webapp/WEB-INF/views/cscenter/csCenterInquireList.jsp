<%@page import="com.kh.icodi.cscenter.model.dto.CsCenterInquireAnswer"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.icodi.cscenter.model.dto.CsCenterInquire"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file = "/WEB-INF/views/common/header.jsp" %>
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
		<div class="main-content-height">
	<h1>SUPPORT CENTER</h1>
	<hr />
	
	<h2 id="inquire-list-h2">문의내역확인</h2>
	<table id="tbl-inquire-list">
	<tbody>
		<tr>
		<th>작성자</th>
		<th>문의명</th>
		<th>등록일</th>
		</tr>
	<%for(CsCenterInquire c : list) {%>
		<tr>
		<td>
			<%=c.getMemberId() %>	
		</td>
		<td>
		<a href="<%=request.getContextPath()%>/csCenter/inquireView?no=<%= c.getNo()%>">
		 <%=c.getTitle() %>
		</a>		
		 </td>
		 <td>
		 <%=c.getInquireDate() %>
		 </td>
		</tr>
	<%} %>
	<tr>	
	</tr>
	</tbody>
	</table>
	</div>
	</main>
<%@include file="/WEB-INF/views/common/footer.jsp"%>