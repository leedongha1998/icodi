package com.kh.icodi.stats.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.codiBoard.model.service.CodiBoardService;
import com.kh.icodi.stats.model.dto.Stats;
import com.kh.icodi.stats.model.exception.StatsService;

/**
 * Servlet implementation class StatsFinder
 */
@WebServlet("/stats/finder")
public class StatsFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StatsService statsService = new StatsService();   
    private CodiBoardService codiBoardService = new CodiBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String searchWhen = request.getParameter("when");
			String codiWhen = request.getParameter("codiWhen");
			
			int result = codiBoardService.countCodiByDate(codiWhen);
			
			List<Stats> list = statsService.findVisitStats(searchWhen);

			System.out.println("when : " + searchWhen);
			System.out.println("codiWhen : " + codiWhen);
			
			request.setAttribute("list", list);
			request.setAttribute("cnt", result);
			System.out.println("코디 개수확인 : " + result);
			
			request.getRequestDispatcher("/WEB-INF/views/admin/statsView.jsp").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
