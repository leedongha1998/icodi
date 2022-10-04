package com.kh.icodi.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.board.model.dto.Board;
import com.kh.icodi.board.model.service.BoardService;
import com.kh.icodi.common.IcodiMvcUtils;

/**
 * Servlet implementation class MemberMyBoardListServlet
 */
@WebServlet("/member/memberMyBoardList")
public class MemberMyBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String writer = request.getParameter("memberId");
			
			int numPerPage = 10;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {}
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> param = new HashMap<>();
			param.put("writer", writer);
			param.put("start", start);
			param.put("end", end);
			
			List<Board> list = boardService.findAllByMe(param);
			
			int totalContent = boardService.getTotalContentByMe(writer);
			String url = request.getRequestURI() + "?memberId=" + writer;
			String pagebar = IcodiMvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
			
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/member/memberMyBoardList.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
