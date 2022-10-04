<%@page import="com.kh.icodi.codiBoard.model.dto.LikeThat"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/codiBoard.css" />
<%
	int totalPage = (int) request.getAttribute("totalPage");
	String loginMemberId = (String)request.getAttribute("loginMemberId");
	List<LikeThat> likeList = (List<LikeThat>) request.getAttribute("likeThat");
%>
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/interactjs/dist/interact.min.js"></script>
<script src="https://unpkg.com/html2canvas@1.4.1/dist/html2canvas.js"></script>
	<main>
		<section>
			<article>
				<nav class="ootdMenu">
					<div id="hot" style="font-weight: bold;">인기</div>
					<div id="new" onclick="goToNewCodi();" style="cursor: pointer;">최신</div>
                </nav>
				<div class="content-wrap"></div>
				<div id="btn-more-content">
					<button id="btn-more" onclick="paging(event);" value="1">MORE</button>
				</div>
			</article>
		</section>
	</main>
<script>
// 최신코디 보기
const goToNewCodi = () => {
	location.href = "<%= request.getContextPath()%>/codi/newCodiList"
}

const paging = (e) => {
	const value = Number(e.target.value) + 1;
	e.target.value = value;
	getPage(e.target.value);
}

const getPage = (cPage) => {
	$.ajax({
		url : '<%= request.getContextPath()%>/codi/hotCodiMore',
		method : 'GET',
		dataType : 'json',
		data : {cPage},
		success(response) {
			const content = document.querySelector(".content-wrap");
			
			response.forEach((codi) => {
				const {codiBoardNo, memberId, codiBoardContent, likeCount, useProduct, regDate, likedMember, filename} = codi;

				const list = `
				<div class="myCodi" id="myCodi">
					<img src="data:image/jpeg;base64,\${filename}" id="myCodiImg" data-product="\${useProduct}"  data-codiBoardNo="\${codiBoardNo}" 
					data-likeCount="\${likeCount}"/>
					<div class="icodi-info">
                        <img src="<%= request.getContextPath()%>/upload/codiboard/defaultProfile.png">

                        <div class="text-wrap">
                            <div class="writerRegDateInfo">
                                <a href="#" id="writer">\${memberId}</a>
                                <span id="regDate">\${regDate}</span>
                            </div>
                            <div id="content">\${codiBoardContent}</div>
                            <div class="like-wrap">
	                            <button id="\${codiBoardNo}" class="like">
		    						\${
		    							likedMember === undefined ? '🤍' : '💗'
		    						}	
	                            </button>
	                            <span id="likeCount">\${likeCount}</span>                            
                        	</div>
                        </div>
                    </div>
				</div>
				`;
				content.insertAdjacentHTML('beforeend', list);
			});
		},
		error : console.log,
		complete() {
			if(cPage == <%= totalPage %>) {
				document.querySelector("#btn-more").disabled = true;
			}
			
			document.querySelectorAll(".like").forEach((no) => {
				no.onclick = (e) => {
					if(<%= loginMember == null %>) {
						alert("로그인 후 이용 가능합니다.");
						return;
					} 
					likeIt(e);
				}
			});
			[...document.querySelectorAll("#myCodiImg")].forEach((img) => {
				img.onclick = (e) => {
					//console.log(img.dataset.codiboardno);
					location.href = "<%= request.getContextPath()%>/codiBoard/codiDetail?useProduct="+`\${img.dataset.product}`+"&codiBoardNo="+`\${img.dataset.codiboardno}`+
					"&likeCount="+`\${img.dataset.likecount}`;
				};
			});
			

		}
	});
};
getPage(1);

const likeIt = (e) => {
	const codiBoardNo = e.target.id;
	const loginMemberId = "<%= loginMemberId %>";
	e.target.disabled = true;
	
	$.ajax({
		url : '<%= request.getContextPath() %>/codiBoard/likeUpdate',
		method : 'POST',
		dateType : 'json',
		data : {codiBoardNo, loginMemberId},
		success(response) {
			const {type, likeCount} = response;
			
			if(type === 'insert') {
				e.target.innerHTML = '💗';
			} else {
				e.target.innerHTML = '🤍'
			}
			e.target.nextElementSibling.innerHTML = `\${likeCount}`;
		},
		error : console.log,
		complete() {
			e.target.disabled = false;	
		}
	})
}

</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>