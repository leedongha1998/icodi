<%@page import="java.util.Arrays"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductAttachment"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductExt"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.icodi.member.model.dto.MemberRole"%>
<%@page import="com.kh.icodi.member.model.dto.MemberCart"%>
<%@page import="com.kh.icodi.admin.model.dto.Product"%>
<%@page import="com.kh.icodi.common.MemberProductManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<%
	List<MemberProductManager> orderList = (List<MemberProductManager>)request.getAttribute("order");
	Member member = orderList.get(0).getMember();
	List<ProductAttachment> attachment = orderList.get(0).getProductExt().getAttachmentList();
	DecimalFormat numFormat = new DecimalFormat("#,###");
	
	String[] phones = member.getPhone().split("-");
	String[] emails = member.getEmail().split("@");

	List<String> phoneList = null;
	List<String> emailList = null;
	if(member.getPhone() != null){
		phoneList = Arrays.asList(phones);
	}
	if(member.getEmail() != null) {
		emailList = Arrays.asList(emails);
	}
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/order.css" />
<main>
	<section>
		<article>
			<form action="<%= request.getContextPath()%>/member/pay" method="POST" name="memberPayFrm">
				<div class="title-wrap">
					<h2>ORDER</h2>
				</div>
				<div class="member-info-wrap">
					<div class="member">
						<span class="strong"><%= member.getMemberName() %>님</span>은, <%= member.getMemberRole() == MemberRole.U ? "[일반]" : "[관리자]" %>회원이십니다.
					</div>
					<div class="mileage">
						적립금 : <span class="strong"><%= numFormat.format(member.getPoint()) %></span>원
					</div>
				</div>
				<div class="help-wrap">상품의 옵션 및 수량 변경은 상품상세 또는 장바구니에서 가능합니다.</div>
				<div class="order-product-header">배송상품 주문내역</div>
				<div class="order-product-view">
					<table class="order-product">
						<thead>
							<tr>
								<th>이미지</th>
								<th>상품정보</th>
								<th>판매가</th>
								<th>수량</th>
								<th>적립금</th>
								<th>배송비</th>
								<th>합계</th>
							</tr>
						</thead>
						<tbody>
						<% if(orderList != null && !orderList.isEmpty()) {
							for(MemberProductManager order : orderList) {	
								Member orderMember = order.getMember();
								ProductExt orderProduct = order.getProductExt();
								MemberCart orderCart = order.getMemberCart();
		
						%>
								<tr>
									<td id="productImg">
										<div id="product-img-wrap">
									<% for(ProductAttachment attach : orderProduct.getAttachmentList()) { 
											if(attach.getProductRenamedFilename() == null) break;
									%>
										<img src="<%= request.getContextPath() %>/upload/admin/<%= attach.getProductRenamedFilename() %>" alt="" />
									<% } %>
										</div>
									</td>
									<td id="productInfo">
										<div class="productName" class="strong"><%= orderProduct.getProductName() %></div>
										<div class="productOption">[옵션:<%=orderProduct.getProductColor() %>/<%= orderProduct.getProductSize() %>]</div>
										<input type="hidden" name="productCode" value="<%= orderProduct.getProductCode() %>" />
										<input type="hidden" name="cartNo" value="<%=orderCart.getCartNo() %>" />
									</td>
									<td><span><span><%= numFormat.format(orderProduct.getProductPrice()) %></span>원</span></td>
									<td>
										<span><%= orderCart.getCartAmount() %></span>
										<input type="hidden" name="cartAmount" value="<%= orderCart.getCartAmount() %>" />
									</td>
									<td><span id="addPoint"><%= numFormat.format((int)(orderProduct.getProductPrice() * orderProduct.getProductPluspoint()) * orderCart.getCartAmount()) %></span></td>
									<td><span>[기본]</span></td>
									<td><span><span id="productPrice"><%= numFormat.format(orderProduct.getProductPrice() * orderCart.getCartAmount()) %></span>원</span></td>
								</tr>
						<%
							}
						}
						%>
						</tbody>
						<tfoot>
							<tr>
								<td>[기본배송]</td>
								<td colspan="6" id="orderProductPrice">상품구매금액 <span id="productTotalPrice"></span>원 + 배송비 2,500 = 합계 : <span id="totalPrice" class="strong"></span>원</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="delivery-info-header">배송정보</div>
				<div class="delivery-info-wrap">
					<table class="delivery-product">
						<tbody>
							<tr>
								<th>주문자</th>
								<td><input type="text" class="input" value="<%= member.getMemberName() %>" /></td>
							</tr>
							<tr>
								<th>주소</th>
								<td>
									<input type="text" class="address" value="<%= member.getAddress() %>" readonly /> <span class="size">기본주소</span>
									<br />
									<input type="text" class="address" value="<%= member.getAddressEx() %>" readonly/> <span class="size">나머지주소</span>
								</td>
							</tr>
							<tr>
								<th>휴대전화</th>
								<td>
									<input type="text" name="phone" id="phone1" maxlength="3" class="input" value="<%= phones[0]%>" />-
									<input type="text" name="phone" id="phone2" maxlength="4" class="input" value="<%= phones[1] %>">-
									<input type="text" name="phone" id="phone3" maxlength="4" class="input" value="<%= phones[2].replace(" ", "") %>"><br>
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td>
									<input type="text" name="email" id="email1" class="input" value="<%= emails[0]%>"/>@
									<input type="text" name="email" id="email2" class="input" value="<%= emails[1] %>">
								</td>
							</tr>
						</tbody>
					</table>
					<div class="fee-info-header">결제 예정 금액</div>
					<div class="fee-info-wrap">
						<table class="total">
							<thead>
								<tr>
									<th>총 주문 금액</th>
									<th>할인 금액</th>
									<th>총 결제예정 금액</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><span class="display"><span id="orderPrice"></span>원</span></td>
									<td><span class="display">-<span id="discount">0</span>원</span></td>
									<td><span class="display">= <span id="total" class="strong"></span>원</span></td>
								</tr>
							</tbody>
						</table>
						<div class="discount-info-wrap">
							<table>
								<tbody>
									<tr>
										<th>적립금</th>
										<td ><input type="text" value="0" id="usePoint" class="input"/> <span class="size">원 (총 사용가능 적립금 : <span class="strong"><%= member.getPoint() %></span>원)</span></td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<div class="payment-info-header">결제수단</div>
						<div class="total-wrap">
							<div>
								<div class="paymentSelect">
									<div class="cash-wrap">
										<input type="radio" name="payment" id="cash" checked />
										<label for="cast">무통장입금</label>
									</div>
									<div class="card-wrap">
										<input type="radio" name="payment" id="card" />
										<label for="card">카드결제</label>
									</div>
								</div>
								<div class="payment-info-wrap">
									<table id="cash-info" class="info">
										<tbody>
											<tr>
												<th>입금자명</th>
												<td><input type="text" name="paymentName" class="bank" value="<%= member.getMemberName() %>" readonly/></td>
											</tr>
											<tr>
												<th>입금은행</th>
												<td><input type="text" name="bank" class="bank" value="KH은행:1111-888-666666 (주)아이코디" readonly/></td>
											</tr>
										</tbody>
									</table>
									<div id="card-info" class="info">
										<p>최소 결제 가능 금액은 결제금액에서 배송비를 제외한 금액입니다.</p>
									</div>
								</div>
							</div>
							<div class="final-wrap">
								<span class="final-wrap-header">최종결제금액</span>
								<div class="flex"><div id="total" class="strong"></div> 원</div>
								<input type="hidden" name="finalPayment" />
								<input type="hidden" name="finalMemberPoint" />
								<input type="hidden" name="finalMemberId" value="<%= member.getMemberId() %>" />
								<input type="hidden" name="finalPrice" />
								<input type="hidden" name="finalUsePoint" />
								<input type="hidden" name="finalAddPoint" />
								<button id="orderBtn" type="button">결제</button>
							</div>
						</div>
					</div>					
				</div>
			</form>
		</article>
	</section>
