package com.kh.icodi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.board.model.dto.Board;
import com.kh.icodi.board.model.dto.BoardComment;
import com.kh.icodi.board.model.service.BoardService;


/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * dd
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리
			int no = Integer.parseInt(request.getParameter("no"));
			
			// 읽음 여부 판단
			Cookie[] cookies = request.getCookies();
			String boardCookieVal = "";
			boolean hasRead = false;
			
			if(cookies != null) {
				for(Cookie c : cookies) {
					String name = c.getName();
					String value = c.getValue();
					if("boardCookie".equals(name)) {
						boardCookieVal = value;
						if(value.contains("[" + no + "]")) {
							hasRead = true;
						}
						break;
					}
				}
			}
			
			// 쿠키 처리
			if(!hasRead) {
				Cookie cookie = new Cookie("boardCookie", boardCookieVal + "[" + no + "]");
				cookie.setPath(request.getContextPath() + "/board/boardView");
				cookie.setMaxAge(365 * 24 * 60 * 60); //읽은 내역은 1년간 관리
				response.addCookie(cookie);
				System.out.println("[boardCookie 새로 발금 되었음 : " + cookie.getValue() + "]");
			}
			
			//2. 업무로직
			
			// 게시글 조회 및 조회수 증가 처리
			//hasRead가 true면 읽엇다는 뜻임으로 no만 반환
			//읽지않았을때는 hasRead가 false를 넘겨주고 쿠키가 저장되어서 반환
			Board board = hasRead ? 
					boardService.findByNo(no) :
					boardService.findByNo(no, hasRead);
			
			List<BoardComment> commentList = boardService.findBoardCommentByBoardNo(no);
			
			System.out.println("board = " + board);
			System.out.println("commentList = " + commentList);
			
			//3. view단 처리
			request.setAttribute("board", board);
			request.setAttribute("commentList", commentList);
			request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp")
			.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
