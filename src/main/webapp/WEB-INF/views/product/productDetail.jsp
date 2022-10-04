<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.icodi.admin.model.dto.Product"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductAttachment"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.icodi.admin.model.dto.ProductExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product.css" />
<%
	List<ProductExt> productList = (List<ProductExt>)request.getAttribute("productList");
	int productPrice = productList.get(0).getProductPrice();
	String productName = productList.get(0).getProductName(); 
	String productInfo = productList.get(0).getProductInfo();
	DecimalFormat priceFormat = new DecimalFormat("#,###");
%>
	<main>
		<section>
			<article>
				<div class="content-wrap">
					<div class="product-wrap">
						<div class="img-wrap">
							<div id="product-img">			
		                        <%
		                           if(productList != null && !productList.isEmpty()) {
		                              for(ProductExt product : productList) {
		                                 List<ProductAttachment> attachments = product.getAttachmentList();
		                                 if(attachments != null && !attachments.isEmpty()) {
		                                    for(ProductAttachment attach : attachments) {
		                                       if(attach.getProductRenamedFilename() == null) break;
		                        %>
		                           <img src="<%= request.getContextPath()%>/upload/admin/<%= attach.getProductRenamedFilename() %>" id="image"/>
		                        <%
		                                    }
		                                 }
		                               }
		                           }
		                        %>
                     		</div>    
						</div>
						<div class="product-info-wrap">
							<h2 class="productName"><%= productName %></h2>
							<table>
								<tbody>
									<tr>
										<th colspan="">price</th>
										<td><%= priceFormat.format(productPrice) %>원</td>
									</tr>
									<tr>
										<th>color</th>
										<td>
											<ul class="color-list">
												<% for(ProductExt product : productList) { %>
													<li class="color"><%= product.getProductColor() %></li>
												<% } %>
											</ul>
											<span class="essential">[필수] 옵션을 선택해주세요</span>
										</td>
									</tr>
									<tr>
										<th>size</th>
										<td>
											<ul class="size-list">
												<% for(ProductExt product : productList) { %>
													<li class="size size-disabled"><%= product.getProductSize() %></li>
												<% } %>
											</ul>
											<span class="essential">[필수] 옵션을 선택해주세요</span>
										</td>
									</tr>
									<tr>
										<th></th>
										<td id="soldOut">
											
										</td>
									</tr>
								</tbody>
							</table>
		
							<div class="total-product-wrap">
								<form action="" method="POST" name="totalProductFrm">
									<table class="totalProductList">
										<tbody>
										
										</tbody>
									</table>
								</form>
							</div>
							<div class="total-price">
								TOTAL : 
								<span id="totalPrice" class="totalPriceVal">0</span>원
								(<span id="totalCount" class="totalCountVal">0</span>개)
							</div>
							<button id="buy" type="button">BUY IT NOW</button>
							<br />
							<button id="cart" type="button">ADD TO CART</button>
						</div>
					</div>
					<div class="product-detail-wrap">
						<div id="detail-header"><span>DETAIL</span></div>
						<div id="detail-content">
							<div id="info">
								<%= productInfo.replace("\r\n", "<br/>") %>
							</div>
						</div>
					</div>
				</div>
			</article>
		</section>
	</main>
<script>

// 컬러 선택을 통해 선택버튼 비활성화 처리 및 사이즈 버튼 활성화 처리
document.querySelectorAll(".color").forEach((target) => {
	target.addEventListener('click', (e) => {
		const color = e.target;
		const size = document.querySelectorAll(".size-list li"); 
			
		if(color.classList.value.indexOf('product-select') == -1) {
			color.classList.add('product-select');
			size.forEach((li) => li.classList.remove('size-disabled'));
		} else {
			color.classList.remove('product-select');		
			size.forEach((li) => li.classList.add('size-disabled'));
			size.forEach((li) => li.classList.remove('product-select'));
		}
	});
});

