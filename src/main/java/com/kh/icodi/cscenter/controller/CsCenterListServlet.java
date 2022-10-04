package com.kh.icodi.cscenter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.cscenter.model.dto.CsCenter;
import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.cscenter.model.service.CsCenterService;

/**
 * Servlet implementation class CsCenterViewServlet
 */
@WebServlet("/cs_center")
public class CsCenterListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CsCenterService csCenterService = new CsCenterService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			List<CsCenter> list = csCenterService.findAll();
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/cscenter/csCenter.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
