package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class ProductCartServlet
 */
@WebServlet("/member/addCart")
public class MemberAddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String data = request.getParameter("data");
			List<Map<String,Object>> list = new Gson().fromJson(data, List.class);

			Member loginMember = (Member) request.getSession().getAttribute("loginMember");
			String memberId = loginMember.getMemberId();

			for(Map<String, Object> cart : list) {
				cart.put("memberId", memberId);
				int result = memberService.insertCart(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
