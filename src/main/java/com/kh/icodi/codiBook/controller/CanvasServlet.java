package com.kh.icodi.codiBook.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.codiBook.model.dto.IsOpen;
import com.kh.icodi.codiBook.model.service.CodiBookService;

/**
 * Servlet implementation class CanvasServlet
 */
@WebServlet("/canvas")
public class CanvasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private CodiBookService codiBookService = new CodiBookService();


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String imgSrc = request.getParameter("imgSrc");
			String memberId = request.getParameter("memberId");
			String useProductArr = request.getParameter("useProductArr");
			String content = request.getParameter("content");
			String _isOpen = request.getParameter("isOpen");
			IsOpen isOpen = _isOpen == null ? IsOpen.Y : IsOpen.N;
			
			Map<String, Object> param = new HashMap<>();
			param.put("imgSrc", imgSrc);
			param.put("memberId", memberId);
			param.put("useProductArr", useProductArr);
			param.put("content", content);
			param.put("isOpen", isOpen);

			int result = codiBookService.insertCodi(param);
			
			response.sendRedirect(request.getContextPath() + "/codibook/create");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
