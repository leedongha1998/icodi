package com.kh.icodi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.cscenter.model.service.CsCenterService;

/**
 * Servlet implementation class AdminInquireAnswerDeleteServlet
 */
@WebServlet("/inquireAnswerDelete")
public class AdminInquireAnswerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CsCenterService csCenterService = new CsCenterService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
//			 delete from inquire_comment where inquire_no = ? and answer_no = ?
			
			int inquireNo = Integer.parseInt(request.getParameter("inquireNo"));
			int answerNo = Integer.parseInt(request.getParameter("answerNo"));
			System.out.println("inquireNo=" + inquireNo);
			int result = csCenterService.deleteInquireAnswer(inquireNo,answerNo);
					
					
			response.sendRedirect(request.getHeader("Referer"));
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
