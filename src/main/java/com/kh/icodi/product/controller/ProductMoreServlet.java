package com.kh.icodi.product.controller;

import static com.kh.icodi.common.JdbcTemplate.getConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.admin.model.dto.ProductAttachment;
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class ProductMoreServlet
 */
@WebServlet("/product/morePage")
public class ProductMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cPage = Integer.parseInt(request.getParameter("cPage"));
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			System.out.println("categoryNo = " + categoryNo);
			int numPerPage = 12;
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			param.put("categoryNo", categoryNo);
			
			List<ProductExt> productList = adminService.findProductList(param);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(productList, response.getWriter());
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}

}