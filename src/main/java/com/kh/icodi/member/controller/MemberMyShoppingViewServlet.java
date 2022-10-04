package com.kh.icodi.member.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.common.MemberOrderProductManager;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberMyShoppingViewServlet
 */
@WebServlet("/member/myShopping")
public class MemberMyShoppingViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
			Date beforeThreeMon = addMonth(date, -3);
			String start = dateFormat.format(beforeThreeMon);
			String end = dateFormat.format(date);
			
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			Map<String, Object> data = new HashMap<>();
			data.put("memberId", memberId);
			data.put("start", start);
			data.put("end", end);
			
			List<MemberOrderProductManager> orderList = memberService.findOrderListByMemberId(data);
			
			request.setAttribute("orderList", orderList);
			request.setAttribute("defaultDate", data);
			request.getRequestDispatcher("/WEB-INF/views/member/memberMyShopping.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Date addMonth(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}
}
