package com.kh.icodi.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.board.model.dto.BoardExt;
import com.kh.icodi.board.model.service.BoardService;
import com.kh.icodi.common.IcodiMvcUtils;

/**
 * Servlet implementation class BoardFinderServlet
 */
@WebServlet("/board/boardFinder")
public class BoardFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리
			int cPage = 1;
			int numPerPage = 10;
			
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {}
			
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchKeyword");

			Map<String, Object> param = new HashMap<>();
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);
			param.put("start", (cPage - 1) * numPerPage + 1);
			param.put("end", cPage * numPerPage);
			System.out.println(param);
			
			List<BoardExt> list = boardService.findLike(param);
			
			int totalContent = boardService.getTotalContentLike(param);
			String url = request.getRequestURI() + "?searchType=" + searchType + "&searchKeyword=" + searchKeyword;
			// /mvc/admin/memberFinder
			String pagebar = IcodiMvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
			
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("searchType", searchType);
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp")
			.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
