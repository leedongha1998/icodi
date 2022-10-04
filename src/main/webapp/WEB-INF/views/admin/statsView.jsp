<%@page import="java.util.List"%>
<%@page import="com.kh.icodi.stats.model.dto.Stats"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>   
<%
	List<Stats> list = (List<Stats>)request.getAttribute("list"); 
	Object cnt = request.getAttribute("cnt");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/stats.css" />
<title>Insert title here</title>
</head>
<body>
<main>
	<h1>통계</h1>
	<hr />
	<div id="space">
	
		<div id="search-container">
		<h2>방문 회원 조회</h2>
		<div id="search-day">
			<form action="<%=request.getContextPath()%>/stats/finder" id="visit-frm">
			  <input type='date' id='currentDate' name="when" required/>
				<button type="submit" class="stats-btn" >검색</button>
			</form>
		</div>	
		<h2>코디 통계</h2>
		<div id="search-codi-cnt">
			<form action="<%=request.getContextPath()%>/stats/finder">
				<input type="date" name="codiWhen"/>
				<button type="submit" class="stats-btn">검색</button>
			</form>
		</div>
	</div>
	<div id="arrow"><i class="fa-solid fa-play"></i></div>
	<div id="result-container">
		<div id="visit-result">
		<h3>방문 회원수</h3>
		<%if(list.isEmpty() || list == null){%>
			<span>아무 결과가 없습니다.</span>
		<%}else{ %>
			<span id="count"></span>
			<table id="tbl-visit">
				<%for(Stats s : list) {%>
				<tr>
					<td><%=s.getVisitMemberId() %></td>
				</tr>
				<%} %>
			</table>	
	<%} %>
		</div>
	
	<div id="codi-result">
		<h3>코디 수</h3>
		<%if(cnt == null) {%>
			<span>아무 결과가 없습니다.</span>
		<%} else{%>
		<span><%=cnt %>개</span>
		<%} %>	
	</div>
	</div>
</div>
</main>
<script>
	const table = document.querySelector("#tbl-visit");
	const totalRowCnt = table.rows.length;
	const count = document.querySelector("#count");
	count.innerText = "총 " + totalRowCnt + "명";		
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>