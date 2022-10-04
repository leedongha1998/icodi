package com.kh.icodi.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class IndexNewProductServlet
 */
@WebServlet("/product/newProdcut")
public class IndexNewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			List<ProductExt> productList = adminService.newProduct();
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(productList, response.getWriter());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
