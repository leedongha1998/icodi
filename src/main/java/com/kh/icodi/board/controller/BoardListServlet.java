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

import com.kh.icodi.board.model.dto.Board;
import com.kh.icodi.board.model.service.BoardService;
import com.kh.icodi.common.IcodiMvcUtils;


/**
 * 
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	/**
	 * 사용자 입력값 처리 cPage
	 * service요청 - 반환 타입
	 * 예외처리
	 * 포워딩
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			//1. 사용자 입력값
			int numPerPage = 10;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			}catch(NumberFormatException e) {}
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			
			//2. 업무로직
			//a. content영역
			List<Board> list = boardService.findAll(param);
			//System.out.println("list = " + list);
			
			
			//b. pagebar영역
			int totalContent = boardService.getTotalContent();
			String url = request.getRequestURI(); // /mvc/board/boradList
			String pagebar = IcodiMvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
			
			
			//3. view단
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp")
			.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
