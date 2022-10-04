package com.kh.icodi.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class AdminUpdateOrderStatusServlet
 */
@WebServlet("/admin/updateStatus")
public class AdminUpdateOrderStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String orderNo = request.getParameter("orderNo");
			String updateStatus = request.getParameter("updateStatus");

			Map<String, Object> data = new HashMap<>();
			data.put("orderNo", orderNo);
			data.put("updateStatus", updateStatus);
			
			int result = adminService.updateOrderStatus(data);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
