package com.kh.icodi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.codiBoard.model.service.CodiBoardService;
import com.kh.icodi.member.model.dto.Member;

/**
 * Servlet implementation class MemberMyCodiListServlet
 */
@WebServlet("/member/memberMyCodiList")
public class MemberMyCodiListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CodiBoardService codiBoardService = new CodiBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			String loginMemberId = null;
			
			if(loginMember == null) {
				
			} else {
				loginMemberId = loginMember.getMemberId();
			}
			System.out.println("loginMemberId = " + loginMemberId);
			
			int totalContent = codiBoardService.getTotalContentByMe(loginMemberId);
			int numPerPage = 8;
			int totalPage = (int) Math.ceil((double) totalContent / numPerPage);
			
			request.setAttribute("loginMemerId", loginMemberId);
			request.setAttribute("totalPage", totalPage);
			System.out.println("totalPage = " + totalPage);
			request.getRequestDispatcher("/WEB-INF/views/member/memberMyCodiList.jsp")
				.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
