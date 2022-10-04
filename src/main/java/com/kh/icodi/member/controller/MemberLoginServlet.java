package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.icodi.common.IcodiMvcUtils;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;
import com.kh.icodi.stats.model.dto.Stats;
import com.kh.icodi.stats.model.exception.StatsService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/memberLogin")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private StatsService statsService = new StatsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp")
			.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 인코딩처리
			request.setCharacterEncoding("utf-8");
			
			// 2. 사용자입력값 처리
			String memberId = request.getParameter("memberId");
			String password = IcodiMvcUtils.getEncryptedPassword(request.getParameter("password"), memberId);
			String saveId = request.getParameter("saveId");
			System.out.println("memberId= " + memberId);
			System.out.println("password= " + password);
			
			// 3. 업무로직 : 로그인여부 판단
			Member member = memberService.findById(memberId); //  디비 요청
			System.out.println("member@MemberLoginServlet =" + member);
			
			HttpSession session = request.getSession(true); // 세션이 존재하지 않으면, 새로 생성해서 반환. true생략가능
			
			// 로그인 성공
			if(member != null && password.equals(member.getPassword())) {
				session.setAttribute("loginMember", member);
				
				// saveId 처리
				Cookie cookie = new Cookie("saveId", memberId);
				cookie.setPath(request.getContextPath()); // /icodi -> /icodi로 시작하는 요청주소에 cookie를 함께 전송
				
				// 방문자 통계조회 시작
				Stats stats = new Stats(memberId, null, 0);
				int result = statsService.insertVisitMember(stats);
				// 방문자 통계조회 끝
				
				if(saveId != null) {
					cookie.setMaxAge(7 * 24 * 60 * 60); // 7일 유지
				}
				else {
					cookie.setMaxAge(0); // 즉시 삭제
				}
				
				response.addCookie(cookie); // 응답메세지에 set-cookie항목으로 전송

				response.sendRedirect(request.getContextPath()+"/"); // /icodi/
			}
			// 로그인 실패 (아이디 존재하지 않는 경우 || 비밀번호를 틀린 경우)
			else {
				session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
				response.sendRedirect(request.getContextPath()+"/member/memberLogin"); // /mvc/member/memberLogin
			}
			
			// 4. 응답 처리 : 로그인후 url변경을 위해 리다이렉트처리
			// 응답 302 redirect 전송.
			// 브라우져에게 location으로 재요청을 명령.
			
		} catch (Exception e) {
			e.printStackTrace(); // 로깅
			throw e; // tomcat에게 예외 던짐.
		}
		
	
	}
}