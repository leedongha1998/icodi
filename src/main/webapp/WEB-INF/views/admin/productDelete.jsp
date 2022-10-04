<%@page import="com.kh.icodi.admin.model.dto.CategoryNo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productDelete.css" /> 
<main>
	<div class="content-wrap">
		<div class="header-wrap">
			<h2>상품 삭제</h2>
		</div>
		<section>
			<article>
				<form name="productDelFrm" action="<%= request.getContextPath()%>/admin/productDel" method="POST">
					<div class="categoryArea-wrap">
						<select name="categoryName">
							<option value="">선택하세요</option>
							<option value="<%=CategoryNo.stringOf("TOP")%>">상의</option>
							<option value="<%=CategoryNo.stringOf("BOTTOM")%>">하의</option>
							<option value="<%=CategoryNo.stringOf("SHOES")%>">신발</option>
							<option value="<%=CategoryNo.stringOf("ACC")%>">악세사리</option>
						</select>
						<input type="submit" value="삭제" id="delBtn"/>
					</div>
					<div class="codiProduct">
						<table id="productTable">
							<thead>
								<tr>
									<th></th>
									<th>상품이미지</th>
									<th>상품코드</th>
									<th>상품이름</th>
									<th>상품가격</th>
									<th>상품사이즈</th>
									<th>상품색상</th>
								</tr>
							</thead>		
							<tbody>
							
							</tbody>					
						</table>
					</div>
				</form>			
			</article>
		</section>
	</div>
</main>
<script>
	document.querySelector("[name=categoryName]").addEventListener('change', (e) => {
		const categoryNo = e.target.value;
		if(categoryNo == ""){
			alert("카테고리를 선택해주세요");
			return;
		}
		$.ajax({
			url : '<%= request.getContextPath()%>/admin/productView',
			method : 'POST',
			dataType : 'json',
			data : {categoryNo},
			success(products){
				console.log(products);
				//const ul = document.querySelector(".codiProduct ul");
				const tbody = document.querySelector("#productTable tbody");
				tbody.innerHTML = '';
				
					products.forEach((product) => {
						if(product == '') return;
						
						const {productCode, productName, productPrice, productSize, productColor, attachmentList} = product;
						const {productRenamedFilename} = attachmentList[0];
						if(productRenamedFilename == undefined) {
							return;
						}
						const li =
							`
							<tr>
								<td><input type="checkbox" id="pdCode" value="\${productCode}" name="pdCode" /></td>
								<td><img src="<%= request.getContextPath()%>/upload/admin/\${productRenamedFilename}"
									id="\${productCode}" class="img"/></td>
								<td><label for="pdCode">\${productCode}</label></td>
								<td id="productName">\${productName}</td>
								<td id="productPrice">\${productPrice}</td>
								<td id="productSize">\${productSize}</td>
								<td id="productColor">\${productColor}</td>
							</tr>
							`;
						tbody.insertAdjacentHTML('afterbegin', li);
					})
				},					
			error : console.log
			})
		});
		
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>