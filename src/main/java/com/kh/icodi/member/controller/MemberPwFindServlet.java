package com.kh.icodi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.common.IcodiMvcUtils;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberPwFindServlet
 */
@WebServlet("/member/memberPwFind")
public class MemberPwFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberSerivce = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberPwFind.jsp")
			.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
		
			String memberId = request.getParameter("memberId");
			String memberPhone = request.getParameter("memberPhone");
			String newPwd = IcodiMvcUtils.getEncryptedPassword(request.getParameter("newPwd"), memberId);
			Member member = new Member();
			member.setMemberId(memberId);
			member.setPhone(memberPhone);
			
			String memberPw = memberSerivce.findMemberPw(member, newPwd);
			Gson json = new Gson();
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().println(json.toJson(memberPw));
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
