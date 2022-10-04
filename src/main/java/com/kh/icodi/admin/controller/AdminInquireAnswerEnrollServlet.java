package com.kh.icodi.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.cscenter.model.dto.CsCenterInquireAnswer;
import com.kh.icodi.cscenter.model.service.CsCenterService;
import com.kh.icodi.notification.model.service.NotificationService;

/**
 * Servlet implementation class AdminInquireAnswerEnrollServlet
 */
@WebServlet("/inquireAnswerEnroll")
public class AdminInquireAnswerEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CsCenterService csCenterService = new CsCenterService();
	private NotificationService notificationService = new NotificationService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 
			int inquireNo = Integer.parseInt(request.getParameter("inquireNo"));
			String answerWriter = request.getParameter("answerWriter");
			String answerContent = request.getParameter("answerContent");
			
			CsCenterInquireAnswer answer = new CsCenterInquireAnswer(0, inquireNo, answerWriter, answerContent, null);
			
			System.out.println("answer = " + answer);
			
			int result = csCenterService.insertInquireAnswer(answer);
			
			//문의 작성자에게 답변알림
			CsCenterInquire csCenterInquire = csCenterService.findInquireContentByNo(inquireNo);
			result = notificationService.notifyInquireAnswer(csCenterInquire);
			
			
			
			
			response.sendRedirect(request.getContextPath() + "/csCenter/inquireView?no=" + inquireNo);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
