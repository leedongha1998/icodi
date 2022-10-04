package com.kh.icodi.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.admin.model.service.AdminService;
import com.kh.icodi.common.IcodiMvcUtils;
import com.kh.icodi.common.MemberOrderProductManager;

/**
 * Servlet implementation class AdminPaymentCompletedServlet
 */
@WebServlet("/admin/paymentCompleted")
public class AdminPaymentCompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int numPerPage = 15;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {}
			
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> data = new HashMap<>();
			data.put("start", start);
			data.put("end", end);
			
			List<MemberOrderProductManager> list = adminService.findOrderListDeliveryContains(data);
			int totalContent = adminService.getTotalContentDeliveryContains();
			
			String url = request.getRequestURI();
			String pagebar = IcodiMvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
			
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/admin/paymentCompleted.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
