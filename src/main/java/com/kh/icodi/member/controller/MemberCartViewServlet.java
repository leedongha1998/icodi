package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.common.MemberCartProductManager;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberCartViewServlet
 */
@WebServlet("/member/memberCart")
public class MemberCartViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			List<MemberCartProductManager> cartList = memberService.findCartListByMemberId(memberId);
			
			request.setAttribute("cartList", cartList);
			request.setAttribute("memberId", memberId);
			request.getRequestDispatcher("/WEB-INF/views/member/memberCartList.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
