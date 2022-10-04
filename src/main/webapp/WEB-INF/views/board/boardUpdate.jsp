<%@page import="com.kh.icodi.board.model.dto.BoardExt"%>
<%@page import="com.kh.icodi.board.model.dto.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.icodi.board.model.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<% 
	Board board = (Board) request.getAttribute("board");
	List<Attachment> attachments = ((BoardExt) board).getAttachments();
	
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath() %>/js/summernote/summernote-lite.js"></script>
<script src="<%= request.getContextPath() %>/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/summernote/summernote-lite.css">
<style>
table#tbl-board-view th, td {border-bottom: 1px solid lightgray;}
table#tbl-board-view {
	width:800px; 
	margin:0 auto; 
}
table#tbl-board-view th {
    padding: 10px 0 10px 8px;
	text-align:start; 
    width: 100px;
} 
table#tbl-board-view td {
    padding: 5px 0 5px 0; 
	text-align:left;
}
table#tbl-board-view img {width: 16px;}
table#tbl-board-view input[type=file] {margin: 10px 0 3px;}
#button-wrap{
    float: right;
    margin-right: 25px;
} 
</style>
<section id="board-container">
<h2>게시판 수정</h2>
<div style="border: 1px solid gray; margin: 10px auto; width: 800px;"></div>
<form 
	name="boardUpdateFrm" 
	action="<%=request.getContextPath() %>/board/boardUpdate"
	enctype="multipart/form-data" 
	method="post">
	<input type="hidden" name="no" value="<%= board.getNo() %>" />
	<table id="tbl-board-view">
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="writer" value="<%= board.getWriter() %>" readonly/>
		</td>
	</tr>
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" value="<%= board.getTitle().replace("\"", "&quot;") %>" style="width: 600px;" required></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
		<%
			if(attachments != null && !attachments.isEmpty()) {
				for(int i = 0; i < attachments.size(); i++){
					Attachment attach = attachments.get(i);
		 %>
		 	<img src="<%= request.getContextPath() %>/images/file.png" width="16px" style="margin: 3px 5px 2px 0px;;" alt="" />
		 	<%= attach.getOriginalFilename() %>
		 	<input 
		 		type="checkbox" name="delFile" style="position: relative; top: 2px; margin-right: 0;" 
		 		id="delFile<%= i %>" value="<%= attach.getNo() %>"/>
		 	<label for="delFile<%= i %>)" style="font-size: 13px;">삭제</label>	
		 	<br />
		<%
				}
			}
		%>
			<input type="file" name="upFile1">
			<input type="file" name="upFile2">
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td>
			<textarea id="summernote" name="content"><%= board.getContent() %></textarea>
		</td>
	</tr>
	<tr>
		<th colspan="2" style="text-align: right;">
			<input type="submit" value="수정하기"/>
			<input type="button" value="취소" onclick="history.go(-1);"/>
		</th>
	</tr>
</table>
</form>
</section>
<script>
document.boardUpdateFrm.onsubmit = (e) => {
	const frm = e.target;
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	const titleVal = frm.title.value.trim(); // 좌우공백
	if(!/^.+$/.test(titleVal)){
		alert("제목을 작성해주세요.");
		frm.title.select();
		return false;
	}		
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	const contentVal = frm.content.value.trim();
	if(!/^(.|\n)+$/.test(contentVal)){
		alert("내용을 작성해주세요.");
		frm.content.select();
		return false;
	}
}

$(document).ready(function() {
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: 300,             // 최소 높이
		  maxHeight: 300,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR", // 한글 설정
		  placeholder: '패션/코디와 관련된 게시글을 작성해 주세요.<br>부적절한 내용이 포함되어 있을 경우, 통보 없이 삭제될 수 있습니다.<br>최대 2048자까지 작성 가능합니다.',//placeholder 설정
          toolbar: [
				['fontname', ['fontname']],
				['fontsize', ['fontsize']],
				['color', ['color']],
				['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
				['para', ['paragraph', 'height']],
				['ulol', ['ul', 'ol']],
        	  	['undoredo', ['undo', 'redo']],
			],
			fontNames: [ '맑은 고딕', '굴림', '돋움', '바탕', '궁서', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', 'Helvetica neue', 'Times New Roman', 'Verdana'],
			fontNamesIgnoreCheck: ['맑은 고딕', '굴림', '돋움', '바탕', '궁서', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', 'Helvetica neue', 'Times New Roman', 'Verdana'],
	});
	
	$('#summernote').summernote('fontsize', 12);
});
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>