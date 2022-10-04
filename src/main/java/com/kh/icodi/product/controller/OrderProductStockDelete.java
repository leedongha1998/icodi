package com.kh.icodi.product.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class OrderProductStockDelete
 */
@WebServlet("/product/deleteStock")
public class OrderProductStockDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String data = request.getParameter("data");
			List<Map<String, Object>> list = new Gson().fromJson(data, List.class);
			int result = adminService.deleteOrderProductStock(list);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
