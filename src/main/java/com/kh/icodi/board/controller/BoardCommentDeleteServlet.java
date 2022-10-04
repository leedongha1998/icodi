package com.kh.icodi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1. 사용자 입력값
			int no = Integer.parseInt(request.getParameter("no"));
			
			//2. 업무로직
			int result = boardService.deleteComment(no);
			
			//3. redirect (DML)
			response.sendRedirect(request.getHeader("Referer"));
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
			
	}

}
