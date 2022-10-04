package com.kh.icodi.codiBook.model.service;

import static com.kh.icodi.common.JdbcTemplate.*;
import static com.kh.icodi.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.icodi.admin.model.dto.ProductAttachment;
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.codiBook.model.dao.CodiBookDao;

public class CodiBookService {
	private CodiBookDao codiBookDao = new CodiBookDao();

	public List<ProductExt> findAllByCategoryNo(int categoryNo) {
		Connection conn = getConnection();
		List<ProductExt> products = codiBookDao.findAllByCategoryNo(conn, categoryNo);
		if(products != null && !products.isEmpty()) {
			for(ProductExt product : products) {
				List<ProductAttachment> attachments = codiBookDao.findAttachmentByProductCode(conn, product.getProductCode());
				product.setAttachmentList(attachments);
			}
		}
		close(conn);
		return products;
	}

	public int insertCodi(Map<String, Object> param) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = codiBookDao.insertCodi(conn, param);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}
}
