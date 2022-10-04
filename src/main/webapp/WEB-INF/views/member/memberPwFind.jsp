<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pwfind.css" />    
<main>
	<div class="pwFind">
        <h1>비밀번호 찾기</h1>
            <form name="pwFindFrm">
                <div class="inputInfo">
                    <input type="text" name="memberId" id="memberId" placeholder="ID" required/>
                    <input type="text" oninput="autoHyphen(this)" name="memberPhone" id="memberPhone" placeholder="휴대폰번호 (- 제외)" required maxlength="13"/>
                </div>
                <button class="findPwBtn">임시번호 발급</button>
                <div class="newPw">
                </div>
            </form>
    </div>
</main>
<script>	
		
	const autoHyphen = (target) => {
		target.value = target.value.replace(/[^0-9]/g, '')
		   .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
	}
	
	
	
	document.pwFindFrm.addEventListener('submit', (e) => {
		e.preventDefault();
		let ranValue1 = ['1','2','3','4','5','6','7','8','9','0'];
		let ranValue2 = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
		let ranValue3 = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
		let ranValue4 = ['!','@','#','$','%','^','&','*','(',')'];
		let newPwd = "";
		
		for(i=0 ; i<2; i++) {
			let ranPick1 = Math.floor(Math.random() * ranValue1.length);
			let ranPick2 = Math.floor(Math.random() * ranValue2.length);
			let ranPick3 = Math.floor(Math.random() * ranValue3.length);
			let ranPick4 = Math.floor(Math.random() * ranValue4.length);
			newPwd = newPwd + ranValue1[ranPick1] + ranValue2[ranPick2] + ranValue3[ranPick3] + ranValue4[ranPick4];
		}
		
		const memberId = document.querySelector("#memberId");
		if(!/^.{4,}$/.test(memberId.value)){
			alert("유효한 아이디를 입력해주세요.");
			memberId.select();
			return false;
		}
		
		const phone = document.querySelector("#memberPhone");
		if(!/^01[0-9]{1}-[0-9]{3,4}-[0-9]{4}$/.test(phone.value)){
			alert("유효한 전화번호를 입력해주세요");
			phone.select();
			return false;
		}
			
		
	
		$.ajax({
			url : '<%= request.getContextPath()%>/member/memberPwFind',
			method : "POST",
			dataType : 'json',
			data : {
				memberId : e.target.memberId.value,
				memberPhone : e.target.memberPhone.value,
				newPwd : newPwd
			},
			success(memberPw) {
				console.log("memberPw", memberPw);
				
				const div = document.querySelector(".newPw");
				const btn = `
				<span class="loginBtn">
					<a href="<%= request.getContextPath()%>/member/memberLogin">LOGIN</a>
				</span>
				`
				if(memberPw !== null){
					const html =`<span>발급된 비밀번호 : </span>
					<input type="text" value="" name="tel" readonly/>
					`;
					
					$('.newPw').append(html);
					$('input[name=tel]').attr('value', newPwd);
					div.insertAdjacentHTML('beforeend', btn);
				}
				else{
					alert("잘못입력하셨습니다");
				}
			
			}, 
			error : console.log
				
		});
	}, {once:true});
	
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>