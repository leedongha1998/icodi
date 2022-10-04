package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			String memberId = request.getParameter("memberId");
			String memberName = request.getParameter("memberName");
			String email = request.getParameter("email");
			String[] phones = request.getParameterValues("phone");
			String address = request.getParameter("address");
			String addressEx = request.getParameter("addressEx");
			
			String phone = phones != null ?
					String.join("-", phones) 
					// String.join("", phones) 
					: null;
			
			Member member = 
					new Member(memberId, memberName, null, email, phone, null, null, 0, address, addressEx);
			System.out.println("member@MemberUpdateServlet = " + member);
			
			int result = memberService.updateMember(member);
			
			HttpSession session = request.getSession();
			String msg = "";
			
			if(result>0) {
				msg = "회원 정보가 수정되었습니다.";
				session.setAttribute("loginMember", memberService.findById(memberId));
			}
			
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/member/memberMyPage");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
