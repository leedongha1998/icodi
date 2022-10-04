package com.kh.icodi.autoComplete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class AutoCompleteServlet
 */
@WebServlet("/productNameList")
public class AutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();
	
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값처리
			String term = request.getParameter("term");
			System.out.println("term = " + term);
			
			//2. 업무로직 : term을 포함하는 단어들을 다시 리스트로 만들어줌
			List<String> resultList = new ArrayList<>();
			List<String> list = adminService.findProducAll();
	
			for(String product : list) {
				if(product.contains(term)) {
					resultList.add(product);
				}
			}
			
			System.out.println("resultList = " + resultList);
			
			//3. 응답처리
			response.setContentType("text/csv; charset=utf-8");
			PrintWriter out = response.getWriter();
			for(int i = 0; i < resultList.size(); i++) {
				out.print(resultList.get(i));
				if(i != resultList.size() - 1) {
					out.print(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
