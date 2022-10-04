<%@page import="java.util.List"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productList.css" />
<%
	int totalPage = (int)request.getAttribute("totalPage");
	String searchKeyword = (String) request.getAttribute("searchKeyword");
	//System.out.println("totalpage@jsp = " + totalPage);
	//System.out.println("keyword@jsp = " + searchKeyword);
	
%>
<main>
	<section>
		<article>
			<h2>'<%= searchKeyword %>'에 대한 검색 결과</h2>
			<ul class="productList">
			
			</ul>
			<div id='btn-more-container'>
				<button id="btn-more" value="">더보기(<span id="cPage"></span>/<span id="totalPage"><%= totalPage%></span>)</button>
			</div>
		</article>
	</section>
</main>
<style>	
	/* .productList img{width:150px; height:150px;} */
	/* #productName{width:150px;} */
</style>
<script>
	document.querySelector("#btn-more").addEventListener('click', (e) => {
		const cPage = Number(document.querySelector("#cPage").textContent) + 1;
		getPage(cPage);
	});
	
	const getPage = (cPage) => {
		const searchKeyword = "<%= searchKeyword%>";
		$.ajax({
			url : '<%= request.getContextPath() %>/product/moreLike',
			dataType : 'json',
			data : {cPage, searchKeyword},
			success(response){
				const ul = document.querySelector(".productList");
				//console.log('response',response);
				response.forEach((product) => {
					const {productRenamedFilename} = product.attachmentList[0];
					//console.log(productRenamedFilename);
					const {productCode, productName, productPrice} = product;
					
					const li = `
						<li class="product">
							<a href="<%= request.getContextPath()%>/product/detail?product_name=\${productName}">					
							<img src="<%= request.getContextPath()%>/upload/admin/\${productRenamedFilename}" alt="" id="productImg"/>
							<div class="product-info">
								<div id="productName">\${productName}</div>
								<div id="productPrice">\${productPrice}</div>
							</div>
						</li>
					</a>
					`;
					ul.insertAdjacentHTML('beforeend', li);
				})
			},
			error : console.log,
			complete() {
				document.querySelector('#cPage').innerHTML = cPage;	

				if(cPage == <%= totalPage %>){
					document.querySelector("#btn-more").disabled = true;
				}
			}
		})
	};
	getPage(1);
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>