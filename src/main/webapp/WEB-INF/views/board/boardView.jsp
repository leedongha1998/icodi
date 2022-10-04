<%@page import="java.sql.Date"%>
<%@page import="com.kh.icodi.member.model.dto.MemberRole"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.icodi.board.model.dto.CommentLevel"%>
<%@page import="com.kh.icodi.board.model.dto.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.icodi.board.model.dto.Attachment"%>
<%@page import="com.kh.icodi.board.model.dto.BoardExt"%>
<%@page import="com.kh.icodi.board.model.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	BoardExt board = (BoardExt) request.getAttribute("board");
	List<Attachment> attachments = board.getAttachments();
	//System.out.println("board = " + board);
	//System.out.println("img = " + board.getAttachments());
	
	List<BoardComment> commentList = (List<BoardComment>) request.getAttribute("commentList");
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
	
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">

	<table id="tbl-board-view">
		<tr>
			<th colspan="6"
            style="border-bottom: 2px solid lightgray;"><%= board.getTitle() %></th>
		</tr>
		<tr>
			<td colspan="6">작성일 : <%=format.format(( board.getRegDate() ))%></td>
		</tr>
		<tr style="float: left;">
			<th id="thWriter" style="width: 60px; text-align:center;">작성자 : </th>
			<td id="tdWriter" style="width: 50px; padding-left: 5px;"><%= board.getWriter() %></td>
            <th id="thReadCount" style="width: 65px; text-align:center;">조회수 : </th>
            <td id="tdReadCount" style="width: 600px" ><%= board.getReadCount() %></td>
           	<% if(attachments != null && !board.getAttachments().isEmpty()) { %>
				<%  for(Attachment a : board.getAttachments()){ %>
            <td>
                <img alt="" src="<%=request.getContextPath() %>/images/file.png" style="width: 20px; margin-right:15px">
                <a href="<%= request.getContextPath()%>/board/fileDownload?no=<%= a.getNo() %>" style="margin-right: 20px; text-decoration: inherit;">
                <%= a.getOriginalFilename() %>
                </a>
            </td>
        </tr>
        		<% } %>	
			<% } %>
		<tr>
            <td colspan="6" style="height: 150px; border-bottom: 1px solid #efefef; border-top: 1px solid #efefef;
            color:#636363"><%= board.getContent() %></td>
		</tr>
	</table><br>
			<% if(loginMember != null && 
				(loginMember.getMemberId().equals(board.getWriter()) 
						|| loginMember.getMemberRole() == MemberRole.A)) { %>
    <div id="button-wrap">
        <input type="button" value="수정하기" onclick="updateBoard()">
        <input type="button" value="삭제하기" onclick="deleteBoard()">
    </div><br>
    		<% } %>
	<hr style="margin-top:30px;" />    
   	<br /><br />
    <!-- 댓글 작성 부분 -->
    <div class="comment-container">
        <!--table#tbl-comment-->
        
        <table id="tbl-comment">
        	<%
        		if(commentList != null && !commentList.isEmpty()) {
        			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
        			for(BoardComment bc : commentList) {
        				boolean canDelete = loginMember != null &&
        						(loginMember.getMemberId().equals(bc.getWriter())
        								|| loginMember.getMemberRole() == MemberRole.A);
        	%>
        	<tr class="<%= bc.getCommentLevel() == CommentLevel.COMMENT ? "level1" : "level2" %>">
        		<td style="font-size: 1.2em;">
        			<sub class="comment-writer"><%= bc.getWriter() %></sub>
        			<sub class="comment-date"><%= sdf.format(bc.getRegDate()) %></sub>
        		</td>
        		<td>
        			<div>
        				<%= bc.getContent() %>
        			</div>
        		</td>
        		<td>
        			<% if(bc.getCommentLevel() == CommentLevel.COMMENT) { %>
        			<button class="btn-reply" value="<%= bc.getNo() %>">답글</button>
        			<% } %>
        			
        			<% if(canDelete) { %>
        			<button class="btn-delete" value="<%= bc.getNo() %>">삭제</button>
        			<% } %>
        		</td>
        	</tr>
        	<%
        			}
        		}
        	%>
        </table>
        <br />
        <div class="comment-editor">
            <form
            name="boardCommentFrm"
            action="<%=request.getContextPath()%>/board/boardCommentEnroll" 
            method="post" >
                <input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
                <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
                <input type="hidden" name="commentLevel" value="1" />
                <input type="hidden" name="commentRef" value="0" />    
                <textarea name="content" cols="125" rows="5" style="resize: none;"></textarea>
                <button type="submit" id="btn-comment-enroll1">등록</button>
            </form>
        </div>
    </div>
    
    
