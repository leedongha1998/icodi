package com.kh.icodi.cscenter.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.cscenter.model.dto.CsCenterInquireAnswer;
import com.kh.icodi.cscenter.model.dto.SelectType;
import com.kh.icodi.cscenter.model.service.CsCenterService;
import com.kh.icodi.notification.model.service.NotificationService;

/**
 * Servlet implementation class CsCenterInqurireEnrollServlet
 */
@WebServlet("/csCenter/inquireEnroll")
public class CsCenterInqurireEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CsCenterService csCenterService = new CsCenterService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/cscenter/csCenterInquire.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리
			String memberId = request.getParameter("memberId");
			String title = request.getParameter("title");
			SelectType selectType = SelectType.valueOf(request.getParameter("selectType"));
			String content = request.getParameter("content");
			CsCenterInquire csCenterInquire = new CsCenterInquire(0, memberId, title, selectType, content, null);
			System.out.println(csCenterInquire);
			
			//2. 업무로직
			int result = csCenterService.insertInquire(csCenterInquire);
			System.out.println("result = " + result);
			
			
			
			
			//3. redirect
			request.getSession().setAttribute("msg", "문의를 성공적으로 등록했습니다.");
			response.sendRedirect(request.getContextPath() + "/cs_center");
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
