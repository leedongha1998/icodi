package com.kh.icodi.codiBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.icodi.codiBoard.model.service.CodiBoardService;
import com.kh.icodi.member.model.dto.Member;

/**
 * Servlet implementation class HotCodeListServlet
 */
@WebServlet("/codi/newCodiList")

public class NewCodeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CodiBoardService codiBoardService = new CodiBoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int totalContent = codiBoardService.getTotalContentNewCodi();
			int numPerPage = 12;
			int totalPage = (int)Math.ceil((double)totalContent / numPerPage);
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			String loginMemberId = null;
			
			if(loginMember == null) {
				
			} else {
				loginMemberId = loginMember.getMemberId();
			}
			
			request.setAttribute("loginMemberId", loginMemberId);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("/WEB-INF/views/codiBoard/newCodiList.jsp").forward(request, response);

		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
