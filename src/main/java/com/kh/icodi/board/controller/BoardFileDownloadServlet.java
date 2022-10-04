package com.kh.icodi.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

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
@WebServlet("/board/fileDownload")
public class BoardFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리 -> attach의 pk
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("no@BoardFileDownloadServlet = " + no);
			
			//2. 업무로직
			//a. attach조회
			Attachment attach = boardService.findAttachmentByNo(no);
			System.out.println("attach@BoardFileDownloadServlet = " + attach);
			
			//b. 파일 입출력 처리
			//입력 - saveDirectory + renamedFilename
			//출력 - http응답 메세지 출력스트림 response.getOutputStream()
			//응답 헤더 contentType application/octet-stream -> 2진데이터일때 사용
			response.setContentType("application/octet-stream");
			
			//한글제목인 파일 다운로드 가능.
			String filename = URLEncoder.encode(attach.getOriginalFilename(), "utf-8");
			response.setHeader("content-Disposition", "attachment;filename=" + filename);
			
			
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			File downFile = new File(saveDirectory, attach.getRenamedFilename());
			
			try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()))
			{
				byte[] buffer = new byte[8192]; //8kb
				int len = 0; // 읽어온 byte 수
				//읽어올게 없으면 -1
				while((len = bis.read(buffer)) != -1) {
					bos.write(buffer, 0, len); // buffer에서 0 ~ len 까지 출력
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
