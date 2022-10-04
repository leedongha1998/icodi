<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductExt"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductOrder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.icodi.common.MemberOrderProductManager"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myShopping.css" />
<%
	List<MemberOrderProductManager> orderList = (List<MemberOrderProductManager>)request.getAttribute("orderList");
	String memberName = loginMember.getMemberName();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Map<String, Object> defaultDate = (Map<String, Object>)request.getAttribute("defaultDate");
	Date dateStart = new SimpleDateFormat("yy/MM/dd").parse((String)defaultDate.get("start"));
	Date dateEnd = new SimpleDateFormat("yy/MM/dd").parse((String)defaultDate.get("end"));
	DecimalFormat numberFormat = new DecimalFormat("#,###");
%>
	<main>
		<section>
			<article>
				<div class="title-wrap">
					<h2>MY SHOPPING</h2>
				</div>
				<div class="date-wrap">
					<div>일자 조회 </div>
					<input type="date" name="start" id="start" value="<%= dateFormat.format(dateStart) %>"/>-
					<input type="date" name="end" id="end" value="<%= dateFormat.format(dateEnd) %>" />
					<button id="dateSelectBtn">조회</button>
				</div>
				<div class="content-wrap">
					<div class="member-info-orderList">
						<div class="wrap">
							<div class="member-info-wrap">
								<img src="<%= request.getContextPath() %>/upload/codiboard/defaultProfile.png" id="profileImg"/>
								<div id="memberName"><%= memberName %></div>
								<hr />
								<img src="<%= request.getContextPath() %>/upload/main/dollar-coins.png" id="pointImg"/>
								<div>적립금</div>
								<div class="memerPoint-wrap"><div id="memberPoint" class="strong"><%= loginMember.getPoint() %></div>원</div>
							</div>
							<nav>
								<div id="nav-header">마이쇼핑정보</div>
								<div id="cart" class="navImgWrap">
									<img src="<%= request.getContextPath() %>/upload/main/shopping-cart.png"/>
									<a href="<%= request.getContextPath()%>/member/memberCart">카트</a>
								</div>
								<div id="order" class="navImgWrap">
									<img src="<%= request.getContextPath() %>/upload/main/express-delivery.png" />
									<a href="<%= request.getContextPath()%>/member/order">주문/배송내역</a>
								</div>
								<div id="cscenter" class="navImgWrap">
									<img src="<%= request.getContextPath() %>/upload/main/conversation.png" />
									<a href="<%= request.getContextPath()%>/cs_center">1:1문의하기</a>
								</div>
							</nav>
						</div>
						
						<div class="content">
							<div class="content-header-wrap">
								<h3>주문 / 배송조회</h3>
							</div>
							<table id="orderListTable">
								<thead>
									<tr>
										<th>
											주문일자<br />
											[주문번호]
										</th>
										<th>상품정보</th>
										<th>구매금액</th>
										<th>구매수량</th>
										<th>상태</th>
									</tr>
								</thead>
								<tbody>
									<%
									if(orderList != null && !orderList.isEmpty()) {
										for(MemberOrderProductManager orders : orderList) {
											ProductExt product = orders.getProduct();
											ProductOrder order = orders.getProductOrder();
									%>
									<tr>
										<td class="date">
											<div id="orderDate"><%= dateFormat.format(order.getOrderDate()) %></div>
											<div id="orderNo">[<%= order.getOrderNo() %>]</div>
										</td>
										<td>
											<a href="<%= request.getContextPath()%>/product/detail?product_name=<%= product.getProductName() %>">
												<div id="productName" class="strong"><%= product.getProductName() %></div>
											</a>
												<div>[옵션:<%= product.getProductColor() %>/<%=product.getProductSize() %>]</div>
										<td><div id="productPrice"><%=numberFormat.format(product.getProductPrice() * order.getOrderTotalCount())%></div></td>
										</td>
										<td><div id="productAmount"><%= order.getOrderTotalCount() %></div></td>
										<td><div id="productStatus"><%= order.getOrderStatus() %></div></td>
									</tr>
									<% 	}
									} %>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="nav-orderInfo-wrap">
						<div class="orderInfo-wrap">
							<div id="orderInfo-header">
								주문상태안내
							</div>
							<div class="orderInfo">
								<div class="wait-wrap info">
									<div class="infoHeader">입금대기</div>
									<img src="<%= request.getContextPath() %>/upload/main/credit-card.png" class="infoImg"/>
									<div>입금 전 상태입니다.</div>
								</div>
								<div class="deliveryWait-wrap info">
									<div class="infoHeader">배송 준비중</div>
									<img src="<%= request.getContextPath() %>/upload/main/box.png" class="infoImg"/>
									<div>입금이 확인되어, <br />ICODI가 발송할 상품을 열심히 준비하고 있습니다.</div>
								</div>
								<div class="delivery-ing-wrap info">
									<div class="infoHeader">배송 중</div>
									<img src="<%= request.getContextPath() %>/upload/main/delivery-truck.png" class="infoImg"/>
									<div>상품을 <span class="strong"><%= memberName %></span>님께 <br />배송하고 있습니다.</div>
								</div>
								<div class="delivery-finish-wrap info">
									<div class="infoHeader">배송완료</div>
									<img src="<%= request.getContextPath() %>/upload/main/home.png" class="infoImg"/>
									<div>상품이 <span class="strong"><%= memberName %></span>님께 <br />전달 완료 되었습니다.</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
		</section>
	</main>
<script>
document.querySelector("#dateSelectBtn").addEventListener('click', (e) => {
	const start = document.querySelector("#start").value;
	const end = document.querySelector("#end").value;
	const memberId = "<%= loginMember.getMemberId()%>";

	$.ajax({
		url : '<%= request.getContextPath()%>/member/myShoppingSelectDate',
		type : "GET",
		dataType : 'json',
		data : {start, end, memberId},
		success(response) {
			const tbody = document.querySelector("#orderListTable tbody");
			tbody.innerHTML = '';
			
			response.forEach((order) => {
				const {product, productOrder} = order;
				const {productName, productColor, productSize, productPrice} = product;
				const {orderDate, orderNo, orderStatus, orderTotalPrice, orderTotalCount} = productOrder;

				const year = orderDate.slice(-4);
				const month = orderDate.substr(0, orderDate.indexOf('월')).padStart(2, 0);
				const day = orderDate.substring(orderDate.search(/'월'|\s/)+1, orderDate.indexOf(',')).padStart(2, 0);
				const date = year + "-" + month + "-" + day;

				const newTr = `
				<tr>
					<td class="date">
						<div id="orderDate">\${date}</div>
						<div id="orderNo">[\${orderNo}]</div>
					</td>
					<td>	
						<a href="<%= request.getContextPath()%>/product/detail?product_name=\${productName}">
							<div id="productName" class="strong">\${productName}</div>
						</a>
							<div>[옵션:\${productColor}/\${productSize}]</div>
					</td>
					<td><div id="productPrice">\${(productPrice * orderTotalCount).toLocaleString('ko-KR')}</div></td>
					<td><div id="productAmount">\${orderTotalCount}</div></td>
					<td><div id="productStatus">\${orderStatus}</div></td>
				</tr>
				`;
				tbody.insertAdjacentHTML('beforeend', newTr);
			})
		},
		error : console.log
	});
});
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>