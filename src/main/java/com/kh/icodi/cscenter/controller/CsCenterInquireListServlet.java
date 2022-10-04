package com.kh.icodi.cscenter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.cscenter.model.dto.CsCenterInquireAnswer;
import com.kh.icodi.cscenter.model.service.CsCenterService;
import com.kh.icodi.member.model.dto.Member;

/**
 * Servlet implementation class CsCenterInquireViewServlet
 */
@WebServlet("/csCenter/inquireList")
public class CsCenterInquireListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CsCenterService csCenterService = new CsCenterService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			 
			 
			 String loginMemberId = request.getParameter("memberId");
			 
			 List<CsCenterInquire> list = csCenterService.findMyInquire(loginMemberId);
//			List<CsCenterInquireAnswer> answerList = csCenterService.findInquireAnwerByInquireNo(InquireNo);
			 
			 request.setAttribute("list", list);
//			 request.setAttribute("answerList", answerList);
			 request.getRequestDispatcher("/WEB-INF/views/cscenter/csCenterInquireList.jsp").forward(request, response);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }
		
	}

}
