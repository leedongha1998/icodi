package com.kh.icodi.member.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberPayServlet
 */
@WebServlet("/member/pay")
public class MemberPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_HHmmssSSS_");
			DecimalFormat df = new DecimalFormat("0000");
			
			String[] productCodes = request.getParameterValues("productCode");
			String payment = request.getParameter("finalPayment");
			int addPoint = Integer.parseInt(request.getParameter("finalMemberPoint"));
			int totalPrice = Integer.parseInt(request.getParameter("finalPrice"));
			String memberId = request.getParameter("finalMemberId");
			int usePoint = Integer.parseInt(request.getParameter("finalUsePoint"));
			String[] _cartAmount = request.getParameterValues("cartAmount");
			String[] _cartNo = request.getParameterValues("cartNo");
		
			int[] cartAmounts = new int[_cartAmount.length];
			int[] cartNo = new int[_cartNo.length];
			
			for(int i = 0; i < _cartAmount.length; i++) {
				cartAmounts[i] = Integer.parseInt(_cartAmount[i]);
				cartNo[i] = Integer.parseInt(_cartNo[i]);
			}
			
			
			Map<String, Object> data = new HashMap<>(); 
			data.put("usePoint", usePoint);
			
			for(int i = 0; i < productCodes.length; i++) { 
				String orderNo = sdf.format(new Date()) + df.format(Math.random()*10000);
				data.put("orderNo",  orderNo);
				data.put("productCode", productCodes[i]);
				data.put("payment", payment); 
				data.put("addPoint", addPoint);
				data.put("totalPrice", totalPrice); 
				data.put("memberId", memberId);
				data.put("cartAmount", cartAmounts[i]); 
				data.put("cartNo", cartNo[i]); 
				int result = memberService.insertProductOrder(data); 
			}
			response.sendRedirect(request.getContextPath() + "/member/myShopping");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
