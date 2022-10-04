package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberCartDeleteServlet
 */
@WebServlet("/member/cartDelete")
public class MemberCartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] _cartNo = request.getParameterValues("delCartNo");
			int[] cartNo = new int[_cartNo.length];
			for(int i = 0; i < _cartNo.length; i++) {
				cartNo[i] = Integer.parseInt(_cartNo[i]);
			}
			int result = memberService.deleteCart(cartNo);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
