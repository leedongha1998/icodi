package com.kh.icodi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/member/memberLogout")
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 업무로직 : 세션객체를 가져와서 무효화처리
			HttpSession session = request.getSession(false); // 세션객체가 존재하지 않으면 null을 반환
			if (session != null) {
				session.invalidate();
			}
			
			// 2. 리다이렉트
			response.sendRedirect(request.getContextPath() + "/");
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
