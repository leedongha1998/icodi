package com.kh.icodi.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class EnrollAutoComplete
 */
@WebServlet("/productCodeList")
public class EnrollAutoComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String term = request.getParameter("term");
			
			List<String> resultList = new ArrayList<>();
			List<String> list = adminService.findProductCodeList();

			for(String product : list) {
				if(product.contains(term)) {
					resultList.add(product);
				}
			}

			response.setContentType("text/csv; charset=utf-8");
			PrintWriter out = response.getWriter();
			for(int i = 0; i < resultList.size(); i++) {
				out.print(resultList.get(i));
				if(i != resultList.size() - 1)
					out.print(",");
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
