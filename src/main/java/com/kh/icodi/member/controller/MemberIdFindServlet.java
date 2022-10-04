package com.kh.icodi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class idFindServlet
 */
@WebServlet("/member/memberIdFind")
public class MemberIdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService =  new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberIdFind.jsp")
			.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			String memberName = request.getParameter("memberName");
			String memberPhone = request.getParameter("memberPhone");
			Member member = new Member();
			member.setMemberName(memberName);
			member.setPhone(memberPhone);
			System.out.println("memberName " + memberName);
			System.out.println("memberPhone " + memberPhone);
			
			String memberId = memberService.findMemberId(member); // db 요청
			Gson json = new Gson();
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().println(json.toJson(memberId));
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
