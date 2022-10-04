package com.kh.icodi.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.icodi.admin.model.dto.CategoryNo;
import com.kh.icodi.admin.model.dto.ProductAttachment;
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.dto.ProductSize;
import com.kh.icodi.admin.model.service.AdminService;
import com.kh.icodi.common.IcodiMvcFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class AdminProductEnrollServlet
 */
@WebServlet("/admin/productEnroll")
public class AdminProductEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/productEnroll.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 0. 첨부파일처리
			ServletContext application = getServletContext();
			String saveDirecotry = application.getRealPath("/upload/admin");
			int maxPostSize = 1024 * 1024 * 10;
			String encoding = "utf-8";
			FileRenamePolicy policy = new IcodiMvcFileRenamePolicy();
			
			// 저장된 파일 정보 확인
			MultipartRequest multiReq = new MultipartRequest(request, saveDirecotry, maxPostSize, encoding, policy);
			
			String categoryName = multiReq.getParameter("categoryName");
			String productName = multiReq.getParameter("productName");
			int productPrice = Integer.parseInt(multiReq.getParameter("productPrice"));
			String _productSize = multiReq.getParameter("productSize");
			String productColor = multiReq.getParameter("productColor");
			String productInfo = multiReq.getParameter("productInfo");
					
			int categoryNo = categoryName != null ? CategoryNo.stringOf(categoryName) : null;
			ProductSize productSize = _productSize != null ? ProductSize.valueOf(_productSize) : null;
			String productCode = productName + "_" + productSize + "_" + productColor;
	 		
			ProductExt product = new ProductExt(productCode, categoryNo, productName, productPrice, null, 0, productSize, productColor, productInfo, 0.0);
			
			Enumeration<String> filenames = multiReq.getFileNames();
			
			while(filenames.hasMoreElements()) {
				String filename = filenames.nextElement();
				File upFile = multiReq.getFile(filename);
				if(upFile != null) {
					ProductAttachment attach = new ProductAttachment();
					if(filename.equals("codiImage")) {
						attach.setCodiOriginalFilename(multiReq.getOriginalFileName(filename));
						attach.setCodiRenamedFilename(multiReq.getFilesystemName(filename));
						product.addAttachment(attach);
					} else {
						attach.setProductOriginalFilename(multiReq.getOriginalFileName(filename));
						attach.setProductRenamedFilename(multiReq.getFilesystemName(filename));
						product.addAttachment(attach);
					}
				}
			}
			System.out.println("Product = " + product);
			
			int result = adminService.insertProduct(product);
			
			response.sendRedirect(request.getContextPath() + "/admin/adminPage");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
