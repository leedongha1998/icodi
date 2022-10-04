package com.kh.icodi.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.icodi.admin.model.dto.ProductAttachment;
import com.kh.icodi.admin.model.service.AdminService;

/**
 * Servlet implementation class AdminProductDeleteServlet
 */
@WebServlet("/admin/productDel")
public class AdminProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] pdCode = request.getParameterValues("pdCode");

			for (String code : pdCode) {
				List<ProductAttachment> attachments = adminService.findAttachmentByProductCode(code);
				if (attachments != null && !attachments.isEmpty()) {
					String saveDirectory = getServletContext().getRealPath("/upload/admin");
					for (ProductAttachment attach : attachments) {
						String productRenamedFilename = attach.getProductRenamedFilename();
						String codiRenamedFilename = attach.getCodiRenamedFilename();
						if(productRenamedFilename != null) {
							File delProductFile = new File(saveDirectory, productRenamedFilename);
							delProductFile.delete();							
						}
						if(codiRenamedFilename != null) {
							File delCodiFile = new File(saveDirectory, codiRenamedFilename);
							delCodiFile.delete();							
						}
					}
				}
			}			
			boolean result = adminService.deleteProduct(pdCode);
			response.sendRedirect(request.getHeader("Referer"));
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
