package com.kh.icodi.cscenter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.cscenter.model.dto.CsCenter;
import com.kh.icodi.cscenter.model.service.CsCenterService;

/**
 * Servlet implementation class CsCenterViewServlet
 */
@WebServlet("/csCenter/csCenterView")
public class CsCenterViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CsCenterService csCenterService = new CsCenterService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int noticeNo = Integer.parseInt(request.getParameter("no"));
			CsCenter csCenter = csCenterService.findNoticeContentByNo(noticeNo);
			
			request.setAttribute("csCenter", csCenter);
			request.getRequestDispatcher("/WEB-INF/views/cscenter/csCenterView.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

}
