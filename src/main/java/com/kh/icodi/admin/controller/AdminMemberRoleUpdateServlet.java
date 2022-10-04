package com.kh.icodi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.dto.MemberRole;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberRoleUpdateServlet
 */
@WebServlet("/admin/memberRoleUpdate")
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String memberId = request.getParameter("memberId");
			MemberRole memberRole = MemberRole.valueOf(request.getParameter("memberRole"));
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberRole(memberRole);
			
			HttpSession session = request.getSession(true);
			
			int result = memberService.updateMemberRole(member);
			
			session.setAttribute("msg", "회원권한을 성공적으로 수정했습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/memberList");	
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
