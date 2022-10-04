package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.common.MemberProductManager;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class ProductOrderServlet
 */
@WebServlet("/member/order")
public class MemberOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] productCode = request.getParameterValues("productCode");
			String[] _productCount = request.getParameterValues("productCount");
			int[] productCount = new int[_productCount.length];
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			List<MemberProductManager> order = new ArrayList<>();

			for(int i = 0; i < _productCount.length; i++) {
				productCount[i] = Integer.parseInt(_productCount[i]);
			}
			
			Map<String, Object> data = new HashMap<>();
			for(int i = 0; i < productCode.length; i++) {
				data.put("productCode", productCode[i]);
				data.put("productCount", productCount[i]);
				data.put("memberId", memberId);
				order.add(memberService.buyItNow(data));
			}
			
			request.setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/views/member/memberOrder.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
