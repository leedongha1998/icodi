<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- src/main/webapp 부터 시작! -->
<%@include file="/WEB-INF/views/common/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/main.css" />
<br />
<br />
<style>
span.moreView {
float: right; margin-right: 15px; font-size: 13.5px; color: #939393;
}
span.moreView:link { color: #939393; }
span.moreView:hover { color: black; }
span.moreView:visited {color: #939393; }
span.moreView:active { color: black; }

</style>
<section>
	<div id="sliderContainer">
		<ul id="slider">
			<li><a
				href="<%= request.getContextPath()%>/csCenter/csCenterView?no=3">
					<img src="<%= request.getContextPath()%>/upload/main/delay.png"
					alt="">
			</a></li>
			<li><a href="<%= request.getContextPath()%>/codibook/create">
					<img
					src="<%= request.getContextPath()%>/upload/main/toCodiMake.png"
					alt="">
			</a></li>
			<li><a href="<%= request.getContextPath()%>/codi/hotCodiList">
					<img
					src="<%= request.getContextPath()%>/upload/main/toCodiBoard.png"
					alt="">
			</a></li>
		</ul>
		<div id="newItem">
		<p style="text-align:right; margin:0 35px 20px 0; font-size: 14px">신상품</p>
		</div>
	</div><br><br>
	<div id="line" style="width: 1024px; border: 2px solid lightgray;"></div><br><br>
	<span style="font-size: 15px;">TOP</span>
	<a href="<%=request.getContextPath()%>/product/tops?categoryNo=1">
	<span class="moreView">> more</span>
	</a>
	<br><br>
	<div id="topwrap">
	
	</div>
	<br /><br />
	<span style="font-size: 15px;">BOTTOM</span>
	<a href="<%=request.getContextPath()%>/product/tops?categoryNo=2">
	<span class="moreView">> more</span>
	</a>
	<br><br>
	<div id="bottomwrap">
	
	</div>
	<br /><br />
	<span style="font-size: 15px;">SHOES</span>
	<a href="<%=request.getContextPath()%>/product/tops?categoryNo=3">
	<span class="moreView">> more</span>
	</a>
	<br><br>
	<div id="shoeswrap">
	
	</div>
	
</section>
<br />
<br />
<br />
<br />
<script>
		const newProduct = () => {
			$.ajax({
				url : '<%=request.getContextPath()%>/product/newProdcut',
				dataType: 'json',
				success(response){
					console.log("response@newProduct = " + response);
					const div = document.querySelector("#newItem");
					
					response.forEach((product) => {
						const {productRenamedFilename} = product.attachmentList[0];
						const {productCode, productName, productPrice} = product;
						
						const img = `
							<a href="<%= request.getContextPath()%>/product/detail?product_name=\${productName}">					
							<img src="<%= request.getContextPath()%>/upload/admin/\${productRenamedFilename}" alt="" /></a>
						`;
						div.insertAdjacentHTML('beforeend', img);
					})
				},
				error: console.log
			});
		};
		newProduct();
		
		 // Slide every slideDelay seconds
        const slideDelay = 3000;
  
        const dynamicSlider = document.getElementById("slider");
  
        var curSlide = 0;
        window.setInterval(()=>{
          curSlide++;
          if (curSlide === dynamicSlider.childElementCount) {
            curSlide = 0;
          }
  
          // Actual slide
          dynamicSlider.firstElementChild.style.setProperty("margin-left", "-" + curSlide + "00%");
        }, slideDelay);
        
        const topItem = () => {
        	$.ajax({
        		url: '<%=request.getContextPath()%>/product/mainTopItem',
        		dataType: 'json',
        		success(response){
					console.log("response@newTopItem = " + response);
					const div = document.querySelector("#topwrap");
					
					response.forEach((product) => {
						const {productRenamedFilename} = product.attachmentList[0];
						const {productCode, productName, productPrice} = product;
						
						const img = `
							<div style="border: 1px solid #9d9d9dd1;" class="topItem">
							<a href="<%= request.getContextPath()%>/product/detail?product_name=\${productName}">					
							<img style="height: 70%;" src="<%= request.getContextPath()%>/upload/admin/\${productRenamedFilename}" alt="" /></a><br /><br />
							<span style="font-size: 13px; top:-15px; position: relative;">\${productName}</span><br /><br />
							<span style="font-size: 13px; top:-24px; position: relative;">\${productPrice} won</span>
						</div>
						`;
						div.insertAdjacentHTML('beforeend', img);
					})
        		},
        		error: console.log,
        		complete() {
        			$(document).ready(function(){
        	            $('#topwrap').slick({
        	                infinite: true,
        	                slidesToShow: 4,
        	                prevArrow: "<button type='button' class='slick-next'>></button>",
        	                nextArrow: "<button type='button' class='slick-prev'><</button>",
        	                autoplay: true,
        	                autoplaySpeed: 1500
        	            });
        	        });
        			
        		}
        	});
        };
      	
        topItem();

        const bottomItem = () => {
        	$.ajax({
        		url: '<%=request.getContextPath()%>/product/mainBottomItem',
        		dataType: 'json',
        		success(response){
					const div = document.querySelector("#bottomwrap");
					
					response.forEach((product) => {
						const {productRenamedFilename} = product.attachmentList[0];
						const {productCode, productName, productPrice} = product;
						
						const img = `
							<div style="border: 1px solid #9d9d9dd1;" class="bottomItem">
							<a href="<%= request.getContextPath()%>/product/detail?product_name=\${productName}">					
							<img style="height: 70%;" src="<%= request.getContextPath()%>/upload/admin/\${productRenamedFilename}" alt="" /></a><br /><br />
							<span style="font-size: 13px; top:-15px; position: relative;">\${productName}</span><br /><br />
							<span style="font-size: 13px; top:-24px; position: relative;">\${productPrice} won</span>
						</div>
						`;
						div.insertAdjacentHTML('beforeend', img);
					})
        		},
        		error: console.log,
        		complete() {
        			$(document).ready(function(){
        	            $('#bottomwrap').slick({
        	                infinite: true,
        	                slidesToShow: 4,
        	                prevArrow: "<button type='button' class='slick-next'>></button>",
        	                nextArrow: "<button type='button' class='slick-prev'><</button>",
        	                autoplay: true,
        	                autoplaySpeed: 2000
        	            });
        	        });
        			
        		}
        	});
        };
        bottomItem();

        const shoesItem = () => {
        	$.ajax({
        		url: '<%=request.getContextPath()%>/product/mainshoesItem',
        		dataType: 'json',
        		success(response){
					const div = document.querySelector("#shoeswrap");
					
					response.forEach((product) => {
						const {productRenamedFilename} = product.attachmentList[0];
						const {productCode, productName, productPrice} = product;
						
						const img = `
							<div style="border: 1px solid #9d9d9dd1;" class="shoesItem">
							<a href="<%= request.getContextPath()%>/product/detail?product_name=\${productName}">					
							<img style="height: 70%;" src="<%= request.getContextPath()%>/upload/admin/\${productRenamedFilename}" alt="" /></a><br /><br />
							<span style="font-size: 13px; top:-15px; position: relative;">\${productName}</span><br /><br />
							<span style="font-size: 13px; top:-24px; position: relative;">\${productPrice} won</span>
						</div>
						`;
						div.insertAdjacentHTML('beforeend', img);
					})
        		},
        		error: console.log,
        		complete() {
        			$(document).ready(function(){
        	            $('#shoeswrap').slick({
        	                infinite: true,
        	                slidesToShow: 4,
        	                prevArrow: "<button type='button' class='slick-next'>></button>",
        	                nextArrow: "<button type='button' class='slick-prev'><</button>",
        	                autoplay: true,
        	                autoplaySpeed: 2000
        	            });
        	        });
        			
        		}
        	});
        };
        shoesItem();
	</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>