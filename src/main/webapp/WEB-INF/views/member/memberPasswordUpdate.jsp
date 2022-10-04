<%@page import="com.kh.icodi.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	
	String saveId = null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null)
		for(Cookie c : cookies){
			String name = c.getName();
			String value = c.getValue();
			System.out.println("[cookie] " + name + " = " + value);
			if("saveId".equals(name)){
				saveId = value;
			}
		}


%>
    <!doctype html>
    <html lang="en">
    <head>
    	<meta charset="UTF-8" />
    	<title>password update</title>
    	<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
    <script>
window.onload = () => {
	<% if(msg != null) { %>	
		alert("<%= msg %>");
	<% } %>
}
</script>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/member.css" />
    </head>
    <body id="pwd-body">
<section id=enroll-container>
		<div id="enroll-title"><h2>비밀번호 변경</h2></div>
		<form 
			name="passwordUpdateFrm" 
			action="<%=request.getContextPath()%>/member/memberPasswordUpdate" 
			method="post" >
			<table id="tbl-pwd">
				<tr>
					<th class="table-pwd-th">현재 비밀번호</th>
					<td><input type="password" name="oldPassword" id="oldPassword" required></td>
				</tr>
				<tr>
					<td colspan="2"><span id="msg1"></span></td>
				</tr>
				<tr>
					<th class="table-pwd-th">변경할 비밀번호</th>
					<td>
						<input type="password" name="newPassword" id="newPassword" required>
					</td>
				</tr>
				<tr>
					<td colspan="2"><span id="msg2"></span></td>
				</tr>
				<tr>
					<th class="table-pwd-th">비밀번호 확인</th>
					<td>	
						<input type="password" id="newPasswordCheck" required><br>
					</td>
				</tr>
				<tr>
					<td colspan="2"><span id="msg3"></span></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input id="btnPasswordUpdate" type="submit"  value="변경" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
		</form>
	</section>
	<script>
	/**
	 * 비밀번호 일치여부 검사
	 */
	 
	const oldPassword = document.querySelector("#oldPassword");
	const newPassword = document.querySelector("#newPassword");
	const newPasswordCheck = document.querySelector("#newPasswordCheck");
	
	oldPassword.addEventListener('blur', (e) => {
	const re = /^(?=.*\d{1,})(?=.*[!@#$%^&*]{1,})(?=.*[a-zA-Z]{1,}).{8,12}$/;
		if(!re.test(oldPassword.value)){
			msg1.innerHTML = "❌ 영문+숫자+특수문자(!@#$%^&*) 포함 8자리 이상";
			msg1.style.color = 'red';
			oldPassword.select();
			return false;
		}
		else {
			msg1.innerHTML = '';
		}
	});
	
	newPassword.addEventListener('blur', (e) => {
		const re = /^(?=.*\d{1,})(?=.*[!@#$%^&*]{1,})(?=.*[a-zA-Z]{1,}).{8,12}$/;
		if(!re.test(newPassword.value)){
			msg2.innerHTML = "❌ 영문+숫자+특수문자(!@#$%^&*) 포함 8자리 이상";
			msg2.style.color = 'red';
			newPassword.select();
			return false;
		}
		else {
			msg2.innerHTML = '';
		}
	});
	
	newPasswordCheck.addEventListener('blur', (e) => {
		if(newPassword.value !== newPasswordCheck.value) {
			msg3.innerHTML = '❌ 비밀번호가 일치하지 않습니다';
			msg3.style.color = 'red';
			newPasswordCheck.value = '';
			newPassword.select();
		}
		else {
			msg3.innerHTML = '';
		}
	});
	
	
	
		
	document.passwordUpdateFrm.onsubmit = (e) => {
		e.preventDefault();
		
		if(newPassword.value !== newPasswordCheck.value) {
			return false;
		}
	
		
		$.ajax({
			url: '<%= request.getContextPath() %>/member/memberPasswordUpdate',
			dataType: 'json',
			method: 'post',
			data : {
				oldPassword : oldPassword.value,
				memberId : "<%= loginMember.getMemberId() %>",
				newPassword : newPassword.value
			},
			success(response) {
				console.log(response, typeof response);
				const re = response['result'];
				console.log(re, typeof re);
				if(re === 1) {
					opener.parent.location='<%= request.getContextPath() %>/member/memberMyCodiList';
					window.close();
				}
				else {
					window.location='<%= request.getContextPath() %>/member/memberPasswordUpdate';
					location.reload();
				}

				
			},
			error : console.log
			
		});
		
	};
	
	
	
	</script>