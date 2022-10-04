package com.kh.icodi.codiBook.controller;

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
import com.kh.icodi.member.model.dto.Member;

/**
 * Servlet implementation class CodiBookPageServlet
 */
@WebServlet("/codibook/create")
public class CodiBookCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CodiBookService codiBookService = new CodiBookService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Member loginMember = (Member) request.getSession().getAttribute("loginMember");
			String memberId = loginMember != null ? loginMember.getMemberId() : null;
			System.out.println("loginMemberId@servlet = " + memberId);
			request.setAttribute("loginMemberId", memberId);
			request.getRequestDispatcher("/WEB-INF/views/codiBook/codiBook.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
