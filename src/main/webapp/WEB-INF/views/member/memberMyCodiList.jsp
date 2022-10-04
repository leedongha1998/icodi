<%@page import="com.kh.icodi.codiBoard.model.dto.LikeThat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/member/memberMyPage.jsp" %>
<%
	int totalPage = (int) request.getAttribute("totalPage");
	String loginMemberId = (String)request.getAttribute("loginMemberId");
	List<LikeThat> likeList = (List<LikeThat>) request.getAttribute("likeThat");
%>
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<section id="my-codi-wrapper">
	<div id="my-codi-container"></div>
	<hr style="border: 0; background-color: gray; height: 0.5px;"/>
	<div id='btn-more-container'>
		<button id="btn-more" value="1" >MORE<span id="cPage"></span></button>
	</div>
</section>
<script>
document.querySelector("#btn-more").addEventListener('click', (e) => {
	const cPage = Number(document.querySelector("#btn-more").value) + 1
	getPage(cPage);
});

const getPage = (cPage) => {
	$.ajax({
		url : '<%= request.getContextPath() %>/member/memberMyCodiMore',
		data : {cPage},
		success(codiList) {
			console.log(codiList);
			console.log(codiList.length);
			const container = document.querySelector("#my-codi-container");
			if(codiList.length === 0) {
				const nonHtml = `
					<div class="non-codi-info-container">
						<div id="non-codi"><span style="color:rgba(102, 205, 170, 0.58); font-size:18px;">✖️</span> 등록하신 코디가 없습니다 <span style="color:rgba(102, 205, 170, 0.58); font-size:18px;">✖️</span></div>
						<div id="codi-create">
						<img style="width: 25px; height: 25px; margin: 0 5px; position: absolute; left: 690px;" src="<%=request.getContextPath()%>/images/addcodiicon.png" />
						<a class="createLink" href="<%= request.getContextPath() %>/codibook/create">create codi</a>
						</div>
					</div>
					`;
				container.insertAdjacentHTML('beforeend', nonHtml);
			} else {
				codiList.forEach((codiList) => {
					const {codiBoardNo, memberId, codiBoardContent, likeCount, filename, isOpen, regDate, useProduct} = codiList;
					console.log(codiList);
					
				const html = `
					<div class="codi-info-container">
						<div class="codi-img-container" style="cursor: pointer;">
							<img src="data:image/jpeg;base64,\${filename}" id="myCodiImg" data-product="\${useProduct}"  data-codiBoardNo="\${codiBoardNo}" 
								data-likeCount="\${likeCount}"/>
						</div>
						<br />
						<div class="liked-wrap">
							<span id="likeCount">받은 좋아요&nbsp;&nbsp;&nbsp;\${likeCount}</span>
							<span style="font-size: 15px;" id="Checkheart">\${likeCount === 0 ? '🤍' : '💗'}</span>
						</div>
					</div>
					`;
				container.insertAdjacentHTML('beforeend', html);
				});
			}
		},
		error : console.log,
		complete() {
			
			if(cPage == <%= totalPage %>){
				document.querySelector("#btn-more").disabled = true;
			}
			else if(<%= totalPage %> == 0){
				document.querySelector('#cPage').value = 0;
				document.querySelector("#btn-more").disabled = true;
			}
			
			[...document.querySelectorAll("#myCodiImg")].forEach((img) => {
				img.onclick = (e) => {
					//console.log(img.dataset.codiboardno);
					location.href = "<%= request.getContextPath()%>/codiBoard/codiDetail?useProduct="+`\${img.dataset.product}`+"&codiBoardNo="+`\${img.dataset.codiboardno}`+
					"&likeCount="+`\${img.dataset.likecount}`;
				};	
			});
		}
	})
};
getPage(1);
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>