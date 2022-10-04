package com.kh.icodi.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class BottomsViewServlet
 */
@WebServlet("/product/bottoms")
public class BottomsViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			int totalContent = adminService.getTotalContentByCategoryNo(categoryNo);
			System.out.println("totalCOntent = " +totalContent);
			int numPerPage = 12;
			int totalPage = (int)Math.ceil((double)totalContent / numPerPage);
			
			
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("categoryNo", categoryNo);
			request.getRequestDispatcher("/WEB-INF/views/product/bottomsView.jsp").forward(request, response);			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
