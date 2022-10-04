package com.kh.icodi.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.admin.model.dto.ProductAttachment;
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class ProductFindServlet
 */
@WebServlet("/product/productFind")
public class ProductFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String searchKeyword = request.getParameter("searchFrm");
			
			int totalContent = adminService.getTotalContentBySearchKeyword(searchKeyword);
			
			int numPerPage = 12;
			int totalPage = (int)Math.ceil((double)totalContent / numPerPage);
			
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("/WEB-INF/views/product/productLikeList.jsp")
			.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


}