</main>
<script>
// 이미지 자동 슬라이드
[...document.querySelectorAll("#product-img-wrap")].forEach((img) => {
   $(img).ready(function () {
        $(img).slick({
            infinite: true,
            speed: 500,
            fade: true,
            cssEase: 'linear',
            autoplay: true,
            autoplaySpeed: 1000,
            prevArrow: "",
            nextArrow: ""
        });
    });
});
	
// 주문 상품에 따른 총 가격 랜더링
window.addEventListener('load', (e) => {
	const productPrice = document.querySelectorAll("#productPrice");
	const productTotalPrice = document.querySelector("#productTotalPrice");
	const totalPrice= document.querySelector("#totalPrice");
	
	const price = [...productPrice].map((span) => Number(span.innerHTML.replace(",",""))).reduce((total, price) => total + price);
	
	productTotalPrice.innerHTML = price.toLocaleString('ko-KR');
	totalPrice.innerHTML = (price + 2500).toLocaleString('ko-KR');
	
	const orderPrice = document.querySelector("#orderPrice");
	const discount = document.querySelector("#discount");
	const total = document.querySelectorAll("#total");
	
	orderPrice.innerHTML = totalPrice.innerHTML;
	[...total].forEach((div) => div.innerHTML = totalPrice.innerHTML);
});
	
