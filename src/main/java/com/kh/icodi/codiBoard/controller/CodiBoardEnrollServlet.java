package com.kh.icodi.codiBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.codiBoard.model.service.CodiBoardService;

/**
 * Servlet implementation class CodiBoardEnrollServlet
 */
@WebServlet("/codiBoard/codiBoardEnroll")
public class CodiBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CodiBoardService codiBoardService = new CodiBoardService();   
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//
			request.setCharacterEncoding("utf-8");
			
			//
			String content = request.getParameter("content");
			System.out.println("content = " + content);
			String isOpen = request.getParameter("isOpen");
			if(isOpen == null) {
				isOpen = null;
			}
			System.out.println("isOpen = " + isOpen);
			
			//
			int result = codiBoardService.updateContent(content, isOpen);
		
			response.sendRedirect(request.getContextPath() + "/codibook/create");
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
