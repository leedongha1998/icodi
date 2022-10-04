package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.dto.MemberCart;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberFindCartByProductCodeServlet
 */
@WebServlet("/member/findCart")
public class MemberFindCartByProductCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String data = request.getParameter("data");
			List<Map<String, Object>> list = new Gson().fromJson(data, List.class);
			Member loginMember = (Member) request.getSession().getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			List<MemberCart> cartList = new ArrayList<>();
			for(Map<String, Object> cart : list) {
				cart.put("memberId", memberId);
				 cartList.add(memberService.findCartByProductCode(cart));
			}
			response.setContentType("application/json; chartset=utf-8");
			new Gson().toJson(cartList, response.getWriter());
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