// 사용자 적립금 사용
document.querySelector("#usePoint").addEventListener('change', (e) => {
	const point = e.target;
	const orderPrice = document.querySelector("#orderPrice");
	const usePoint = document.querySelector("#usePoint");
	const memberPoint = <%= member.getPoint()%>;
	const discount = document.querySelector("#discount");
	const total = document.querySelectorAll("#total");
	const totalPrice= document.querySelector("#totalPrice");
	
	if(memberPoint < point.value) {
		alert('사용가능한 적립금보다 많습니다.');
		point.value = 0;
		point.focus();
		return;
	}
	
	if(Number(totalPrice.innerHTML.replace(",","")) < usePoint.value) {
		alert('상품 가격보다 사용적립금이 더 많습니다.')
		point.value = 0;
		point.focus();
		return;
	}
	
	discount.innerHTML = (point.value).toLocaleString('ko-KR');
	
	total.forEach((div) => 
		div.innerHTML = (Number(orderPrice.innerHTML.replace(",","")) - Number(discount.innerHTML.replace(",",""))).toLocaleString('ko-KR'));
});
	
// 결제수단에 따른 안내 사항 보여주기
document.querySelectorAll("[name=payment]").forEach((payment) => {
	payment.addEventListener('change', (e) => {
		const cashInfo = document.querySelector("#cash-info");
		const cardInfo = document.querySelector("#card-info");
		const finalPayment = document.querySelector("[name=finalPayment]");
		
		if(e.target.id == 'cash') {
			cashInfo.style.display = 'block';
			cardInfo.style.display = 'none';
			finalPayment.value = 'CC';
			return;
		} else {
			cardInfo.style.display = 'block';
			cashInfo.style.display = 'none';
			finalPayment.value = 'CA';
			return;
		}
	});
});

// 주문버튼 클릭 시
document.querySelector("#orderBtn").addEventListener('click', (e) => {
	const frm = document.memberPayFrm;
	
	const productCode = document.querySelectorAll("[name=productCode]");
	const finalPayment = document.querySelector("[name=finalPayment]");
	const finalMemberPoint = document.querySelector("[name=finalMemberPoint]");
	const finalPrice = document.querySelector("[name=finalPrice]");
	const payment = document.querySelectorAll("[name=payment]");
	const addPoint = document.querySelectorAll("#addPoint");
	const finalUsePoint = document.querySelector("[name=finalUsePoint]");
	const usePoint = document.querySelector("#usePoint");
	const total = document.querySelector("#total");
	
	let productList = [];
	
	// 재고 확인
	for(let i = 0; i < productCode.length; i++) {
	<% 
		for(MemberProductManager manager : orderList) {
			ProductExt product = manager.getProductExt();
	%>
			productAmounts = productCode[i].parentElement.nextElementSibling.nextElementSibling.innerText;
			
			if("<%= product.getProductCode()%>" == productCode[i].value && <%= product.getProductStock() %> < productAmounts) {
				alert("해당 상품[<%= product.getProductCode()%>]에 대한 재고가 부족합니다. 고객센터에 문의해주세요.");
				return;
			} else {
				productList.push({"productCode":`\${productCode[i].value}`,"productAmount":`\${productAmounts}`});
			} 
		<%
		}
		%>
	};

	[...payment].forEach((pay) => {
		if(pay.checked == true) {
			finalPayment.value = pay.id;
			return;
		}
	});
	
	const point = [...addPoint].map((text) => Number(text.innerHTML.replace(",", ""))).reduce((total, price) => total+price);
	finalMemberPoint.value = point;
	finalPrice.value = total.innerHTML.replace(",","");
	finalUsePoint.value = usePoint.value.replace(",", "");
	
	// 상품 재고 삭제처리
	$.ajax({
		url : '<%= request.getContextPath()%>/product/deleteStock',
		dataType : 'text',
		type : "POST",
		traditional:true,
		data : {data : JSON.stringify(productList)},
		success(response) {
			if(finalPayment.value == 'cash') {
				alert("주문이 완료되었습니다.");
				frm.submit();
			} else {
			   var IMP = window.IMP;      
			   IMP.init("imp83181016");
			   IMP.request_pay({
				   pg: 'html5_inicis',
	               pay_method: 'card',
	               merchant_uid: 'merchant_' + new Date().getTime(),
	               name: '아이코디',
	               amount: Number(total.innerHTML.replace(",","")),
	               buyer_email: '<%= member.getEmail() %>',
	               buyer_name: '<%= member.getMemberName()%>',
	               buyer_tel: '<%= member.getPhone()%>',
	               buyer_addr: '<%= member.getAddress()%> <%= member.getAddressEx()%>',
	               buyer_postcode: '111-111',
	               m_redirect_url: 'http://localhost:9090/icodi/'
			   }, function (rsp) {
			      if (rsp.success) {
			         var msg = '결제가 완료되었습니다.';
			         msg += '결제 금액 : ' + rsp.paid_amount;
			         msg += '카드 승인번호 : ' + rsp.apply_num;
					 frm.submit();
			      } else {
			         var msg = '결제에 실패하였습니다.';
			      }
			      alert(msg);
			   });
			}
		},
		error : console.log
	});
});
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>