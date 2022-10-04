package com.kh.icodi.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.cscenter.model.dto.CsCenter;
import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.cscenter.model.dto.CsCenterInquireAnswer;
import com.kh.icodi.cscenter.model.service.CsCenterService;

/**
 * Servlet implementation class AdminInquireAnswerServlet
 */
@WebServlet("/inquireAnswer")
public class AdminInquireAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CsCenterService csCenterService = new CsCenterService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<CsCenterInquire> list = csCenterService.findAllInquire();
			
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/views/admin/inquireAnswer.jsp").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
