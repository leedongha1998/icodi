package com.kh.icodi.codiBoard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.codiBoard.model.dto.LikeThat;
import com.kh.icodi.codiBoard.model.service.CodiBoardService;

/**
 * Servlet implementation class LikeThatServlet
 */
@WebServlet("/codiBoard/likeUpdate")
public class LikeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CodiBoardService codiBoardService = new CodiBoardService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codiBoardNo = Integer.parseInt(request.getParameter("codiBoardNo"));
			String loginMemberId = request.getParameter("loginMemberId");
			String type = null;
			int likeCount = 0;
			
			Map<String, Object> data = new HashMap<>();
			data.put("loginMemberId", loginMemberId);
			data.put("codiBoardNo", codiBoardNo);
			
			// 좋아요 유무 체크 
			LikeThat liked = codiBoardService.checkLiked(data);
			
			if(liked != null) {
				likeCount = codiBoardService.deleteLike(data);
				type = "delete";
			} else {
				likeCount = codiBoardService.insertLike(data);
				type = "insert";
			}
			data.put("likeCount", likeCount);
			data.put("type", type);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(data, response.getWriter());
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
