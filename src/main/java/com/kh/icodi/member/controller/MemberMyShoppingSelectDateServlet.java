package com.kh.icodi.member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.common.MemberOrderProductManager;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberMyShoppingSelectDateServlet
 */
@WebServlet("/member/myShoppingSelectDate")
public class MemberMyShoppingSelectDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
			String _start = request.getParameter("start");
			String _end = request.getParameter("end");
			String memberId = request.getParameter("memberId");

			String start = dateFormat.format(new SimpleDateFormat("yyyy-MM-dd").parse(_start));
			String end = dateFormat.format(new SimpleDateFormat("yyyy-MM-dd").parse(_end));
			
			Map<String, Object> data = new HashMap<>();
			data.put("start", start);
			data.put("end", end);
			data.put("memberId", memberId);
			
			List<MemberOrderProductManager> orderList = memberService.findOrderListByMemberId(data);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(orderList, response.getWriter());
		} catch(ParseException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}
}