</section>
<form 
	action="<%= request.getContextPath() %>/board/boardCommentDelete"
	method="post" 
	name="boardCommentDelFrm">
	<input type="hidden" name="no" /> <!-- 댓글번호 같이 전송 -->	
</form>
<script>
document.querySelectorAll(".btn-delete").forEach((btn) => {
	btn.addEventListener('click', (e) => {
		if(confirm("해당 댓글을 정말 삭제하시겠습니까?")){
			const {value} = e.target;
			const frm = document.boardCommentDelFrm;
			frm.no.value = value;
			
			frm.submit();
		}
	});
});

document.querySelectorAll(".btn-reply").forEach((btn) => {
	btn.addEventListener('click', (e) => {
		<% if(loginMember == null){%>
			loginAlert(); return;
		<% } %>
		
		const {value} = e.target;
		console.log(value);
		
		const tr = `
		<tr>
			<td colspan="2" style="text-align:left;">
				<form
		        	name="boardCommentFrm"
					action="<%=request.getContextPath()%>/board/boardCommentEnroll" 
					method="post">
		            <input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
		            <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
		            <input type="hidden" name="commentLevel" value="2" />
		            <input type="hidden" name="commentRef" value="\${value}" />    
					<textarea name="content" cols="70" rows="2" style="resize: none;"></textarea>
		            <button type="submit" class="btn-comment-enroll2">등록</button>
		        </form>
			</td>
		</tr>`;
		
        const target = e.target.parentElement.parentElement; // tr
        target.insertAdjacentHTML('afterend', tr);
		
	}, {once: true}); // 1회용 핸들러옵션
});

document.boardCommentFrm.content.addEventListener('focus', (e) => {
	if(<%= loginMember == null %>)
		loginAlert();
});

/**
 * 부모요소에서 자식 submit 이벤트 핸들링
 */
document.addEventListener('submit', (e) => {
	
	// matches(selector) 선택자 일치여부를 반환
	if(e.target.matches("form[name=boardCommentFrm]")){		
		if(<%= loginMember == null %>){
			loginAlert();
			e.preventDefault();
			return;		
		}
		
		if(!/^(.|\n)+$/.test(e.target.content.value)){
			alert("내용을 작성해주세요.");
			e.preventDefault();
			return;			
		}
	}
	
});

const loginAlert = () => {
	alert("로그인후 이용할 수 있습니다.");
	document.querySelector("#memberId").focus();
};

</script>
<% if(loginMember != null && 
				(loginMember.getMemberId().equals(board.getWriter()) 
						|| loginMember.getMemberRole() == MemberRole.A)) { %>
<form action="<%= request.getContextPath() %>/board/boardDelete" 
name="boardDelFrm"
method="POST">
<input type="hidden" name="no" value="<%= board.getNo() %>" />
</form>
<script>
	const updateBoard = () => {
		location.href = "<%= request.getContextPath() %>/board/boardUpdate?no=<%= board.getNo() %>"
	};
	const deleteBoard = () => {
		if(confirm("정말 게시글을 삭제하시겠습니까?")){
			document.boardDelFrm.submit();
		}
	};
</script>
<% } %>
<%@include file="/WEB-INF/views/common/footer.jsp"%>