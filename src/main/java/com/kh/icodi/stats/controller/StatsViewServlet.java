package com.kh.icodi.stats.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.stats.model.dto.Stats;
import com.kh.icodi.stats.model.exception.StatsService;

/**
 * Servlet implementation class StatsFinder
 */
@WebServlet("/admin/stats")
public class StatsViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List <Stats> list = new ArrayList<>();
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/views/admin/statsView.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
