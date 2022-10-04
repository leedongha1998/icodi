package com.kh.icodi.cscenter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.kh.icodi.cscenter.model.dto.Alarm;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.notification.model.service.NotificationService;

/**
 * Servlet implementation class AlarmServlet
 */
@WebServlet("/alarm")
public class AlarmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationService notificationService= new NotificationService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			
			String memberId = request.getParameter("alarmMemberId");
			
			List<Alarm> alarmList = notificationService.findAlarmById(memberId);
			
			
			System.out.println("지금확인alarmList = " + alarmList);
			request.setAttribute("alarmList", alarmList);
			
			request.getRequestDispatcher("/WEB-INF/views/alarm/alarm.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	

}
