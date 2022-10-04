package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.icodi.common.IcodiMvcUtils;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class memberPasswordUpdateServlet
 */
@WebServlet("/member/memberPasswordUpdate")
public class MemberPasswordUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberPasswordUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			String memberId = request.getParameter("memberId");
			String oldPassword = IcodiMvcUtils.getEncryptedPassword(request.getParameter("oldPassword"), memberId);
			String newPassword = IcodiMvcUtils.getEncryptedPassword(request.getParameter("newPassword"), memberId);
			
			Member member = memberService.findById(memberId);
						
			
			String msg = null;
			int result = 0;
			if(member != null && oldPassword.equals(member.getPassword())) {
				result = memberService.updatePassword(memberId, newPassword);
				
				msg = "비밀번호를 성공적으로 변경했습니다.";
				
				
			}
			else {
				msg = "기존 비밀번호가 일치하지 않습니다.";
				// response.sendRedirect(request.getContextPath() + "/member/memberPasswordUpdate");
				
				Member loginMember = (Member) request.getSession().getAttribute("loginMember");
				loginMember.setPassword(newPassword);
			}	
//			
			request.getSession().setAttribute("msg", msg);
			System.out.println("result = " + result);
			
			
			Map<String, Object> map = new HashMap<>();
			map.put("result", result);
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(map, response.getWriter());
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
