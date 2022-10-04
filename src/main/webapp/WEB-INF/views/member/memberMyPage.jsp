<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/member.css" />
<%
	String memberId = loginMember.getMemberId();
	String memberName = loginMember.getMemberName();
	String email = loginMember.getEmail();
	String phone = loginMember.getPhone();
	String address = loginMember.getAddress();
	String addressEx = loginMember.getAddressEx();
	
	String[] phones = phone.split("-");

	List<String> phoneList = null;
	if(phone != null){
	phoneList = Arrays.asList(phones);
	}

%>
<style>

</style>
<!-- 정보수정 -->
<div id="myPage-container">
	<h2>마이페이지</h2>
	<img src="/icodi/upload/codiboard/defaultProfile.png" id="memberProfileImg">
	<span id="memberProfileId"><%= memberId %></span>
	<input type="button" value="정보수정" id="btn-modal-memberUpdate" />
</div>
<div id="modal" class="modal-overlay">
	<div class="modal-window">
		<div class="title">
			<h4>정보 수정</h4>
		</div>
		<div class="close-area">X</div>
		<div class="content">
		<form name="memberUpdateFrm"
			  action="<%= request.getContextPath() %>/member/memberUpdate"
			  method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td><input style="outline: none; border: none; font-size: 15px;" type="text" name="memberId" id="memberId" value="<%= memberId %>" readonly/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="button" value="비밀번호 변경" id="btn-modal-passwordUpdate" 
						onclick="updatePassword();">
					</td>
				</tr>
    			<tr>
    				<td>이름</td>
					<td><input style="outline: none; border: none; font-size: 15px;" type="text" name="memberName" id="memberName" value="<%= memberName %>" readonly/></td>
				</tr>
    			<tr>
    				<td>이메일</td>
					<td>
						<input type="email" name="email" id="email" value="<%= email %>" required><br>
						<span id="msgEmail"></span>
					</td>
				</tr>
    			<tr>
    				<td>휴대폰</td>
      				<td>
						<select name="phone" id="phone1">
							<option value="010" <%= phoneChecked(phoneList, "010") %>>010</option>
							<option value="011" <%= phoneChecked(phoneList, "011") %>>011</option>
							<option value="016" <%= phoneChecked(phoneList, "016") %>>016</option>
							<option value="017" <%= phoneChecked(phoneList, "017") %>>017</option>
							<option value="018" <%= phoneChecked(phoneList, "018") %>>018</option>
							<option value="019" <%= phoneChecked(phoneList, "019") %>>019</option>
						</select>-
						<input type="text" name="phone" id="phone2" maxlength="4" value="<%= phones[1] %>" required>-
						<input type="text" name="phone" id="phone3" maxlength="4" value="<%= phones[2].replace(" ", "") %>" required><br>
						<span id="msgPhone"></span>
					</td>
				</tr>
  				<tr>
  					<td>주소</td>
					<td>
						<input type="text" name="address" id="address" value="<%= address %>" required>
						<input type="button" id="researchButton" value="주소 찾기🔎"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="text" name="addressEx" id="addressEx" value="<%= addressEx %>" required>
					</td>
				</tr>
			</table>
			<hr />
			<input type="submit" id="btn-save" value="저장">
			<input type="button" id="btn-delete" onclick="deleteMember();" value="탈퇴"/>
			</form>
		</div>
	</div>
</div>
<!-- 회원탈퇴폼 : POST /member/memberDelete 전송을 위해 시각화되지 않는 폼태그 이용 -->
<form name="memberDelFrm" action="<%= request.getContextPath() %>/member/memberDelete" method="POST">
	<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
</form>


<!-- 내 코디 확인 -->
<div id="myFrm-container">
	<form class="myFrm" name="myCodiFrm" action="<%= request.getContextPath() %>/member/memberMyCodiList?memberId=<%= loginMember.getMemberId() %>">
		<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
	</form>
	<div id="my-codi-selected" onclick="myCodi();">내 코디</div>
	<form class="myFrm" name="myBoardFrm" action="<%= request.getContextPath() %>/member/memberMyBoardList?memberId=<%= loginMember.getMemberId() %>">
		<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
	</form>
	<div id="my-board-selected" onclick="myBoard();">내 게시글</div>
</div>




<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
/** 모달창 띄우기 */
const modal = document.getElementById("modal");
function modalOn() {
    modal.style.display = "flex";
   	$('body').css("overflow", "hidden");
}

function isModalOn() {
    return modal.style.display === "flex"
}

function modalOff() {
    modal.style.display = "none"
   	$('body').css("overflow-y", "scroll");
}

const btnModal = document.getElementById("btn-modal-memberUpdate")
btnModal.addEventListener("click", e => {
    modalOn()
});
   
const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    modalOff()
});

modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal-overlay")) {
        modalOff()
    }
});

window.addEventListener("keyup", e => {
    if(isModalOn() && e.key === "Escape") {
        modalOff()
    }
});

/** 주소 찾기 */
document.querySelector("#address").addEventListener('click', function(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            document.querySelector("#address").value = data.address;
			document.querySelector("#addressEx").focus();
        }
    }).open();
});	
document.querySelector("#researchButton").addEventListener('click', function(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            document.querySelector("#address").value = data.address;
			document.querySelector("#addressEx").focus();
        }
    }).open();
});
   
const updatePassword = () => {
	window.open('<%= request.getContextPath() %>/member/memberPasswordUpdate', 'popup', 'width=500px, height=400px, top=50; left=100; location=no, status=no, scrollbars=no');
};

/**
 * POST /member/memberDelete
 * memberDelFrm 제출
 */
const deleteMember = () => {
	if(confirm("정말로 탈퇴하시겠습니까?"))
		document.memberDelFrm.submit();
};   
/**
 * 내 코디/게시글 확인
 */
const myBoard = () => {
	document.myBoardFrm.submit();
};

const myCodi = () => {
	document.myCodiFrm.submit();
};
/**
 * 폼 유효성 검사
 */
const email = document.querySelector("#email");
email.addEventListener('blur', (e) => {
	if(!/^([\w\.-]+)@([\w-]+)(\.[\w-]+){1,2}$/.test(email.value)) {
		msgEmail.innerHTML = '❌ 이메일 형식이 유효하지 않습니다';
		msgEmail.style.color = 'red';
		email.select();
		return false;
	}
	else {
		msgEmail.innerHTML = '';
		msgEmail.style.color = '';
	}
});

const phone2 = document.querySelector("#phone2");
phone2.addEventListener('blur', (e) => {
	if(!/[0-9]{3,4}/.test(phone2.value)) {
		msgPhone.innerHTML = '❌ 휴대폰 번호를 다시 확인해 주세요';
		msgPhone.style.color = 'red';
		phone2.select();
		return false;
	}
	else {
		msgPhone.innerHTML = '';
		msgPhone.style.color = '';
	}
});

const phone3 = document.querySelector("#phone3");
phone3.addEventListener('blur', (e) => {
	if(!/[0-9]{4,}/.test(phone3.value)) {
		msgPhone.innerHTML = '❌ 휴대폰 번호를 다시 확인해 주세요';
		msgPhone.style.color = 'red';
		phone3.select();
		return false;
	}
	else {
		msgPhone.innerHTML = '';
		msgPhone.style.color = '';
	}

});

document.memberUpdateFrm.onsubmit = (e) => {
	if(!email.value || !phone2.value || !phone3.value || !address.value || !addressEx.value) {
		return false;
	}
};


    
</script>
<%!
public String phoneChecked(List<String> phoneList, String phone){
	return phoneList != null && phoneList.contains(phone) ? "selected" : "";
}
%>