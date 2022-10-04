package com.kh.icodi.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/product/detail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String productName = request.getParameter("product_name");
			List<ProductExt> productList = adminService.findProductByProductName(productName);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("/WEB-INF/views/product/productDetail.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
