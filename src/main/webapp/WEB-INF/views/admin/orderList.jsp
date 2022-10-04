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
				<h2>주문리스트 - 취소/교환처리</h2>
			</div>
			<div class="search-bar-wrap">
				<select name="searchKeyword" id="searchKeyword">
					<option value="order_no">주문번호</option>
					<option value="member_id">회원아이디</option>
				</select>
				<input type="text" name="searchValue" id="searchValue"/>
				<button type="button" id="searchBtn">조회</button>
			</div>
			<div class="order-list-wrap">
				<table id="orderListTable">
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
							<td class="strong"><%= order.getOrderNo() %></td>
							<td><%= product.getProductCode() %></td>
							<td><%= member.getMemberId() %></td>
							<td><%= member.getMemberName() %></td>
							<td><%= order.getOrderPayments() %></td>
							<td class="status strong"><%= order.getOrderStatus() %></td>
							<td>
								<% if(!order.getOrderStatus().equals("취소") && !order.getOrderStatus().equals("환불") && !order.getOrderStatus().equals("교환")) { %>
								<button type="button" id="cancle" class="statusBtn" data-order-no="<%= order.getOrderNo() %>" value="취소">취소처리</button>
								<button type="button" id="refund" class="statusBtn" data-order-no="<%= order.getOrderNo() %>" value="환불">환불처리</button>
								<button type="button" id="change" class="statusBtn" data-order-no="<%= order.getOrderNo() %>" value="교환">교환처리</button>
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
document.querySelector("#searchBtn").addEventListener('click', (e) => {
	const searchKeyword = document.querySelector("#searchKeyword").value;
	const searchValue = document.querySelector("#searchValue").value;
	
	$.ajax({
		url : '<%= request.getContextPath()%>/admin/searchOrderList',
		type : 'GET',
		data : {searchKeyword, searchValue},
		success(response) {
			const {list, pagebar} = response;
			console.log({pagebar});
			const tbody = document.querySelector("#orderListTable tbody");
			const oldPagebar = document.querySelector("#pagebar");
			tbody.innerHTML = '';
			
			if(!list) return;
			
			list.forEach((order) => {
				const {member, product, productOrder} = order;
				const {memberId, memberName} = member;
				const {productCode} = product;
				const {orderNo, orderPayments, orderStatus} = productOrder;
				const tr = `
				<tr>
					<td class="strong">\${orderNo}</td>
					<td>\${productCode}</td>
					<td>\${memberId}</td>
					<td>\${memberName}</td>
					<td>\${orderPayments}</td>
					<td class="status strong">\${orderStatus}</td>
					<td>
						<button type="button" id="cancle" class="statusBtn" data-order-no="\${orderNo}" value="취소">취소처리</button>
						<button type="button" id="refund" class="statusBtn" data-order-no="\${orderNo}" value="환불">환불처리</button>
						<button type="button" id="change" class="statusBtn" data-order-no="\${orderNo}" value="교환">교환처리</button>
					</td>
				</tr>
				`
				tbody.insertAdjacentHTML('beforeend', tr);
			})
			oldPagebar.innerHTML = pagebar;
		},
		error : console.log,
		complete() {
			const status = document.querySelectorAll(".status");
			[...status].forEach((td) => {
				if(td.innerHTML == '취소' || td.innerHTML == '환불' || td.innerHTML == '교환') {
					td.nextElementSibling.innerHTML = '';
				}
				
			})
		}
	})
});

document.querySelectorAll(".statusBtn").forEach((btn) => {
	btn.onclick = (e) => {
		const updateStatus = e.target.value;
		const orderNo = e.target.dataset.orderNo;
		const allBtn = document.querySelectorAll(".statusBtn");
		console.log(orderNo);
		if(!confirm(`\${orderNo}에 대해 \${updateStatus}처리를 하시겠습니까?`)) return;
		
		$.ajax({
			url : '<%= request.getContextPath()%>/admin/updateStatus',
			type : "POST",
			data : {updateStatus, orderNo},
			success(response) {
				location.reload();
			},
			error : console.log
		});
	}
});
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>