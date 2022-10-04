package com.kh.icodi.cscenter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.icodi.common.IcodiMvcUtils;
import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.cscenter.model.dto.CsCenterInquireAnswer;
import com.kh.icodi.cscenter.model.service.CsCenterService;

/**
 * Servlet implementation class CsCenterInquireViewServlet
 */
@WebServlet("/csCenter/inquireView")
public class CsCenterInquireViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CsCenterService csCenterService = new CsCenterService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CsCenterInquireAnswer csCenterInquireAnswer = new CsCenterInquireAnswer();
			
			int InquireNo = Integer.parseInt(request.getParameter("no"));
			CsCenterInquire csCenterInquire = csCenterService.findInquireContentByNo(InquireNo);
			System.out.println(InquireNo);
			
			                                                                                                                                                                                                                                                                                                                                                           
			
			List<CsCenterInquireAnswer> answerList = csCenterService.findInquireAnwerByInquireNo(InquireNo);
			request.setAttribute("answerList", answerList);
			
			
			//xxs 공격대비
			IcodiMvcUtils.escapeXml(csCenterInquireAnswer.getAnswerContent() == null ? "" : csCenterInquireAnswer.getAnswerContent());
			
			// 개행문자 변환 처리
			IcodiMvcUtils.convertLineFeedToBr(csCenterInquireAnswer.getAnswerContent() == null ? ""  : csCenterInquireAnswer.getAnswerContent());
			
			
			request.setAttribute("csCenterInquire", csCenterInquire);
			request.getRequestDispatcher("/WEB-INF/views/cscenter/csCenterInquireView.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
