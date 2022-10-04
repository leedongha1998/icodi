<%@page import="com.kh.icodi.admin.model.dto.ProductSize"%>
<%@page import="com.kh.icodi.admin.model.dto.CategoryNo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productEnroll.css">
<main>
	<div class="content-wrap">
		<div class="header-wrap">
			<h2>상품 등록</h2>
		</div>
		<section>
			<article>
				<form action="<%=request.getContextPath()%>/admin/productEnroll" method="POST" name="productEnrollFrm" enctype="multipart/form-data">
					<table>
						<tbody>
							<tr>
								<th>카테고리</th>
								<td>
									<select name="categoryName">
										<option value="<%=CategoryNo.TOP%>">상의</option>
										<option value="<%=CategoryNo.BOTTOM%>">하의</option>
										<option value="<%=CategoryNo.SHOES%>">신발</option>
										<option value="<%=CategoryNo.ACC%>">악세사리</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>상품명</th>
								<td>
									<input type="text" name="productName" required/>
								</td>
							</tr>
							<tr>
								<th>판매가</th>
								<td>
									<input type="text" name="productPrice" required />
								</td>
							</tr>
							<tr>
								<th>상품옵션</th>
								<td>
									<table id="option-tbl">
										<tr>
											<th>옵션명</th>
											<th>옵션값</th>
										</tr>
										<tr>
											<td>사이즈</td>
											<td>
												<select name="productSize">
													<option value="<%=ProductSize.S%>">S</option>
													<option value="<%=ProductSize.M%>">M</option>
													<option value="<%=ProductSize.L%>">L</option>
													<option value="<%=ProductSize.F%>">F</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>색상</td>
											<td>
												<select name="productColor">
													<option value="black">블랙</option>
													<option value="white">화이트</option>
													<option value="red">레드</option>
													<option value="blue">블루</option>
													<option value="yellow">옐로우</option>
												</select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th>상품 상세설명</th>
								<td>
									<textarea name="productInfo" cols="40" rows="7"></textarea>
								</td>
							</tr>
							<tr>
								<th>상품용 이미지</th>
								<td class="td-file"><input type="file" name="productImage1"/></td>
								<td class="td-file"><input type="file" name="productImage2"/></td>
							</tr>
							<tr>
								<th>코디용 이미지</th>
								<td><input type="file" name="codiImage" /></td>
							</tr>
						</tbody>
					</table>
					<button id="btn-1">상품등록</button>
				</form>
			</article>
			
			<hr />
			
				<div class="header-wrap">
					<h2>상품 재고 관리</h2>
				</div>
			<article>
				<form action="<%= request.getContextPath()%>/admin/productStock" method="POST" name="productStockFrm">
					<table>
						<tbody>
							<tr>
								<th>상품코드</th> <%-- 검색기능으로 구현하기 --%>
								<td><input type="text" id="productCode" name="productCode"/></td>
							</tr>
							<tr>
								<th>입/출고 여부</th>
								<td id="td-io">
									<div class="div-io">
									<input type="radio" name="ioStatus" value="I"/>
									<span>입고</span>
									</div>
									<div class="div-io">
									<input type="radio" name="ioStatus" value="O"/>
									<span>출고</span>
									</div>
								</td>
							</tr>
							<tr>
								<th>수량</th>
								<td>
									<input type="number" name="ioStock" id="" />
								</td>
							</tr>
						</tbody>
					</table>
					<button id="btn-2">재고등록</button>
				</form>
			</article>
		</section>
	</div>
</main>
<script>
$("#productCode").autocomplete({
       source(request, response){
     	  console.log(request);
     	  const {term} = request;
     	  if(!/.+/.test(term)) return;
     	  
     	  $.ajax({
     		  url : "<%= request.getContextPath()%>/productCodeList",
     		  method: "GET",
     		  data : {term},
     		  success(csv){
     			  console.log(csv);
     			  const arr = csv.split(",").map((classmate) => ({
     				 label : classmate,
     				 value : classmate
     			  }));
     			  console.log(arr);
     			  response(arr);
     			  
     		  },
     		  error(jqxhr, statusText, err){
     			  console.log(jqxhr, statusText, err);
     		  }
     		  
     	  });
       },
       focus(e, select){
     	  return false;
       }
     });
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>