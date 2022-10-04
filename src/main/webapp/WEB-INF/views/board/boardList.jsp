<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.icodi.board.model.dto.BoardExt"%>
<%@page import="com.kh.icodi.board.model.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<Board> list = (List<Board>) request.getAttribute("list"); 
	//System.out.println("list@boardList = " + list);
	String searchKeyword = (String) request.getAttribute("searchKeyword");
	String searchType = (String) request.getAttribute("searchType");
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />
<style>
div#search-container {
	width: 100%;
	margin: 0 0 10px 0;
	padding: 3px;
	float: left;
}
#search-content{
	display: none;
}
#search-title{
	display: block;
}
</style>
<section id="board-container">
	<table id="tbl-board">
		<tr id="menuHeader">
			<th>번호</th>
			<th></th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
		<% if(searchKeyword != null) {%>
			<span>'<%= searchKeyword %>' 에 대한 검색 결과 입니다.</span>
			<br /><br />
		<% } %>
		<% 
			if(list != null && !list.isEmpty()){ 
				for(Board _board : list){
					BoardExt board = (BoardExt) _board;
		%>
		<tr>
			<td><%= board.getNo() %></td>
			<td>
				<% if(board.getAttachCount() > 0){ %> <img
				src="<%= request.getContextPath() %>/images/clip.png" alt=""
				style="width: 30px;" /> <% } %>
			</td>
			<td id="boardTitle"><a
				href="<%= request.getContextPath()%>/board/boardView?no=<%= board.getNo() %>">
					<%= board.getTitle() %> <% if(board.getCommentCount() > 0){ %> [<%= board.getCommentCount() %>]
					<% } %>
			</a></td>
			<td><%= board.getWriter() %></td>
			<td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(board.getRegDate()) %></td>
			<td><%= board.getReadCount() %></td>
		</tr>
		<%
				}
			} else { 
		%>
		<tr>
			<td colspan="6">조회된 게시글이 없습니다.</td>
		</tr>
		<% } %>
	</table>
	<% if(loginMember != null){ %>
	<br />
	<input type="button" value="글쓰기" id="btn-add"
		onclick="location.href='<%= request.getContextPath() %>/board/boardEnroll';" />
	<br />
	<% } %>
	<br /><br />
	<div id="search-container">
		<div id="select" style="display: inline;">
			<select id='selSearchOption' onchange="test(this);">
				<option value='T'>제목</option>
				<option value='C'>내용</option>
			</select>
		</div>
		<div id="search-title" class="search-type" style="display:inline;">
		<form action="<%=request.getContextPath()%>/board/boardFinder" style="display: inline;">
		<input type="hidden" name="searchType" value="board_title" /> 
		<input type="text" name="searchKeyword" value=""  />
		<button type="submit">검색</button>
		</form>
		</div>
		<div id="search-content" class="search-type"  style="display:none;">
		<form action="<%=request.getContextPath()%>/board/boardFinder" style="display: inline;">
		<input type="hidden" name="searchType" value="board_content" /> 
		<input type="text" name="searchKeyword" value=""  />
		<button type="submit">검색</button>
		</form>
		</div>
	</div>
	<script>
		const test = (e) => {
			console.log(e.value);
			if(e.value == 'C'){
				const title = document.getElementById("search-title");
				title.style.display = "none";
				const content = document.getElementById("search-content");
				content.style.display = "inline";
			}
			else{
				const title = document.getElementById("search-title");
				title.style.display = "inline";
				const content = document.getElementById("search-content");
				content.style.display = "none";
				
				
			}
		}
	</script>
	<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
	</div>
	<br /><br />
</section>
<%@include file="/WEB-INF/views/common/footer.jsp"%>