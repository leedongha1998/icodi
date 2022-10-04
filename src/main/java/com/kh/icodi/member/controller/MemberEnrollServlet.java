package com.kh.icodi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.icodi.common.IcodiMvcUtils;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			String memberId = request.getParameter("memberId");
			String memberName = request.getParameter("memberName");
			String password = IcodiMvcUtils.getEncryptedPassword(request.getParameter("password"), memberId);
			// String password = request.getParameter("password");
			String email = request.getParameter("email");
			String[] phones = request.getParameterValues("phone");
			String address = request.getParameter("address");
			String addressEx = request.getParameter("addressEx");
			
			String phone = phones != null ? 
					String.join("-", phones)
					// String.join("", phones) 
					: null;
			
			Member member =
					new Member(memberId, memberName, password, email, phone, null, null, 0, address, addressEx);
			
			System.out.println("member = " + member);
			
			int result = memberService.insertMember(member);
			System.out.println("result@MemberEnrollServlet = " + result);
			
			
			HttpSession session = request.getSession();
			
			session.setAttribute("msg", "회원가입을 완료했습니다.");
			response.sendRedirect(request.getContextPath() + "/member/memberLogin");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
