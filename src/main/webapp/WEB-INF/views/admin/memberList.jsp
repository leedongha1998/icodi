<%@page import="com.kh.icodi.member.model.dto.MemberRole"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.icodi.member.model.dto.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberList.css">
<%

	List<Member> list = (List<Member>) request.getAttribute("list");
	String type = request.getParameter("searchType");
	String kw = request.getParameter("searchKeyword");
%>
<style>
div#search-memberId {
	display: <%= type == null || "member_id".equals(type) ? "inline-block" : "none"%>;
}
div#search-memberName {
	display: <%= "member_name".equals(type) ? "inline-block" : "none"%>;
}
div#search-memberRole {
	display: <%= "member_role".equals(type) ? "inline-block" : "none"%>;
}
</style>
<script>
window.addEventListener('load', (e) => {
	document.querySelector("select#searchType").onchange = (e) => {
		document.querySelectorAll(".search-type").forEach((div, index) => {
			div.style.display = "none";
		});
		let id;
		switch(e.target.value){
		case "member_id" : id = "memberId"; break;
		case "member_name" : id = "memberName"; break;
		case "member_role" : id = "memberRole"; break;
		}
		document.querySelector(`#search-\${id}`).style.display = "inline-block";
	}
});
</script>
<section id="memberList-container">
	<h2>회원 검색</h2>
	
	<div id="search-container">
        <label for="searchType">검색타입 :</label> 
        <select id="searchType">
            <option value="member_id" <%= "member_id".equals(type) ? "selected" : "" %>>아이디</option>        
            <option value="member_name" <%= "member_name".equals(type) ? "selected" : "" %>>회원명</option>
            <option value="member_role" <%= "member_role".equals(type) ? "selected" : "" %>>회원권한</option>
        </select>
        <div id="search-memberId" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="member_id"/>
                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요."
                	value="<%= "member_id".equals(type) ? kw : "" %>"/>
                <button type="submit">검색</button>            
            </form>    
        </div>
        <div id="search-memberName" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="member_name"/>
                <input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요."
                	value="<%= "member_name".equals(type) ? kw : "" %>"/>
                <button type="submit">검색</button>            
            </form>    
        </div>
        <div id="search-memberRole" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="member_role"/>
                <input type="radio" name="searchKeyword" value="U" <%= "member_role".equals(type) && "U".equals(kw) ? "checked" : "" %>><span class="role">일반</span>
                <input type="radio" name="searchKeyword" value="A" <%= "member_role".equals(type) && "A".equals(kw) ? "checked" : "" %>><span class="role">관리자</span>
                <button type="submit">검색</button>
            </form>
        </div>
    </div>
	
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>상세주소</th>
				<th>적립금</th><%-- 세자리컴마 DecimalFormat --%>
				<th>가입일</th><%-- 날짜형식 yyyy-MM-dd  --%>
				<th>회원권한</th><%-- select태그로 처리 --%>
			</tr>
		</thead>
		<tbody>
		<%
			if(list == null || list.isEmpty()){
		%>
			<tr>
				<td colspan="9" align="center"> 검색 결과가 없습니다. </td>
			</tr>
		<%
			} 
			else {
				for(Member m : list){
		%>
			<tr>
				<td><%= m.getMemberId() %></td>
				<td><%= m.getMemberName() %></td>
				<td><%= m.getEmail() %></td>
				<td><%= m.getPhone() %></td>
				<td><%= m.getAddress() %></td>
				<td><%= m.getAddressEx() %></td>
				<td><%= new DecimalFormat("#,###").format(m.getPoint()) %></td>
				<td><%= new SimpleDateFormat("yyyy-MM-dd").format(m.getEnrollDate()) %></td>
				<td>
					<select class="member-role" data-member-id="<%= m.getMemberId() %>">
						<option value="A" <%= MemberRole.A == m.getMemberRole() ? "selected" : "" %>>관리자</option>
						<option value="U" <%= MemberRole.U == m.getMemberRole() ? "selected" : "" %>>일반</option>
					</select>	
				</td>
			</tr>		
		<%		} 
			}
		%>
			</tbody>

	</table>
	
	<div id="pagebar">
		<%= request.getAttribute("pagebar") %>
	</div>
</section>
<form action="<%= request.getContextPath() %>/admin/memberRoleUpdate" 
	method="POST"
	name="memberRoleUpdateFrm">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="memberRole" />
</form>
<script>
document.querySelectorAll(".member-role").forEach((select, index) => {
	select.onchange = (e) => {
		console.log(e.target.dataset.memberId, e.target.value);
		
		if(confirm(`해당 회원의 권한을 \${e.target.value}로 변경하시겠습니까?`)){
			const frm = document.memberRoleUpdateFrm;
			frm.memberId.value = e.target.dataset.memberId;
			frm.memberRole.value = e.target.value;
			frm.submit();
		}
		else {
			// 원상복구코드
			e.target.querySelector("[selected]").selected = true;
		}
	};
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>