<%@page import="com.kh.icodi.codiBoard.model.dto.LikeThat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductAttachment"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductExt"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/codiDetail.css" />

<%
	List<ArrayList<ProductExt>> list =(List<ArrayList<ProductExt>>) request.getAttribute("list");
	String codiImg = (String) request.getAttribute("codiImg");
	int likeCount = (int) request.getAttribute("likeCount");
	LikeThat liked = (LikeThat)request.getAttribute("liked");
	int codiBoardNo = (int)request.getAttribute("codiBoardNo");
	DecimalFormat numFormat = new DecimalFormat("#,###");
	String loginMemberId = (String)request.getAttribute("loginMemberId") != null ? (String)request.getAttribute("loginMemberId") : null;
%>
<main>
	<section>
		<article>
			<div id="codiImg">
		        <img src="data:image/jpeg;base64,<%=codiImg %>" alt="">
		        <hr />
		    </div>
		    <div class="like-count-wrap">
	       		<div class="like-wrap">
		        	<% if(liked == null) { %>
		       			<button id="<%= codiBoardNo%>" class="like">🤍</button>
		       		<% } else { %>
		       			<button id="<%= codiBoardNo%>" class="like">💗</button>
		       		<% } %>
	       		</div> 
	        	<span class="strong" id="likeCount"><%=likeCount %></span>명이 좋아합니다.
		    </div>
		    <div class="useProductHeader">
		        <h2>코디에 사용된 상품</h2>
		    </div>		    
		    
		    <div class="productContainer">
			<% for(List<ProductExt> _product : list){ 
			for(ProductExt product : _product){
				String codiRenamedFilename = null;
				for(ProductAttachment attach : product.getAttachmentList()) {
					codiRenamedFilename = attach.getCodiRenamedFilename();
				}
		%>
			<div class="product">
				<div class="imgWrap">
					<a href="<%= request.getContextPath()%>/product/detail?product_name=<%= product.getProductName() %>">
						<img src="<%= request.getContextPath()%>/upload/admin/<%= codiRenamedFilename %>" alt="" />
					</a>
				</div>
				<div class="product-info-wrap">
					<div id="productName">
						<p><%= product.getProductName() %></p>
					</div>
					<div id="productPrice">
						<p><%= numFormat.format(product.getProductPrice()) %>원</p>
					</div>
				</div>
			</div>
			<% 		} 
			}
		%>
		</div>
		</article>
	</section>		
</main>
<script>
document.querySelector(".like").addEventListener('click', (e) => {
	if(<%= loginMember == null %>) {
		alert('로그인 후 이용 가능합니다.');
		return;
	} 
	likeItAtDetail(e);
});

const likeItAtDetail = (e) => {
	const codiBoardNo = e.target.id;
	const loginMemberId = "<%= loginMemberId%>";
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
			document.querySelector("#likeCount").innerHTML = `\${likeCount}`; 
		},
		error : console.log,
		complete() {
			e.target.disabled = false;	
		}
	})
};

</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>