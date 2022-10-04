package com.kh.icodi.codiBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.service.AdminService;
import com.kh.icodi.codiBoard.model.dto.LikeThat;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.service.MemberService;

/**
 * Servlet implementation class CodiBoardDetailServlet
 */
@WebServlet("/codiBoard/codiDetail")
public class CodiBoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String useProduct = request.getParameter("useProduct");
			int codiBoardNo = Integer.parseInt(request.getParameter("codiBoardNo"));
			int likeCount = Integer.parseInt(request.getParameter("likeCount"));
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			String memberId = loginMember != null ? loginMember.getMemberId() : null;
			
			Map<String, Object> data = new HashMap<>();
			data.put("memberId", memberId);
			data.put("codiBoardNo", codiBoardNo);
			LikeThat liked = memberId != null ? adminService.findLikedByCodiBoardNo(data) : null;

			String codiImg = adminService.getCodiImg(codiBoardNo);
			List<ArrayList<ProductExt>> list = new ArrayList<ArrayList<ProductExt>>();
			String arr[] = useProduct.split(", ");
			
			for(String product : arr) {
				int underBar = product.indexOf("_");
				String productName = product.substring(0, underBar);
				List<ProductExt> productList = adminService.findProductByProductName(productName);
				list.add((ArrayList<ProductExt>) productList);
			}
			
			request.setAttribute("list", list);
			request.setAttribute("codiImg", codiImg);
			request.setAttribute("likeCount", likeCount);
			request.setAttribute("liked", liked);
			request.setAttribute("codiBoardNo", codiBoardNo);
			request.setAttribute("loginMemberId", memberId);
			request.getRequestDispatcher("/WEB-INF/views/codiBoard/codiDetail.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
