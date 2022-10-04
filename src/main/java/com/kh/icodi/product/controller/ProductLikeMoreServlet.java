package com.kh.icodi.product.controller;

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
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class ProductLikeMoreServlet
 */
@WebServlet("/product/moreLike")
public class ProductLikeMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AdminService adminService = new AdminService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cPage = Integer.parseInt(request.getParameter("cPage"));
			String searchKeyword = request.getParameter("searchKeyword");
			System.out.println("searchKeyword@more = " + searchKeyword);
			int numPerPage = 12;
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			param.put("searchKeyword", searchKeyword);
			
			List<ProductExt> productList = adminService.findProductLike(param);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(productList, response.getWriter());
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
