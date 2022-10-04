<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/member.css?dfjdf" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<section id="enroll-container">
	<h2 id="main-msg">JOIN US</h2>
	<div id="form-container">
		<form name="memberEnrollFrm" method="POST">
			<table id="enroll-table">
				<tr>
					<th>아이디<sup>*</sup></th>
					<td>
						<input style="font-size: 13px;" type="text" placeholder="아이디를 입력해주세요" name="memberId" id="memberId" maxlength="10" oninput="checkId()" required />
						<div><span id="msgMemberId" class="msg">❣️ 아이디는 영소문자/숫자 포함 6자리 이상이여야 합니다</span></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호<sup>*</sup></th>
					<td>
						<input style="font-size: 13px;" type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요" maxlength="12" required />
						<div><span id="msgPassword" class="msg">❣️ 비밀번호는 영문+숫자+특수문자(!@#$%^&*) 포함 8자리 이상이여야 합니다</span></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인<sup>*</sup></th>
					<td>
						<input style="font-size: 13px;" type="password" id="passwordCheck" placeholder="비밀번호를 한 번 더 입력해주세요" maxlength="12" required />
						<div><span id="msgPasswordCheck" class="msg"></span></div>
					</td>
				</tr>
				<tr>
					<th>이름<sup>*</sup></th>
					<td>
						<input style="font-size: 13px;" type="text" name="memberName" id="memberName" placeholder="이름을 입력해주세요" maxlength="20" required />
						<div><span id="msgMemberName" class="msg"></span></div>
					</td>
				</tr>
				<tr>
					<th>이메일<sup>*</sup></th>
					<td>
						<input style="font-size: 13px;" type="email" name="email" id="email" placeholder="이메일을 입력해주세요" required />
						<div><span id="msgEmail" class="msg"></span></div>
					</td>
				</tr>
				<tr>
					<th>휴대폰<sup>*</sup></th>
					<td>
						<select name="phone" id="phone1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
						</select>-
						<input style="font-size: 13px;" type="text" name="phone" id="phone2" maxlength="4" required/>-
						<input style="font-size: 13px;" type="text" name="phone" id="phone3" maxlength="4" required/>
						<div><span id="msgPhone" class="msg"></span></div>
					</td>
				</tr>
				<tr>
					<th>주소<sup>*</sup></th>
					<td>
						<input style="font-size: 13px;" type="text" id="address" name="address" required/>
						<input type="button" id="researchButton" value="주소 찾기🔎"/>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<input style="font-size: 13px;" type="text" id="addressEx" name="addressEx" required/>
						<div><span id="msgAddress" class="msg"></span></div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr style="border: 0; background-color: gray; height: 1px;" />
					<input type="checkbox" id="all-checkbox" style="width: 13px; height: 13px; position: relative; top: 3px; accent-color: rgb(22, 160, 133); margin-bottom: 5px;"/>
					<label style="font-size: 13px;" for="all-checkbox">전체 동의</label><br />
					<span style="font-size: 11px; color: #a3a3a3;">고객님의 편의를 위하여 전체동의 기능을 제공하고 있습니다. 전체동의 하시겠습니까?</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr style="border: 0; background-color: gray; height: 1px;"/>
					<input type="checkbox" id="checkbox1" name="checkbox" style="width: 13px; height: 13px; position: relative; top: 3px; accent-color: rgb(22, 160, 133);" required/>
					<label style="font-size: 13px;" for="checkbox1">(필수) 이용약관에 동의합니다.</label>
					<button type="button" id="checkbox1-overview"style="font-size: 12px; font-weight: bold; -webkit-transform: scale(2,1); float: right; background-color: #fff; border: none; margin-right: 5px; cursor: pointer;">∨</button>
					<div id="toggle1" style="overflow: scroll; width: 557px; height: 150px; font-size: 13px; display: none; margin-top: 7px; margin-right: 8px; padding: 0 7px;">
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">이 약관은 (주)kh(전자상거래 사업자)가 운영하는 kh온라인몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리.의무 및 책임사항을 규정함을 목적으로 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제1조(목적)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">※「PC통신, 무선 등을 이용하는 전자상거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다.」</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제2조(정의)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”이란 kh(주)가 재화 또는 용역(이하 “재화 등”이라 함)을 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 등을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버몰을 운영하는 사업자의 의미로도 사용합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “이용자”란 “몰”에 접속하여 이 약관에 따라 “몰”이 제공하는 서비스를 받는 회원 및 비회원을 말합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ ‘회원’이라 함은 “몰”에 회원등록을 한 자로서, 계속적으로 “몰”이 제공하는 서비스를 이용할 수 있는 자를 말합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">④ ‘비회원’이라 함은 회원에 가입하지 않고 “몰”이 제공하는 서비스를 이용하는 자를 말합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제3조 (약관 등의 명시와 설명 및 개정)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 이 약관의 내용과 상호 및 대표자 성명, 영업소 소재지 주소(소비자의 불만을 처리할 수 있는 곳의 주소를 포함), 전화번호.모사전송번호.전자우편주소, 사업자등록번호, 통신판매업 신고번호, 개인정보보호책임자등을 이용자가 쉽게 알 수 있도록 kh몰의 초기 서비스화면(전면)에 게시합니다. 다만, 약관의 내용은 이용자가 연결화면을 통하여 볼 수 있도록 할 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰은 이용자가 약관에 동의하기에 앞서 약관에 정하여져 있는 내용 중 청약철회.배송책임.환불조건 등과 같은 중요한 내용을 이용자가 이해할 수 있도록 별도의 연결화면 또는 팝업화면 등을 제공하여 이용자의 확인을 구하여야 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ “몰”은 「전자상거래 등에서의 소비자보호에 관한 법률」, 「약관의 규제에 관한 법률」, 「전자문서 및 전자거래기본법」, 「전자금융거래법」, 「전자서명법」, 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」, 「방문판매 등에 관한 법률」, 「소비자기본법」 등 관련 법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">④ “몰”이 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 몰의 초기화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다. 다만, 이용자에게 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 “몰“은 개정 전 내용과 개정 후 내용을 명확하게 비교하여 이용자가 알기 쉽도록 표시합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">⑤ “몰”이 약관을 개정할 경우에는 그 개정약관은 그 적용일자 이후에 체결되는 계약에만 적용되고 그 이전에 이미 체결된 계약에 대해서는 개정 전의 약관조항이 그대로 적용됩니다. 다만 이미 계약을 체결한 이용자가 개정약관 조항의 적용을 받기를 원하는 뜻을 제3항에 의한 개정약관의 공지기간 내에 “몰”에 송신하여 “몰”의 동의를 받은 경우에는 개정약관 조항이 적용됩니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">⑥ 이 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 전자상거래 등에서의 소비자보호에 관한 법률, 약관의 규제 등에 관한 법률, 공정거래위원회가 정하는 전자상거래 등에서의 소비자 보호지침 및 관계법령 또는 상관례에 따릅니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제4조(서비스의 제공 및 변경)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 다음과 같은 업무를 수행합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 1. 재화 또는 용역에 대한 정보 제공 및 구매계약의 체결</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 2. 구매계약이 체결된 재화 또는 용역의 배송</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 3. 기타 “몰”이 정하는 업무</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”은 재화 또는 용역의 품절 또는 기술적 사양의 변경 등의 경우에는 장차 체결되는 계약에 의해 제공할 재화 또는 용역의 내용을 변경할 수 있습니다. 이 경우에는 변경된 재화 또는 용역의 내용 및 제공일자를 명시하여 현재의 재화 또는 용역의 내용을 게시한 곳에 즉시 공지합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ “몰”이 제공하기로 이용자와 계약을 체결한 서비스의 내용을 재화등의 품절 또는 기술적 사양의 변경 등의 사유로 변경할 경우에는 그 사유를 이용자에게 통지 가능한 주소로 즉시 통지합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">④ 전항의 경우 “몰”은 이로 인하여 이용자가 입은 손해를 배상합니다. 다만, “몰”이 고의 또는 과실이 없음을 입증하는 경우에는 그러하지 아니합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제5조(서비스의 중단)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 컴퓨터 등 정보통신설비의 보수점검.교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”은 제1항의 사유로 서비스의 제공이 일시적으로 중단됨으로 인하여 이용자 또는 제3자가 입은 손해에 대하여 배상합니다. 단, “몰”이 고의 또는 과실이 없음을 입증하는 경우에는 그러하지 아니합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ 사업종목의 전환, 사업의 포기, 업체 간의 통합 등의 이유로 서비스를 제공할 수 없게 되는 경우에는 “몰”은 제8조에 정한 방법으로 이용자에게 통지하고 당초 “몰”에서 제시한 조건에 따라 소비자에게 보상합니다. 다만, “몰”이 보상기준 등을 고지하지 아니한 경우에는 이용자들의 마일리지 또는 적립금 등을 “몰”에서 통용되는 통화가치에 상응하는 현물 또는 현금으로 이용자에게 지급합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제6조(회원가입)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① 이용자는 “몰”이 정한 가입 양식에 따라 회원정보를 기입한 후 이 약관에 동의한다는 의사표시를 함으로서 회원가입을 신청합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”은 제1항과 같이 회원으로 가입할 것을 신청한 이용자 중 다음 각 호에 해당하지 않는 한 회원으로 등록합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 1. 가입신청자가 이 약관 제7조제3항에 의하여 이전에 회원자격을 상실한 적이 있는 경우, 다만 제7조제3항에 의한 회원자격 상실 후 3년이 경과한 자로서 “몰”의 회원재가입 승낙을 얻은 경우에는 예외로 한다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 2. 등록 내용에 허위, 기재누락, 오기가 있는 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 3. 기타 회원으로 등록하는 것이 “몰”의 기술상 현저히 지장이 있다고 판단되는 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ 회원가입계약의 성립 시기는 “몰”의 승낙이 회원에게 도달한 시점으로 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">④ 회원은 회원가입 시 등록한 사항에 변경이 있는 경우, 상당한 기간 이내에 “몰”에 대하여 회원정보 수정 등의 방법으로 그 변경사항을 알려야 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제7조(회원 탈퇴 및 자격 상실 등)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① 회원은 “몰”에 언제든지 탈퇴를 요청할 수 있으며 “몰”은 즉시 회원탈퇴를 처리합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② 회원이 다음 각 호의 사유에 해당하는 경우, “몰”은 회원자격을 제한 및 정지시킬 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 1. 가입 신청 시에 허위 내용을 등록한 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 2. “몰”을 이용하여 구입한 재화 등의 대금, 기타 “몰”이용에 관련하여 회원이 부담하는 채무를 기일에 지급하지 않는 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 3. 다른 사람의 “몰” 이용을 방해하거나 그 정보를 도용하는 등 전자상거래 질서를 위협하는 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 4. “몰”을 이용하여 법령 또는 이 약관이 금지하거나 공서양속에 반하는 행위를 하는 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ “몰”이 회원 자격을 제한.정지 시킨 후, 동일한 행위가 2회 이상 반복되거나 30일 이내에 그 사유가 시정되지 아니하는 경우 “몰”은 회원자격을 상실시킬 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">④ “몰”이 회원자격을 상실시키는 경우에는 회원등록을 말소합니다. 이 경우 회원에게 이를 통지하고, 회원등록 말소 전에 최소한 30일 이상의 기간을 정하여 소명할 기회를 부여합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제8조(회원에 대한 통지)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”이 회원에 대한 통지를 하는 경우, 회원이 “몰”과 미리 약정하여 지정한 전자우편 주소로 할 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”은 불특정다수 회원에 대한 통지의 경우 1주일이상 “몰” 게시판에 게시함으로서 개별 통지에 갈음할 수 있습니다. 다만, 회원 본인의 거래와 관련하여 중대한 영향을 미치는 사항에 대하여는 개별통지를 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제9조(구매신청)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”이용자는 “몰”상에서 다음 또는 이와 유사한 방법에 의하여 구매를 신청하며, “몰”은 이용자가 구매신청을 함에 있어서 다음의 각 내용을 알기 쉽게 제공하여야 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 1. 재화 등의 검색 및 선택</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 2. 받는 사람의 성명, 주소, 전화번호, 전자우편주소(또는 이동전화번호) 등의 입력</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 3. 약관내용, 청약철회권이 제한되는 서비스, 배송료.설치비 등의 비용부담과 관련한 내용에 대한 확인</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 4. 이 약관에 동의하고 위 3.호의 사항을 확인하거나 거부하는 표시(예, 마우스 클릭)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 5. 재화등의 구매신청 및 이에 관한 확인 또는 “몰”의 확인에 대한 동의</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 6. 결제방법의 선택</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”이 제3자에게 구매자 개인정보를 제공·위탁할 필요가 있는 경우 실제 구매신청 시 구매자의 동의를 받아야 하며, 회원가입 시 미리 포괄적으로 동의를 받지 않습니다. 이 때 “몰”은 제공되는 개인정보 항목, 제공받는 자, 제공받는 자의 개인정보 이용 목적 및 보유·이용 기간 등을 구매자에게 명시하여야 합니다. 다만 「정보통신망이용촉진 및 정보보호 등에 관한 법률」 제25조 제1항에 의한 개인정보 처리위탁의 경우 등 관련 법령에 달리 정함이 있는 경우에는 그에 따릅니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제10조 (계약의 성립)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 제9조와 같은 구매신청에 대하여 다음 각 호에 해당하면 승낙하지 않을 수 있습니다. 다만, 미성년자와 계약을 체결하는 경우에는 법정대리인의 동의를 얻지 못하면 미성년자 본인 또는 법정대리인이 계약을 취소할 수 있다는 내용을 고지하여야 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 1. 신청 내용에 허위, 기재누락, 오기가 있는 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 2. 미성년자가 담배, 주류 등 청소년보호법에서 금지하는 재화 및 용역을 구매하는 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 3. 기타 구매신청에 승낙하는 것이 “몰” 기술상 현저히 지장이 있다고 판단하는 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”의 승낙이 제12조제1항의 수신확인통지형태로 이용자에게 도달한 시점에 계약이 성립한 것으로 봅니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ “몰”의 승낙의 의사표시에는 이용자의 구매 신청에 대한 확인 및 판매가능 여부, 구매신청의 정정 취소 등에 관한 정보 등을 포함하여야 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제11조(지급방법)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">“몰”에서 구매한 재화 또는 용역에 대한 대금지급방법은 다음 각 호의 방법중 가용한 방법으로 할 수 있습니다. 단, “몰”은 이용자의 지급방법에 대하여 재화 등의 대금에 어떠한 명목의 수수료도 추가하여 징수할 수 없습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">1. 폰뱅킹, 인터넷뱅킹, 메일 뱅킹 등의 각종 계좌이체</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">2. 선불카드, 직불카드, 신용카드 등의 각종 카드 결제</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">3. 온라인무통장입금</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">4. 전자화폐에 의한 결제</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">5. 수령 시 대금지급</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">6. 마일리지 등 “몰”이 지급한 포인트에 의한 결제</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">7. “몰”과 계약을 맺었거나 “몰”이 인정한 상품권에 의한 결제</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">8. 기타 전자적 지급 방법에 의한 대금 지급 등</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제12조(수신확인통지.구매신청 변경 및 취소)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 이용자의 구매신청이 있는 경우 이용자에게 수신확인통지를 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② 수신확인통지를 받은 이용자는 의사표시의 불일치 등이 있는 경우에는 수신확인통지를 받은 후 즉시 구매신청 변경 및 취소를 요청할 수 있고 “몰”은 배송 전에 이용자의 요청이 있는 경우에는 지체 없이 그 요청에 따라 처리하여야 합니다. 다만 이미 대금을 지불한 경우에는 제15조의 청약철회 등에 관한 규정에 따릅니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제13조(재화 등의 공급)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 이용자와 재화 등의 공급시기에 관하여 별도의 약정이 없는 이상, 이용자가 청약을 한 날부터 7일 이내에 재화 등을 배송할 수 있도록 주문제작, 포장 등 기타의 필요한 조치를 취합니다. 다만, “몰”이 이미 재화 등의 대금의 전부 또는 일부를 받은 경우에는 대금의 전부 또는 일부를 받은 날부터 3영업일 이내에 조치를 취합니다. 이때 “몰”은 이용자가 재화 등의 공급 절차 및 진행 사항을 확인할 수 있도록 적절한 조치를 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”은 이용자가 구매한 재화에 대해 배송수단, 수단별 배송비용 부담자, 수단별 배송기간 등을 명시합니다. 만약 “몰”이 약정 배송기간을 초과한 경우에는 그로 인한 이용자의 손해를 배상하여야 합니다. 다만 “몰”이 고의.과실이 없음을 입증한 경우에는 그러하지 아니합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제14조(환급)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">“몰”은 이용자가 구매신청한 재화 등이 품절 등의 사유로 인도 또는 제공을 할 수 없을 때에는 지체 없이 그 사유를 이용자에게 통지하고 사전에 재화 등의 대금을 받은 경우에는 대금을 받은 날부터 3영업일 이내에 환급하거나 환급에 필요한 조치를 취합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제15조(청약철회 등)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”과 재화등의 구매에 관한 계약을 체결한 이용자는 「전자상거래 등에서의 소비자보호에 관한 법률」 제13조 제2항에 따른 계약내용에 관한 서면을 받은 날(그 서면을 받은 때보다 재화 등의 공급이 늦게 이루어진 경우에는 재화 등을 공급받거나 재화 등의 공급이 시작된 날을 말합니다)부터 7일 이내에는 청약의 철회를 할 수 있습니다. 다만, 청약철회에 관하여 「전자상거래 등에서의 소비자보호에 관한 법률」에 달리 정함이 있는 경우에는 동 법 규정에 따릅니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② 이용자는 재화 등을 배송 받은 경우 다음 각 호의 1에 해당하는 경우에는 반품 및 교환을 할 수 없습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 1. 이용자에게 책임 있는 사유로 재화 등이 멸실 또는 훼손된 경우(다만, 재화 등의 내용을 확인하기 위하여 포장 등을 훼손한 경우에는 청약철회를 할 수 있습니다)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 2. 이용자의 사용 또는 일부 소비에 의하여 재화 등의 가치가 현저히 감소한 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 3. 시간의 경과에 의하여 재판매가 곤란할 정도로 재화등의 가치가 현저히 감소한 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; 4. 같은 성능을 지닌 재화 등으로 복제가 가능한 경우 그 원본인 재화 등의 포장을 훼손한 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ 제2항제2호 내지 제4호의 경우에 “몰”이 사전에 청약철회 등이 제한되는 사실을 소비자가 쉽게 알 수 있는 곳에 명기하거나 시용상품을 제공하는 등의 조치를 하지 않았다면 이용자의 청약철회 등이 제한되지 않습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">④ 이용자는 제1항 및 제2항의 규정에 불구하고 재화 등의 내용이 표시·광고 내용과 다르거나 계약내용과 다르게 이행된 때에는 당해 재화 등을 공급받은 날부터 3월 이내, 그 사실을 안 날 또는 알 수 있었던 날부터 30일 이내에 청약철회 등을 할 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제16조(청약철회 등의 효과)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 이용자로부터 재화 등을 반환받은 경우 3영업일 이내에 이미 지급받은 재화 등의 대금을 환급합니다. 이 경우 “몰”이 이용자에게 재화등의 환급을 지연한때에는 그 지연기간에 대하여 「전자상거래 등에서의 소비자보호에 관한 법률 시행령」제21조의2에서 정하는 지연이자율(괄호 부분 삭제)을 곱하여 산정한 지연이자를 지급합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”은 위 대금을 환급함에 있어서 이용자가 신용카드 또는 전자화폐 등의 결제수단으로 재화 등의 대금을 지급한 때에는 지체 없이 당해 결제수단을 제공한 사업자로 하여금 재화 등의 대금의 청구를 정지 또는 취소하도록 요청합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ 청약철회 등의 경우 공급받은 재화 등의 반환에 필요한 비용은 이용자가 부담합니다. “몰”은 이용자에게 청약철회 등을 이유로 위약금 또는 손해배상을 청구하지 않습니다. 다만 재화 등의 내용이 표시·광고 내용과 다르거나 계약내용과 다르게 이행되어 청약철회 등을 하는 경우 재화 등의 반환에 필요한 비용은 “몰”이 부담합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">④ 이용자가 재화 등을 제공받을 때 발송비를 부담한 경우에 “몰”은 청약철회 시 그 비용을 누가 부담하는지를 이용자가 알기 쉽도록 명확하게 표시합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제17조(개인정보보호)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 이용자의 개인정보 수집시 서비스제공을 위하여 필요한 범위에서 최소한의 개인정보를 수집합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”은 회원가입시 구매계약이행에 필요한 정보를 미리 수집하지 않습니다. 다만, 관련 법령상 의무이행을 위하여 구매계약 이전에 본인확인이 필요한 경우로서 최소한의 특정 개인정보를 수집하는 경우에는 그러하지 아니합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ “몰”은 이용자의 개인정보를 수집·이용하는 때에는 당해 이용자에게 그 목적을 고지하고 동의를 받습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">④ “몰”은 수집된 개인정보를 목적외의 용도로 이용할 수 없으며, 새로운 이용목적이 발생한 경우 또는 제3자에게 제공하는 경우에는 이용·제공단계에서 당해 이용자에게 그 목적을 고지하고 동의를 받습니다. 다만, 관련 법령에 달리 정함이 있는 경우에는 예외로 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">⑤ “몰”이 제2항과 제3항에 의해 이용자의 동의를 받아야 하는 경우에는 개인정보보호 책임자의 신원(소속, 성명 및 전화번호, 기타 연락처), 정보의 수집목적 및 이용목적, 제3자에 대한 정보제공 관련사항(제공받은자, 제공목적 및 제공할 정보의 내용) 등 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」 제22조제2항이 규정한 사항을 미리 명시하거나 고지해야 하며 이용자는 언제든지 이 동의를 철회할 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">⑥ 이용자는 언제든지 “몰”이 가지고 있는 자신의 개인정보에 대해 열람 및 오류정정을 요구할 수 있으며 “몰”은 이에 대해 지체 없이 필요한 조치를 취할 의무를 집니다. 이용자가 오류의 정정을 요구한 경우에는 “몰”은 그 오류를 정정할 때까지 당해 개인정보를 이용하지 않습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">⑦ “몰”은 개인정보 보호를 위하여 이용자의 개인정보를 처리하는 자를 최소한으로 제한하여야 하며 신용카드, 은행계좌 등을 포함한 이용자의 개인정보의 분실, 도난, 유출, 동의 없는 제3자 제공, 변조 등으로 인한 이용자의 손해에 대하여 모든 책임을 집니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">⑧ “몰” 또는 그로부터 개인정보를 제공받은 제3자는 개인정보의 수집목적 또는 제공받은 목적을 달성한 때에는 당해 개인정보를 지체 없이 파기합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">⑨ “몰”은 개인정보의 수집·이용·제공에 관한 동의란을 미리 선택한 것으로 설정해두지 않습니다. 또한 개인정보의 수집·이용·제공에 관한 이용자의 동의거절시 제한되는 서비스를 구체적으로 명시하고, 필수수집항목이 아닌 개인정보의 수집·이용·제공에 관한 이용자의 동의 거절을 이유로 회원가입 등 서비스 제공을 제한하거나 거절하지 않습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제18조(“몰“의 의무)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 법령과 이 약관이 금지하거나 공서양속에 반하는 행위를 하지 않으며 이 약관이 정하는 바에 따라 지속적이고, 안정적으로 재화.용역을 제공하는데 최선을 다하여야 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”은 이용자가 안전하게 인터넷 서비스를 이용할 수 있도록 이용자의 개인정보(신용정보 포함)보호를 위한 보안 시스템을 갖추어야 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ “몰”이 상품이나 용역에 대하여 「표시.광고의 공정화에 관한 법률」 제3조 소정의 부당한 표시.광고행위를 함으로써 이용자가 손해를 입은 때에는 이를 배상할 책임을 집니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">④ “몰”은 이용자가 원하지 않는 영리목적의 광고성 전자우편을 발송하지 않습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제19조(회원의 ID 및 비밀번호에 대한 의무)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① 제17조의 경우를 제외한 ID와 비밀번호에 관한 관리책임은 회원에게 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② 회원은 자신의 ID 및 비밀번호를 제3자에게 이용하게 해서는 안됩니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ 회원이 자신의 ID 및 비밀번호를 도난당하거나 제3자가 사용하고 있음을 인지한 경우에는 바로 “몰”에 통보하고 “몰”의 안내가 있는 경우에는 그에 따라야 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제20조(이용자의 의무)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">이용자는 다음 행위를 하여서는 안 됩니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">1. 신청 또는 변경시 허위 내용의 등록</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">2. 타인의 정보 도용</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">3. “몰”에 게시된 정보의 변경</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">4. “몰”이 정한 정보 이외의 정보(컴퓨터 프로그램 등) 등의 송신 또는 게시</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">5. “몰” 기타 제3자의 저작권 등 지적재산권에 대한 침해</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">6. “몰” 기타 제3자의 명예를 손상시키거나 업무를 방해하는 행위</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">7. 외설 또는 폭력적인 메시지, 화상, 음성, 기타 공서양속에 반하는 정보를 몰에 공개 또는 게시하는 행위</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제21조(연결“몰”과 피연결“몰” 간의 관계)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① 상위 “몰”과 하위 “몰”이 하이퍼링크(예: 하이퍼링크의 대상에는 문자, 그림 및 동화상 등이 포함됨)방식 등으로 연결된 경우, 전자를 연결 “몰”(웹 사이트)이라고 하고 후자를 피연결 “몰”(웹사이트)이라고 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② 연결“몰”은 피연결“몰”이 독자적으로 제공하는 재화 등에 의하여 이용자와 행하는 거래에 대해서 보증 책임을 지지 않는다는 뜻을 연결“몰”의 초기화면 또는 연결되는 시점의 팝업화면으로 명시한 경우에는 그 거래에 대한 보증 책임을 지지 않습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제22조(저작권의 귀속 및 이용제한)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰“이 작성한 저작물에 대한 저작권 기타 지적재산권은 ”몰“에 귀속합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② 이용자는 “몰”을 이용함으로써 얻은 정보 중 “몰”에게 지적재산권이 귀속된 정보를 “몰”의 사전 승낙 없이 복제, 송신, 출판, 배포, 방송 기타 방법에 의하여 영리목적으로 이용하거나 제3자에게 이용하게 하여서는 안됩니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ “몰”은 약정에 따라 이용자에게 귀속된 저작권을 사용하는 경우 당해 이용자에게 통보하여야 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제23조(분쟁해결)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”은 이용자가 제기하는 정당한 의견이나 불만을 반영하고 그 피해를 보상처리하기 위하여 피해보상처리기구를 설치.운영합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”은 이용자로부터 제출되는 불만사항 및 의견은 우선적으로 그 사항을 처리합니다. 다만, 신속한 처리가 곤란한 경우에는 이용자에게 그 사유와 처리일정을 즉시 통보해 드립니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">③ “몰”과 이용자 간에 발생한 전자상거래 분쟁과 관련하여 이용자의 피해구제신청이 있는 경우에는 공정거래위원회 또는 시·도지사가 의뢰하는 분쟁조정기관의 조정에 따를 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">제24조(재판권 및 준거법)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">① “몰”과 이용자 간에 발생한 전자상거래 분쟁에 관한 소송은 제소 당시의 이용자의 주소에 의하고, 주소가 없는 경우에는 거소를 관할하는 지방법원의 전속관할로 합니다. 다만, 제소 당시 이용자의 주소 또는 거소가 분명하지 않거나 외국 거주자의 경우에는 민사소송법상의 관할법원에 제기합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">② “몰”과 이용자 간에 제기된 전자상거래 소송에는 한국법을 적용합니다.</span></p>
					<p dir="ltr" style="border: 0px; margin-top: 0pt; margin-bottom: 0pt; padding: 0px; list-style: none; line-height: 1.8; color: rgb(0, 0, 0); font-family: UNO, Helvetica, sans-serif; font-size: 11px;">&nbsp;</p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">#카카오톡 알림톡 시행에 관한 내용</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;“(주)kh”는 회원가입, 주문안내, 배송안내 등 비광고성 정보를 카카오톡 알림톡으로 알려드리며, 만약 알림톡 수신이 불가능하거나 수신 차단하신 경우에는 일반 문자메시지로 보내 드립니다. 카카오톡 알림톡을 통해 안내되는 정보를 와이파이가 아닌 이동통신망으로 이용할 경우, 알림톡 수신 중 데이터 요금이 발생할 수 있습니다. 카카오톡을 통해 발송되는 알림톡 수신을 원치 않으실 경우 반드시 알림톡을 차단하여 주시기 바랍니다.</span></p>
					<p dir="ltr" style="border: 0px; margin-top: 0pt; margin-bottom: 0pt; padding: 0px; list-style: none; line-height: 1.8; color: rgb(0, 0, 0); font-family: UNO, Helvetica, sans-serif; font-size: 11px;">&nbsp;</p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">#카카오톡 상담톡 시행에 관한 내용</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;“(주)kh”는 상담업무를 카카오톡 상담톡으로 진행하며, 만약 카카오톡을 통해 안내되는 상담 내용을 Wi-Fi 나 PC가 아닌 이동통신망으로 이용할 경우, 데이터 요금이 발생할 수 있습니다. 카카오톡을 통해 상담을 원치 않으실 경우 고객센터, 게시판 문의를 이용해 주시기 바랍니다.</span></p>
					<p dir="ltr" style="border: 0px; margin-top: 0pt; margin-bottom: 0pt; padding: 0px; list-style: none; line-height: 1.8; color: rgb(0, 0, 0); font-family: UNO, Helvetica, sans-serif; font-size: 11px;">&nbsp;</p>
					<p dir="ltr" style="border: 0px; margin-top: 0pt; margin-bottom: 0pt; padding: 0px; list-style: none; line-height: 1.8; color: rgb(0, 0, 0); font-family: UNO, Helvetica, sans-serif; font-size: 11px;">&nbsp;</p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">부 칙(시행일) 이 약관은 2022년 7월 26일부터 시행합니다.</span></p>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="checkbox" id="checkbox2" name="checkbox" style="width: 13px; height: 13px; position: relative; top: 3px; accent-color: rgb(22, 160, 133);" required/>
					<label style="font-size: 13px;" for="checkbox2">(필수) 개인정보 수집 및 이용에 동의합니다.</label>
					<button type="button" id="checkbox2-overview"style="font-size: 12px; font-weight: bold; -webkit-transform: scale(2,1); float: right; background-color: #fff; border: none; margin-right: 5px; cursor: pointer;">∨</button>
					<div id="toggle2" style="overflow: scroll; width: 557px; height: 150px; font-size: 13px; display: none; margin-top: 7px; margin-right: 8px; padding: 0 7px;">
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">(주)kh (이하&nbsp;"회사"는)&nbsp;고객님의 개인정보를 중요시하며, "정보통신망 이용촉진 및 정보보호"에 관한 법률을 준수하고 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회사는 개인정보처리방침을 통하여 고객님께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며,&nbsp;개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">■ 수집하는 개인정보 항목 및 수집방법</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">가.&nbsp;수집하는 개인정보의 항목</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;회사는 회원가입,&nbsp;상담,&nbsp;서비스 신청 등을 위해 아래와 같은 개인정보를 수집하고 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-&nbsp;회원가입시&nbsp;:&nbsp;이름&nbsp;,&nbsp;생년월일&nbsp;,&nbsp;성별&nbsp;,&nbsp;로그인ID ,&nbsp;비밀번호&nbsp;,&nbsp;자택 전화번호&nbsp;,&nbsp;휴대전화번호&nbsp;,&nbsp;이메일&nbsp;, 14세미만 가입자의 경우 법정대리인의 정보</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-&nbsp;서비스 신청시&nbsp;:&nbsp;주소,&nbsp;결제 정보</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;서비스 이용 과정이나 사업 처리 과정에서 서비스이용기록,&nbsp;접속로그,&nbsp;쿠키,&nbsp;접속&nbsp;IP,&nbsp;결제 기록,&nbsp;불량이용 기록이 생성되어 수집될 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">나.&nbsp;수집방법</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-&nbsp;홈페이지,&nbsp;서면양식,&nbsp;게시판,&nbsp;이메일,&nbsp;이벤트 응모,&nbsp;배송요청,&nbsp;전화,&nbsp;팩스,&nbsp;생성 정보 수집 툴을 통한 수집</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">■ 개인정보의 수집 및 이용목적</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">콘텐츠 제공&nbsp;,&nbsp;구매 및 요금 결제&nbsp;,&nbsp;물품배송 또는 청구지 등 발송&nbsp;,&nbsp;금융거래 본인 인증 및 금융 서비스</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;회원 관리</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회원제 서비스 이용에 따른 본인확인&nbsp;,&nbsp;개인 식별&nbsp;,&nbsp;불량회원의 부정 이용 방지와 비인가 사용 방지&nbsp;,&nbsp;가입 의사 확인&nbsp;,&nbsp;연령확인&nbsp;,&nbsp;만14세 미만 아동 개인정보 수집 시 법정 대리인 동의여부 확인,&nbsp;불만처리 등 민원처리&nbsp;,&nbsp;고지사항 전달</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;마케팅 및 광고에 활용</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">이벤트 등 광고성 정보 전달&nbsp;,&nbsp;접속 빈도 파악 또는 회원의 서비스 이용에 대한 통계</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">■ 개인정보의 보유 및 이용기간</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">원칙적으로,&nbsp;개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다.&nbsp;단,&nbsp;다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">가.&nbsp;회사 내부방침에 의한 정보보유 사유</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회원이 탈퇴한 경우에도 불량회원의 부정한 이용의 재발을 방지,&nbsp;분쟁해결 및 수사기관의 요청에 따른 협조를 위하여,&nbsp;이용계약 해지일로부터&nbsp;1년간 회원의 정보를 보유할 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">나.&nbsp;관련 법령에 의한 정보 보유 사유</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">전자상거래등에서의소비자보호에관한법률 등 관계법령의 규정에 의하여 보존할 필요가 있는 경우 회사는 아래와 같이 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;계약 또는 청약철회 등에 관한 기록</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-보존이유&nbsp;:&nbsp;전자상거래등에서의소비자보호에관한법률</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-보존기간&nbsp;: 5년</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;대금 결제 및 재화 등의 공급에 관한 기록</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-보존이유:&nbsp;전자상거래등에서의소비자보호에관한법률</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-보존기간&nbsp;: 5년</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;소비자 불만 또는 분쟁처리에 관한 기록</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-보존이유&nbsp;:&nbsp;전자상거래등에서의소비자보호에관한법률</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-보존기간&nbsp;: 3년</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;로그 기록</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-보존이유:&nbsp;통신비밀보호법</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">-보존기간&nbsp;: 3개월</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">■ 개인정보의 파기절차 및 방법</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회사는 원칙적으로 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체없이 파기합니다.&nbsp;파기절차 및 방법은 다음과 같습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;파기절차</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회원님이 회원가입 등을 위해 입력하신 정보는 목적이 달성된 후 별도의&nbsp;DB로 옮겨져(종이의 경우 별도의 서류함)&nbsp;내부 방침 및 기타 관련 법령에 의한 정보보호 사유에 따라(보유 및 이용기간 참조)&nbsp;일정 기간 저장된 후 파기되어집니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">별도&nbsp;DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 보유되어지는 이외의 다른 목적으로 이용되지 않습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;파기방법</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">전자적 파일형태로 저장된 개인정보는 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">■ 개인정보 제공</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회사는 이용자의 개인정보를 원칙적으로 외부에 제공하지 않습니다.&nbsp;다만,&nbsp;아래의 경우에는 예외로 합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;이용자들이 사전에 동의한 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;법령의 규정에 의거하거나,&nbsp;수사 목적으로 법령에 정해진 절차와 방법에 따라 수사기관의 요구가 있는 경우</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">■ 수집한 개인정보의 위탁</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회사는 서비스 이행을 위해 아래와 같이 외부 전문업체에 위탁하여 운영하고 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;위탁 대상자&nbsp;: CJ&nbsp;대한통운㈜</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;위탁업무 내용&nbsp;:&nbsp;배송 업무 대행</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;위탁 대상자&nbsp;: KG모빌리언스</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;위탁업무 내용&nbsp;:&nbsp;결제관련</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;"></span></p>
					<p style="border: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 1.5; color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; #개인정보의 위탁 처리</span></p>
					<p style="border: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 1.5; color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; &nbsp; &nbsp; &nbsp; “kh(주)”는 서비스 향상을 위해 관계법령에 따라 회원의 동의를 얻거나 관련 사항을 공개 또는 고지 후 회원의 개인정보를 외부에 위탁하여 처리하고 있습니다.&nbsp;<span style="border: 0px; margin: 0px; padding: 0px; list-style: none;">“(주)kh”</span>&nbsp;의 개인정보처리 수탁자와 그 업무의 내용은 다음과 같습니다.</span></p>
					<p style="border: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 1.5; color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; &nbsp; &nbsp; &nbsp; - 수탁자 : (주)루나소프트</span></p>
					<p style="border: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 1.5; color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; &nbsp; &nbsp; &nbsp; - 개인정보제공처 : 주식회사 카카오</span></p>
					<p style="border: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 1.5; color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; &nbsp; &nbsp; &nbsp; - 위탁 업무 내용 : 카카오톡 알림톡(정보성 메시지) 발송 업무, 카카오톡 기반 상담 서비스 제공</span></p>
					<p style="border: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 1.5; color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; &nbsp; &nbsp; &nbsp; - 수집항목 : 휴대폰번호, 주문정보, 배송정보</span></p>
					<p style="border: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 1.5; color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp; &nbsp; &nbsp; &nbsp; - 보유기간 : 회원 탈퇴 시 혹은 법정 보유기간</span></p>
					<p dir="ltr" style="border: 0px; margin-top: 0pt; margin-bottom: 0pt; padding: 0px; list-style: none; line-height: 1.8; color: rgb(0, 0, 0); font-family: UNO, Helvetica, sans-serif; font-size: 11px;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">﻿</span><br style="border: 0px; margin: 0px; padding: 0px; list-style: none;"></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">■ 이용자 및 법정대리인의 권리와 그 행사방법</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;이용자는 언제든지 등록되어 있는 자신의 개인정보를 조회하거나 수정할 수 있으며 가입해지를 요청할 수도 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;이용자들의 개인정보 조회,수정을 위해서는&nbsp;"개인정보변경"(또는&nbsp;"회원정보수정"&nbsp;등)을 가입해지(동의철회)를 위해서는&nbsp;"회원탈퇴"를 클릭하여 본인 확인 절차를 거치신 후 직접 열람,&nbsp;정정 또는 탈퇴가 가능합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;혹은 개인정보보호책임자에게 서면,&nbsp;전화 또는 이메일로 연락하시면 지체없이 조치하겠습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;귀하가 개인정보의 오류에 대한 정정을 요청하신 경우에는 정정을 완료하기 전까지 당해 개인정보를 이용 또는 제공하지 않습니다.&nbsp;또한 잘못된 개인정보를 제3자에게 이미 제공한 경우에는 정정 처리결과를 제3자에게 지체없이 통지하여 정정이 이루어지도록 하겠습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;회사는 이용자의 요청에 의해 해지 또는 삭제된 개인정보는&nbsp;"회사가 수집하는 개인정보의 보유 및 이용기간"에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 이용할 수 없도록 처리하고 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">■ 개인정보 자동수집 장치의 설치,&nbsp;운영 및 그 거부에 관한 사항</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회사는 귀하의 정보를 수시로 저장하고 찾아내는&nbsp;"쿠키(cookie)"&nbsp;등을 운용합니다.&nbsp;쿠키란 웹사이트를 운영하는데 이용되는 서버가 귀하의 브라우저에 보내는 아주 작은 텍스트 파일로서 귀하의 컴퓨터 하드디스크에 저장됩니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회사은(는)&nbsp;다음과 같은 목적을 위해 쿠키를 사용합니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;쿠키 등 사용 목적</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">1.&nbsp;회원과 비회원의 접속 빈도나 방문 시간 등을 분석,&nbsp;이용자의 취향과 관심분야를 파악 및 자취 추적,&nbsp;각종 이벤트 참여 정도 및 방문 회수 파악 등을 통한 타겟 마케팅 및 개인 맞춤 서비스 제공</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">2.&nbsp;귀하는 쿠키 설치에 대한 선택권을 가지고 있습니다.&nbsp;따라서,&nbsp;귀하는 웹브라우저에서 옵션을 설정함으로써 모든 쿠키를 허용하거나,&nbsp;쿠키가 저장될 때마다 확인을 거치거나,&nbsp;아니면 모든 쿠키의 저장을 거부할 수도 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;쿠키 설정 거부 방법</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">1.&nbsp;쿠키 설정을 거부하는 방법으로는 회원님이 사용하시는 웹 브라우저의 옵션을 선택함으로써 모든 쿠키를 허용하거나 쿠키를 저장할 때마다 확인을 거치거나,&nbsp;모든 쿠키의 저장을 거부할 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">2.&nbsp;설정방법 예(인터넷 익스플로어의 경우) :&nbsp;웹 브라우저 상단의 도구&nbsp;&gt;&nbsp;인터넷 옵션&nbsp;&gt;&nbsp;개인정보</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">3.&nbsp;단,&nbsp;귀하께서 쿠키 설치를 거부하였을 경우 서비스 제공에 어려움이 있을 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">&nbsp;</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">■ 개인정보에 관한 민원서비스</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">회사는 고객의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 관련 부서 및 개인정보보호책임자를 지정하고 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;개인정보보호책임자</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">성명&nbsp;:&nbsp;차혜영</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">전화번호&nbsp;: 02-790-4096</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">이메일&nbsp;: hello@nonfiction.kr</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;개인정보에 관한 민원 서비스 연락처</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">전화번호&nbsp;: 02-790-4096</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">이메일&nbsp;: hello@nonfiction.kr</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;귀하께서는 회사의 서비스를 이용하시며 발생하는 모든 개인정보보호 관련 민원을 개인정보보호책임자 혹은 담당부서로 신고하실 수 있습니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;회사는 이용자들의 신고사항에 대해 신속하게 충분한 답변을 드릴 것입니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">o&nbsp;기타 개인정보침해에 대한 신고나 상담이 필요하신 경우에는 아래 기관에 문의하시기 바랍니다.</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">개인정보침해신고센터&nbsp;(privacy.kisa.or.kr /&nbsp;국번 없이&nbsp;118)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">개인정보분쟁조정위원회&nbsp;(kopico.go.kr / 1833-6972)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">대검찰청 사이버수사과&nbsp;(spo.go.kr /&nbsp;지역번호+1301)</span></p>
					<p style="border: 0px; margin: 0cm 0cm 8pt; padding: 0px; list-style: none; line-height: 13.91px; color: rgb(0, 0, 0); text-align: justify; font-family: &quot;맑은 고딕&quot;;"><span style="border: 0px; margin: 0px; padding: 0px; list-style: none; font-size: 12px;">경찰청 사이버안전국&nbsp;(cyberbureau.police.go.kr /&nbsp;국번없이&nbsp;182)</span></p>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="checkbox" id="checkbox3" name="checkbox" style="width: 13px; height: 13px; position: relative; top: 3px; accent-color: rgb(22, 160, 133);" required/>
					<label style="font-size: 13px;" for="checkbox3">(필수) 만 14세 이상입니다.</label>
					</td>
				</tr>
				
			</table>
			<div id="submit-container">
				<input type="submit" value="JOIN" />
			</div>
		</form>
	</div>
</section>
<script>
/**
 * 폼 유효성 검사
 */
const availableCheck = (input, result, msg) => {
	if (result === "fail") {
	  input.style.color = 'red';
	} else {
		input.style.color = 'blue';
	}
	input.innerHTML = msg;
};

const password = document.querySelector("#password");
const msgPassword = document.querySelector("#msgPassword");
password.addEventListener('input', (e) => {
	if (!/^(?=.*\d{1,})(?=.*[!@#$%^&*]{1,})(?=.*[a-zA-Z]{1,}).{8,12}$/.test(password.value)) {
		availableCheck(msgPassword, "fail", "❌ 비밀번호는 영문+숫자+특수문자(!@#$%^&*) 포함 8자리 이상이여야 합니다");
	} else {
		availableCheck(msgPassword, "clear", "");
	}
});

password.addEventListener('blur', (e) => {
	if (!/^(?=.*\d{1,})(?=.*[!@#$%^&*]{1,})(?=.*[a-zA-Z]{1,}).{8,12}$/.test(password.value)) {
		password.select();
	} else {
		msgPassword.innerHTML = "";
	}
});

const passwordCheck = document.querySelector("#passwordCheck");
passwordCheck.addEventListener('blur', (e) => {
	if (password.value !== passwordCheck.value) {
		availableCheck(msgPassword, "fail", "❌ 비밀번호가 일치하지 않습니다");
		password.select();
		passwordCheck.value ="";
	}
});
 
const memberName = document.querySelector("#memberName");
const msgMemberName = document.querySelector("#msgMemberName");
memberName.addEventListener('input', (e) => {
	if (!/^[가-힣]{2,}$/.test(memberName.value)) {
		availableCheck(msgMemberName, "fail", "❌ 한글 2글자 이상 입력해주세요");
	} else {
		availableCheck(msgMemberName, "clear", "");
	}
});

memberName.addEventListener('blur', (e) => {
	if (!/^[가-힣]{2,}$/.test(memberName.value)) {
		memberName.select();
	} else {
		msgMemberName.innerHTML = "";
	}
});

const email = document.querySelector("#email");
const msgEmail = document.querySelector("#msgEmail");
email.addEventListener('input', (e) => {
	if (!/^([\w\.-]+)@([\w-]+)(\.[\w-]+){1,2}$/.test(email.value)) {
		availableCheck(msgEmail, "fail", "❌ 이메일 형식이 유효하지 않습니다");
	} else {
		availableCheck(msgEmail, "clear", "");
	}
});

email.addEventListener('blur', (e) => {
	if (!/^([\w\.-]+)@([\w-]+)(\.[\w-]+){1,2}$/.test(email.value)) {
		email.select();
	} else {
		msgEmail.innerHTML = "";
	}
});

const phone2 = document.querySelector("#phone2");
const msgPhone = document.querySelector("#msgPhone");
phone2.addEventListener('input', (e) => {
	if (!/[0-9]{3,4}$/.test(phone2.value)) {
		availableCheck(msgPhone, "fail", "❌ 휴대폰 번호를 다시 확인해주세요");
	} else {
		availableCheck(msgPhone, "clear", "");
	}
});

phone2.addEventListener('blur', (e) => {
	if (!/[0-9]{3,4}$/.test(phone2.value)) {
		phone2.select();
	} else {
		msgPhone.innerHTML = "";
	}
});

const phone3 = document.querySelector("#phone3");
phone3.addEventListener('input', (e) => {
	if (!/[0-9]{4,}$/.test(phone3.value)) {
		availableCheck(msgPhone, "fail", "❌ 휴대폰 번호를 다시 확인해주세요");
	} else {
		availableCheck(msgPhone, "clear", "");
	}
});

phone3.addEventListener('blur', (e) => {
	if (!/[0-9]{4,}$/.test(phone3.value)) {
		phone3.select();
	} else {
		msgPhone.innerHTML = "";
	}
});


function checkId(){
	let memberId = $('#memberId').val();
	
	$.ajax({
		url : '<%= request.getContextPath()%>/member/memberCheckId',
		type : "post",
		data : {memberId : memberId},
		dataType : 'json',
		success : function(result){
				if(/^[a-z\d]{6,10}$/.test(memberId) && result == 0){	
					$("#msgMemberId").html('❌ 사용할 수 없는 아이디입니다');
					$("#msgMemberId").css("color", 'red');
					document.querySelector("#memberId").addEventListener('focusout', (e) => {
						e.target.select();
					});
					return false;
				}	
				else if(!/^[a-z\d]{6,10}$/.test(memberId) && result != 0) {
					$("#msgMemberId").html('❌ 아이디는 영소문자/숫자 포함 6자리 이상이여야 합니다');
					$("#msgMemberId").css("color", 'red');
					document.querySelector("#memberId").addEventListener('focusout', (e) => {
						e.target.select();
					});
				}
				
				else if(/^[a-z\d]{6,10}$/.test(memberId) && result != 0) {
					$("#msgMemberId").html('✔️ 사용가능한 아이디입니다');
					$("#msgMemberId").css("color", 'blue');
					document.querySelector("#memberId").addEventListener('focusout', (e) => {
						document.querySelector("#password").select();
					});
				}

		},
		error : console.log
		
	})
};


$(function (){
	$("#checkbox1-overview").click(function (){
  	$("#toggle1").toggle();
  });
});
$(function (){
	$("#checkbox2-overview").click(function (){
  	$("#toggle2").toggle();
  });
});

$(document).ready(function() {
	$("#all-checkbox").click(function() {
		if($("#all-checkbox").is(":checked")) $("input[name=checkbox]").prop("checked", true);
		else $("input[name=checkbox]").prop("checked", false);
	});
	
	$("input[name=checkbox]").click(function() {
		let total = $("input[name=checkbox]").length;
		let checked = $("input[name=checkbox]:checked").length;
		
		if(total != checked) $("#all-checkbox").prop("checked", false);
		else $("#all-checkbox").prop("checked", true);
	});
	
});

</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
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
</script>
<%@include file="/WEB-INF/views/common/footer.jsp"%>