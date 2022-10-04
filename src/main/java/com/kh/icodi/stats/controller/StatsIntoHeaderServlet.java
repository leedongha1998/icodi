package com.kh.icodi.stats.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.codiBoard.model.service.CodiBoardService;

/**
 * Servlet implementation class StatsIntoHeaderServlet
 */
@WebServlet("/viewCodiCnt")
public class StatsIntoHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CodiBoardService codiBoardService = new CodiBoardService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			 response.setContentType("text/html; charset=UTF-8");
		     PrintWriter out = response.getWriter();
			int result = codiBoardService.findAllCodiCnt();
			System.out.println(result);
			out.print(result);
		 } catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }
		
	}

}
