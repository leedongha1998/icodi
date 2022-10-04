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

import com.google.gson.Gson;
import com.kh.icodi.admin.model.service.AdminService;
import com.kh.icodi.common.IcodiMvcUtils;
import com.kh.icodi.common.MemberOrderProductManager;

/**
 * Servlet implementation class AdminSearchOrderListServlet
 */
@WebServlet("/admin/searchOrderList")
public class AdminSearchOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String searchKeyword = request.getParameter("searchKeyword");
			String searchValue = request.getParameter("searchValue");
			
			int numPerPage = 10;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {}
			
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> data = new HashMap<>();
			data.put("start", start);
			data.put("end", end);
			data.put("searchKeyword", searchKeyword);
			data.put("searchValue", searchValue);
			
			List<MemberOrderProductManager> list = adminService.findOrderListBySearchKeyword(data); 
			
			int totalContent = adminService.getTotalContentBySearchKeyword(data);
			System.out.println("totalContent = " + totalContent);
			String url = request.getRequestURI();
			String pagebar = IcodiMvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
			System.out.println("pagebar = " + pagebar);
			Map<String, Object> param = new HashMap<>();
			param.put("list", list);
			param.put("pagebar", pagebar);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(param, response.getWriter());
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
