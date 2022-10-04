package com.kh.icodi.admin.model.service;

import static com.kh.icodi.common.JdbcTemplate.close;
import static com.kh.icodi.common.JdbcTemplate.commit;
import static com.kh.icodi.common.JdbcTemplate.getConnection;
import static com.kh.icodi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.icodi.admin.model.dao.AdminDao;
import com.kh.icodi.admin.model.dto.Product;
import com.kh.icodi.admin.model.dto.ProductAttachment;
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.dto.ProductIO;
import com.kh.icodi.codiBoard.model.dto.LikeThat;
import com.kh.icodi.common.MemberOrderProductManager;

public class AdminService {
	private AdminDao adminDao = new AdminDao();

	// 상품등록
	public int insertProduct(Product product) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 상품 테이블 insert
			result = adminDao.insertProduct(conn, product);
			
			// 첨부파일 테이블 insert
			List<ProductAttachment> attachments = ((ProductExt)product).getAttachmentList();
			if(attachments != null && !attachments.isEmpty()) {
				for(ProductAttachment attach : attachments) {
					attach.setProductCode(product.getProductCode());
					result = adminDao.insertAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	// 상품 입출고 관리
	public int insertIO(ProductIO productIo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = adminDao.insertIO(conn, productIo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<ProductAttachment> findAttachmentByProductCode(String code) {
		Connection conn = getConnection();
		List<ProductAttachment> attachments = adminDao.findAttachmentByProductCode(conn, code);
		close(conn);
		return attachments;
	}

	public boolean deleteProduct(String[] pdCode) {
		Connection conn = getConnection();
		boolean result = true;
		try {
			result = adminDao.deleteProduct(conn, pdCode);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);			
		}
		return result;
	}

	public int getTotalContentByCategoryNo(int categoryNo) {
		Connection conn = getConnection();
		int totalContent = adminDao.getTotalContentByCategoryNo(conn, categoryNo);
		close(conn);
		return totalContent;
	}

	public List<ProductExt> findProductList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<ProductExt> productList = adminDao.findProductList(conn, param);

		if(productList != null && !productList.isEmpty()) {
			for(ProductExt product : productList) {
				List<ProductAttachment> attachments = adminDao.findAttachmentByProductCode(conn, product.getProductCode());
				if(attachments != null && !attachments.isEmpty()) {
					for(ProductAttachment attach : attachments) {
						product.addAttachment(attach);
					}
				}
			}
		}
		return productList;
	}

	public List<ProductExt> findProductByProductName(String productName) {
		Connection conn = getConnection();
		List<ProductExt> productList = adminDao.findProductByProductName(conn, productName);
		if(productList != null && !productList.isEmpty()) {
			for(ProductExt product : productList) {
				List<ProductAttachment> attachments = adminDao.findAttachmentByProductCode(conn, product.getProductCode());
				if(attachments != null && !attachments.isEmpty()) {
					for(ProductAttachment attach : attachments) {
						product.addAttachment(attach);
					}
				}
			}
		}
		return productList;
	}

	public List<String> findProducAll() {
		Connection conn = getConnection();
		List<String> list = adminDao.findProductAll(conn);
		close(conn);
		return list;
	}

	public int getTotalContentBySearchKeyword(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = adminDao.getTotalContentBySearchKeyword(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public List<ProductExt> findProductLike(Map<String, Object> param) {
		Connection conn = getConnection();
		List<ProductExt> productList = adminDao.findProductLike(conn, param);
		if(productList != null && !productList.isEmpty()) {
			for(ProductExt product : productList) {
				List<ProductAttachment> attachments = adminDao.findAttachmentLike(conn, product.getProductCode());
				if(attachments != null && !attachments.isEmpty()) {
					for(ProductAttachment attach : attachments) {
						product.addAttachment(attach);
					}
				}
			}
		}
		return productList;
	}

	public List<ProductExt> newProduct() {
		Connection conn = getConnection();
		List<ProductExt> productList = adminDao.newProduct(conn);
		if(productList != null && !productList.isEmpty()) {
			for(ProductExt product : productList) {
				List<ProductAttachment> attachments = adminDao.findAttachmentByProductCode(conn, product.getProductCode());
				if(attachments != null && !attachments.isEmpty()) {
					for(ProductAttachment attach : attachments) {
						product.addAttachment(attach);
					}
				}
			}
		}
		return productList;
	}


	public List<String> findProductCodeList() {
		Connection conn = getConnection();
		List<String> list = adminDao.findProductCodeList(conn);
		
		close(conn);
		return list;
	}

	public String getCodiImg(int codiBoardNo) {
		Connection conn = getConnection();
		String codiImg = adminDao.getCodiImg(conn, codiBoardNo);
		close(conn);
		return codiImg;
	}

	public List<ProductExt> mainProductByCategoryNo(int no) {
		Connection conn = getConnection();
		List<ProductExt> productList = adminDao.mainProductByCategoryNo(conn, no);
		if(productList != null && !productList.isEmpty()) {
			for(ProductExt product : productList) {
				List<ProductAttachment> attachments = adminDao.findAttachmentByProductCode(conn, product.getProductCode());
				if(attachments != null && !attachments.isEmpty()) {
					for(ProductAttachment attach : attachments) {
						product.addAttachment(attach);
					}
				}
			}
		}
		return productList;
	}
	
	public int deleteOrderProductStock(List<Map<String, Object>> list) {
		Connection conn = getConnection();
		int result = 0;
		try {
			for(Map<String, Object> product : list) {
				result = adminDao.deleteOrderProductStock(conn, product); 
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	public List<MemberOrderProductManager> findOrderListByOrderStatus(Map<String, Object> data) {
		Connection conn = getConnection();
		List<MemberOrderProductManager> list = adminDao.findOrderListByOrderStatus(conn, data);
		close(conn);
		return list;
	}

	public int getTotalContentByOrderStatus(String status) {
		Connection conn = getConnection();
		int totalContent = adminDao.getTotalContentByOrderStatus(conn, status);
		close(conn);
		return totalContent;
	}

	public int updateOrderStatus(Map<String, Object> data) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = adminDao.updateOrderStatus(conn, data);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<MemberOrderProductManager> findOrderListDeliveryContains(Map<String, Object> data) {
		Connection conn = getConnection();
		List<MemberOrderProductManager> list = adminDao.findOrderListDeliveryContains(conn, data);
		close(conn);
		return list;
	}

	public int getTotalContentDeliveryContains() {
		Connection conn = getConnection();
		int totalContent = adminDao.getTotalContentDeliveryContains(conn);
		close(conn);
		return totalContent;
	}

	public List<MemberOrderProductManager> findAllOrderList(Map<String, Object> data) {
		Connection conn = getConnection();
		List<MemberOrderProductManager> list = adminDao.findAllOrderList(conn, data);
		close(conn);
		return list;
	}

	public int getTotalContentAllOrderList() {
		Connection conn = getConnection();
		int totalContent = adminDao.getTotalContentAllOrderList(conn);
		close(conn);
		return totalContent;
	}

	public List<MemberOrderProductManager> findOrderListBySearchKeyword(Map<String, Object> data) {
		Connection conn = getConnection();
		List<MemberOrderProductManager> list = adminDao.findOrderListBySearchKeyword(conn, data);
		close(conn);
		return list;
	}

	public int getTotalContentBySearchKeyword(Map<String, Object> data) {
		Connection conn = getConnection();
		int totalContent = adminDao.getTotalContentBySearchKeyword(conn, data);
		close(conn);
		return totalContent;
	}

	public LikeThat findLikedByCodiBoardNo(Map<String, Object> data) {
		Connection conn = getConnection();
		LikeThat liked = adminDao.findLikedByCodiBoardNo(conn, data);
		close(conn);
		return liked;
	}

}
