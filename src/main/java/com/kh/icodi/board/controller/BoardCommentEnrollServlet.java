package com.kh.icodi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.board.model.dto.Board;
import com.kh.icodi.board.model.dto.BoardComment;
import com.kh.icodi.board.model.dto.CommentLevel;
import com.kh.icodi.board.model.service.BoardService;
import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.notification.model.service.NotificationService;

/**
 * 
 */
@WebServlet("/board/boardCommentEnroll")
public class BoardCommentEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	private NotificationService notificationService = new NotificationService();

	/**
	 * DML
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값
			CommentLevel commentLevel = CommentLevel.valueOf(Integer.parseInt(request.getParameter("commentLevel"))); // "1" -> 정수형 -> COMMENT(ENUM)
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int commentRef = Integer.parseInt(request.getParameter("commentRef"));
			
			BoardComment boardComment = new BoardComment(0, commentLevel, writer, content, boardNo, commentRef, null);
			System.out.println("boardComment = " + boardComment);
			
			//2. 업무로직
			int result = boardService.insertBoardComment(boardComment);
			
			// 댓글 작성시 게시글 작성자에게 알림 보내기
			Board board = boardService.findByNo(boardNo);
			result = notificationService.notifyBoardAnswer(board);
			
			//3. 응답(redirect : DML)
			response.sendRedirect(request.getContextPath() + "/board/boardView?no=" + boardNo);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
