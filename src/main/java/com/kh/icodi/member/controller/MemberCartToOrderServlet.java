package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.common.MemberProductManager;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberCartToOrderServlet
 */
@WebServlet("/member/cartToOrder")
public class MemberCartToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] _cartNo = request.getParameterValues("checkbox");
			int[] cartNo = new int[_cartNo.length];
			for(int i = 0; i < cartNo.length; i++) {
				cartNo[i] = Integer.parseInt(_cartNo[i]);
			}
			
			List<MemberProductManager> order = memberService.findOrderListByCartNo(cartNo);
			request.setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/views/member/memberOrder.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
