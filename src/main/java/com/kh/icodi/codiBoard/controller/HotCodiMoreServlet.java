package com.kh.icodi.codiBoard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.codiBoard.model.dto.CodiBoardExt;
import com.kh.icodi.codiBoard.model.service.CodiBoardService;
import com.kh.icodi.member.model.dto.Member;

/**
 * Servlet implementation class HotCodiMoreServlet
 */
@WebServlet("/codi/hotCodiMore")
public class HotCodiMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CodiBoardService codiBoardService = new CodiBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cPage = Integer.parseInt(request.getParameter("cPage"));
			int numPerPage = 12;
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			String memberId = loginMember != null ? loginMember.getMemberId() : null;
					
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			param.put("memberId", memberId);
			
			List<CodiBoardExt> codiBoardList = codiBoardService.findHotCodiBoard(param);

			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(codiBoardList, response.getWriter());
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
