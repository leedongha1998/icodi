<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
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
table#tbl-board-view input[type=file] {margin: 3px 0;}
#button-wrap{
    float: right;
    margin-right: 25px;
} 
</style>
<section id="board-container">
<h2>게시글 작성</h2>
<div style="border: 1px solid gray; margin: 10px auto; width: 800px;"></div>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/board/boardEnroll" 
	method="post" 
	enctype="multipart/form-data"> <!--파일 업로드를 하고싶을때 이거 적혀있는지 확인과 POST방식 확인  -->
	<table id="tbl-board-view">
       <tr>
            <th>작성자</th>
            <td>
                <input type="text" name="writer" value="<%= loginMember.getMemberId() %>"  readonly />
            </td>
        </tr>
        <tr>
            <th>제 목</th>
            <td><input type="text" name="title" required style="width: 600px;"></td>
        </tr>
        <tr>
	        <th>첨부파일</th>
	        <td>			
	            <input type="file" name="upFile1">
	            <input type="file" name="upFile2">
	        </td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><textarea id="summernote" name="content"></textarea></td>
        </tr>
        <tr>
            <td colspan="2" style="border-bottom: none;">
                <input type="submit" value="등록하기" style="float: right;">
            </td>
        </tr>
    </table>
</form>
</section>
<script>
/**
* boardEnrollFrm 유효성 검사
*/
document.boardEnrollFrm.onsubmit= (e) => {
	const frm = e.target;
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.+$/.test(frm.title.value)){
		alert("제목을 작성해 주세요")
		frm.title.focus();
		return false;
	}
	
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^(.|\n)+$/.test(frm.content.value)){
		alert("내용을 입력하세요.");
		frm.content.focus();
		return false;
	}

	return true;
}

$(document).ready(function() {
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: 300,             // 최소 높이
		  maxHeight: 300,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR", // 한글 설정
		  placeholder: '패션/코디와 관련된 게시글을 작성해 주세요.<br>부적절한 내용이 포함되어 있을 경우, 통보 없이 삭제될 수 있습니다.<br>최대 2048자까지 작성 가능합니다.',
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
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36']
	});
});
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>