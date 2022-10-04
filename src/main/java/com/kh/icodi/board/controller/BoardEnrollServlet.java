package com.kh.icodi.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.board.model.dto.Attachment;
import com.kh.icodi.board.model.dto.BoardExt;
import com.kh.icodi.board.model.service.BoardService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * 
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * GET 게시글 등록폼 요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardEnroll.jsp")
		.forward(request, response);
		}

	/**
	 * POST db에 insert 요청
	 * 
	 * 첨부파일이 포함된 게시글 등록
	 * - 1. 서버 컴퓨터에 파일 저장 -> cos.jar
	 * 		- MultipartRequest객체 생성
	 * 			- HttpServletRequest
	 * 			- saveDirectory
	 * 			- maxPostSize
	 * 			- encoding
	 * 			- FileRenamePolicy객체 - DefaultFileRenamePolicy(기본)
	 * 		* 주의 사항
	 * 		- 기존 request객체가 아닌 MultipartRequest객체에서 모든 사용자 입력값을 가져와야 한다.
	 * 
	 * - 2. 저장된 파일정보 attachment 레코드로 등록
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			//0. 첨부파일 처리
			ServletContext application = getServletContext();
			String saveDirectory = application.getRealPath("/upload/board");
			System.out.println("saveDirectory = " + saveDirectory);
			
			//첨부파일 크기 제한
			int maxPostSize = 1024 * 1024 * 10; // 10MB
			
			String encoding = "utf-8";
			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			
			MultipartRequest multiReq = new MultipartRequest(
					request, saveDirectory, maxPostSize, encoding, policy);
			
			// 저장된 파일
			String originalFilename = multiReq.getOriginalFileName("upFile1");
			String renamedFilename = multiReq.getFilesystemName("upFile1");
			System.out.println("originalFilename = " + originalFilename);
			System.out.println("renamedFielname = " + renamedFilename);
			
			//1. 사용자 입력값 -> 멀티파트를 사용하면 리퀘스트로 파라미터를 못가져옴
			String title = multiReq.getParameter("title");
			String writer = multiReq.getParameter("writer");
			String content = multiReq.getParameter("content");
			
			BoardExt board = new BoardExt(0, title, writer, content, 0, null);
			
			Enumeration<String> filenames = multiReq.getFileNames();
			while(filenames.hasMoreElements()) {
				String filename = filenames.nextElement();
				System.out.println("filename = " + filename);
				File upFile = multiReq.getFile(filename);
				//첨부파일이 있을 때만
				if(upFile != null) {
					Attachment attach = new Attachment();
					attach.setOriginalFilename(multiReq.getOriginalFileName(filename));
					attach.setRenamedFilename(multiReq.getFilesystemName(filename));
					board.addAttachment(attach);
				}
			}
			
			System.out.println("board = " + board);
			
			//2. 업무로직
			int result = boardService.insertBoard(board); 
			
			//3. redirect
			response.sendRedirect(request.getContextPath() + "/board/boardList");
			
		} 
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
