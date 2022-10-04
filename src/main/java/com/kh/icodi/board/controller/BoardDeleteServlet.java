package com.kh.icodi.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.board.model.dto.Attachment;
import com.kh.icodi.board.model.service.BoardService;


/**
 * 
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * 0. 저장된 첨부파일 조회 및 삭제
	 * - {@link java.io.File#delete()}
	 * 
	 * 1. board 레코드 삭제
	 * - (on delete cascade에 의해서 attachment 레코드도 자동으로 연쇄 삭제)
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("no= " + no);
			
			//2. 업무로직
			//a. 저장된 첨부파일 조회 및 삭제
			List<Attachment> attachments = boardService.findAttachmentByBoardNo(no);
			System.out.println("attachments =" + attachments);
			if(attachments != null && !attachments.isEmpty()) {
				String saveDirectory = getServletContext().getRealPath("/upload/board");
				for(Attachment attach : attachments) {
					File delFile = new File(saveDirectory, attach.getRenamedFilename());
					boolean hasDeleted = delFile.delete();
					System.out.println("[파일삭제 " + attach.getRenamedFilename() + " : " + hasDeleted + "]");
				}
			}
			
			//b. board 삭제
			int result = boardService.deleteBoard(no);
			
			//3. 리다이렉트
			request.setAttribute("msg", "게시글을 성공적으로 삭제했습니다");
			response.sendRedirect(request.getContextPath() + "/board/boardList");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
