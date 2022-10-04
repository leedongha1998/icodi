package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String memberId = request.getParameter("memberId");
			
			int result = memberService.deleteMember(memberId);
			
			HttpSession session = request.getSession();
			Enumeration<String> names = session.getAttributeNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				session.removeAttribute(name);
			}
			
			Cookie c = new Cookie("saveId", memberId);
			c.setPath(request.getContextPath());
			c.setMaxAge(0);
			response.addCookie(c);
			
			session.setAttribute("msg", "회원 탈퇴를 완료했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
