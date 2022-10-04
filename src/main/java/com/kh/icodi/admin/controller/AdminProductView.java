package com.kh.icodi.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.codiBook.model.service.CodiBookService;

/**
 * Servlet implementation class AdminProductView
 */
@WebServlet("/admin/productView")
public class AdminProductView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CodiBookService codiBookService = new CodiBookService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/admin/productDelete.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			request.setCharacterEncoding("utf-8");
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			List<ProductExt> product = codiBookService.findAllByCategoryNo(categoryNo);

			Gson gson = new Gson();
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(gson.toJson(product));
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