document.querySelectorAll(".size").forEach((target) => {
	target.onclick = (e) => {
		const size = e.target;
		const color = document.querySelector(".color-list li");
		
		if(size.classList.value.indexOf('size-disabled') != -1 || size.classList.value.indexOf('product-select') != -1) {
			size.disabled = true;
			size.classList.remove('product-select');	
		} else {
			// 추가리스트에 기존에 추가된 상품인지 체크 및 후처리
			const sizeList = document.querySelectorAll(".size-list li");
			const colorList = document.querySelectorAll(".color-list li");
			
			size.classList.add('product-select');
			sizeList.forEach((li) => li.classList.add('size-disabled'));
			
			const selected = document.querySelectorAll(".product-select");
			
			let totalColor = '';
			let totalSize = '';
			
			[...selected].forEach((select) => {
				if(select.className.indexOf('color') != -1) {
					totalColor = select.innerHTML;
				} else if(select.className.indexOf('size') != -1) {
					totalSize = select.innerHTML;					
				}
			});
			
			sizeList.forEach((li) => li.classList.remove('product-select'));
			colorList.forEach((li) => li.classList.remove('product-select'));
			
			const tbody = document.querySelector(".totalProductList tbody");
			const productCode = `<%= productName%>_\${totalSize}_\${totalColor}`;
			
			for(let i = 0; i < tbody.children.length; i++) {
				if(productCode == tbody.children[i].id) {
					alert("이미 추가한 상품입니다.");
					return;
				}
			}
			
			document.querySelector("#soldOut").innerHTML = '';
			// 품절 확인
			<% if(productList != null && !productList.isEmpty()) {
				for(Product product : productList) {
			%>
				if("<%= product.getProductCode()%>" == productCode && <%= product.getProductStock()%> == 0) {
						document.querySelector("#soldOut").innerHTML = '해당 상품은 품절입니다.';
						return;
					}
		 	<% }
			} %>
			
			const tr = `
			<tr id="\${productCode}" class="productList">
				<td class='totalSelect'>
					<p class="product">
						<span id="totalProductName"><%= productName %></span>
						<br />
						- \${totalColor}/\${totalSize}
					</p>
					<span class="count"><input type="number" name="count" min="1" max="100" value="1" onchange="countChange(event)"/></span>
					<span class="delete" onclick="productDelete(event)">X</span>
					<span class="price"><%= priceFormat.format(productPrice) %></span>원
					<input type="hidden" name="productCode" value="\${productCode}" />
					<input type="hidden" name="productCount" value="1"/>
				</td>
			</tr>
			`;
			tbody.insertAdjacentHTML('beforeend', tr);
			
			// totalPrice
			const totalPrice = [...document.querySelectorAll('.price')].map((price) => {
					return Number(price.innerHTML.replace(",", ""));
				}).reduce((total, price) => {
					return total + price;
				}, 0);
			document.querySelector("#totalPrice").innerHTML = totalPrice.toLocaleString('ko-KR');
			totalCount();
		}
	}
});

const countChange = (e) => {
	let totalCnt = totalCount();

	const countVal = e.target.value;
	const price = e.target.parentElement.nextElementSibling.nextElementSibling;
	const inputCount = e.target.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling;
	price.innerHTML = (countVal * <%= productPrice %>).toLocaleString('ko-KR');
	inputCount.value = countVal;
	
	totalPrice(totalCnt);
};

const totalCount = (e) => {
	const totalCount = [...document.querySelectorAll('[name=count]')].map((count) => {
		return Number(count.value);
	}).reduce((total, count) => {
		return total + count;
	}, 0);
	document.querySelector("#totalCount").innerHTML = totalCount;
	return totalCount;
};

const totalPrice = (totalCnt) => {
	const totalPrice = document.querySelector("#totalPrice");
	totalPrice.innerHTML = (totalCnt * <%= productPrice %>).toLocaleString('ko-KR');
}

const productDelete = (e) => {
	e.target.parentElement.parentElement.remove();
	let totalCnt = totalCount();
	totalPrice(totalCnt);
};

// 바로주문 및 장바구니 추가 처리
document.querySelectorAll("button").forEach((button) => {
	button.addEventListener('click', (e) => {
		const frm = document.totalProductFrm;
		const tbody = document.querySelector(".totalProductList tbody");
		const productCode = document.querySelectorAll("[name=productCode]");
		let cartList = [];
		[...productCode].forEach((code) => {
			const productCount = code.nextElementSibling.value;
			cartList.push({"productCode":code.value, "productCount":productCount});	
		});
			
		// 상품을 선택하지 않으면 alert
		if(tbody.children.length == 0) {
			alert("상품을 선택해주세요.");
			return;
		}
		
		// buy it now
		if(e.target.id === 'buy') {
			if(<%= loginMember == null %>) {
				alert("로그인 후 이용할 수 있습니다.");
				location.href = "<%= request.getContextPath()%>/member/memberLogin";
				return;
			}
			frm.action = "<%= request.getContextPath()%>/member/order";
			frm.submit();
			return;
		}
		// add to cart
		else if(e.target.id === 'cart') {
			if(<%= loginMember == null %>) {
				alert("로그인 후 이용할 수 있습니다.");
				location.href = "<%= request.getContextPath()%>/member/memberLogin";
				return;
			}
			$.ajax({
				url : '<%= request.getContextPath()%>/member/findCart',
				type : "GET",
				data : {data : JSON.stringify(cartList)},
				success(response) {
					// 기존 장바구니에 존재하는지 체크
					if(response[0]) {
						if(confirm('장바구니에 존재하는 상품입니다. 그래도 추가하시겠습니까?')) {
							addCart(cartList);
						} else return;
					} else {
						addCart(cartList);
					}
				},
				error : console.log
			})
			
		}
	})
})
const addCart = (cartList) => {
	$.ajax({
		url : '<%= request.getContextPath()%>/member/addCart',
		type : "POST",
		data : {data : JSON.stringify(cartList)},
		success(response) {
			if(confirm('장바구니로 이동하시겠습니까?')) {
				location.href = '<%= request.getContextPath()%>/member/memberCart';
				return;
			}
		},
		error : console.log
	});
};
$(document).ready(function () {
    $('#product-img').slick({
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
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>