<%@page import="com.kh.icodi.admin.model.dto.ProductOrder"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductExt"%>
<%@page import="com.kh.icodi.common.MemberOrderProductManager"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/adminOrder.css" />

<%
	List<MemberOrderProductManager> list = (List<MemberOrderProductManager>)request.getAttribute("list");
	String pagebar = (String)request.getAttribute("pagebar");
%>

<main>
	<section>
		<article>
			<div class="header-wrap">
				<h2>결제완료</h2>
			</div>
			<div class="order-list-wrap">
				<table>
					<thead>
						<tr>
							<th>주문번호</th>
							<th>상품코드</th>
							<th>회원아이디</th>
							<th>회원이름</th>
							<th>결제수단</th>
							<th>상태</th>
							<th>처리</th>
						</tr>
					</thead>
					<tbody>
						<%
						if(list != null && !list.isEmpty()) {
							for(MemberOrderProductManager orderList : list) {
								ProductExt product = orderList.getProduct();
								Member member = orderList.getMember();
								ProductOrder order = orderList.getProductOrder();
						%>
						<tr>
							<td><%= order.getOrderNo() %></td>
							<td><%= product.getProductCode() %></td>
							<td><%= member.getMemberId() %></td>
							<td><%= member.getMemberName() %></td>
							<td><%= order.getOrderPayments() %></td>
							<td><%= order.getOrderStatus() %></td>
							<td>
							<% if(order.getOrderStatus().equals("배송준비중")) { %>
								<button type="button" id="checkedDeliveryBtn" data-order-no="<%= order.getOrderNo()%>">배송처리</button>
							<% } else { %>
								<button type="button" class="style-disabled" id="checkedDeliveryBtn" data-order-no="<%= order.getOrderNo()%>" disabled><%= order.getOrderStatus() %></button>
							<% } %>
							</td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
				</table>
			</div>
		</article>
	</section>
	<nav>
		<div id="pagebar">
			<%= pagebar %>
		</div>
	</nav>
</main>
<script>
document.querySelectorAll("#checkedDeliveryBtn").forEach((btn) => {
	btn.addEventListener('click', (e) => {
		const orderNo = e.target.dataset.orderNo;
		const updateStatus = '배송중';
		if(!confirm(`\${orderNo}에 대한 배송처리를 하시겠습니까?`)) return;
		
		$.ajax({
			url : '<%= request.getContextPath()%>/admin/updateStatus',
			type : "POST",
			data : {orderNo, updateStatus},
			success(response) {
				location.reload();
			 },
			error : console.log
		}); 
	})
})
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>