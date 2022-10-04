package com.kh.icodi.admin.model.dao;

import static com.kh.icodi.common.JdbcTemplate.close;
import static com.kh.icodi.member.model.dao.MemberDao.handleMemberOrderProductResultSet;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.icodi.admin.model.dto.Product;
import com.kh.icodi.admin.model.dto.ProductAttachment;
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.dto.ProductIO;
import com.kh.icodi.admin.model.dto.ProductSize;
import com.kh.icodi.admin.model.exception.AdminException;
import com.kh.icodi.codiBoard.model.dto.LikeThat;
import com.kh.icodi.common.MemberOrderProductManager;

public class AdminDao {
	private Properties prop = new Properties();
	
	public AdminDao() {
		String filename = AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// insertProduct = insert into product 
	// (product_code, category_code, product_name, product_price, product_size, product_color, product_info) values(?, ?, ?, ?, ?, ?, ?)
	public int insertProduct(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProductCode());
			pstmt.setInt(2, product.getCategoryCode());
			pstmt.setString(3, product.getProductName());
			pstmt.setInt(4, product.getProductPrice());
			pstmt.setString(5, product.getProductSize().name());
			pstmt.setString(6, product.getProductColor());
			pstmt.setString(7, product.getProductInfo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AdminException("상품 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 상품 입출고 관리
	// insertIO = insert into product_io values (seq_product_io_no.nextval, ?, ?, ?)
	public int insertIO(Connection conn, ProductIO productIo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertIO");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productIo.getProductCode());
			pstmt.setString(2, productIo.getIoStatus());
			pstmt.setInt(3, productIo.getIoCount());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AdminException("상품 입출고 관리 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 상품 이미지 추가
	// insert into product_attachment values (seq_product_attachment_no.nextval, ?, ?, ?, ?, ?)
	public int insertAttachment(Connection conn, ProductAttachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attach.getProductCode());
			pstmt.setString(2, attach.getProductOriginalFilename());
			pstmt.setString(3, attach.getProductRenamedFilename());
			pstmt.setString(4, attach.getCodiOriginalFilename());
			pstmt.setString(5, attach.getCodiRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AdminException("상품 첨부파일 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	// findAttachmentByProductCode = select * from product_attachment where product_code=?
	public List<ProductAttachment> findAttachmentByProductCode(Connection conn, String pdCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductAttachment> attachments = new ArrayList<>();
		String sql = prop.getProperty("findAttachmentByProductCode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pdCode);
			rset = pstmt.executeQuery();
			while(rset.next())
				attachments.add(handleAttachmentResultSet(rset));
		}
		catch (SQLException e) {
			throw new AdminException("게시글별 첨부파일 조회 오류", e);
		}
		finally {
			close(rset);
			close(pstmt);			
		}
		
		return attachments;
	}

	private ProductAttachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		ProductAttachment attach = new ProductAttachment();
		attach.setProductAttachNo(rset.getInt("product_attach_no"));
		attach.setProductCode(rset.getString("product_code"));
		attach.setProductOriginalFilename(rset.getString("product_original_filename"));
		attach.setProductRenamedFilename(rset.getString("product_renamed_filename"));
		attach.setCodiOriginalFilename(rset.getString("codi_original_filename")); 
		attach.setCodiRenamedFilename(rset.getString("codi_renamed_filename"));
		return attach;
	}

	// deleteProduct = delete from product where product_code = ?
	public boolean deleteProduct(Connection conn, String[] pdCode) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteProduct");
		int count[] = new int[pdCode.length];
		
		
		try {
			pstmt = conn.prepareStatement(sql);

			for(int i = 0; i<pdCode.length; i++) {
				pstmt.setString(1, pdCode[i]);
				pstmt.addBatch();
			}
			count = pstmt.executeBatch();
		}
		catch (SQLException e) {
			throw new AdminException("상품 삭제 오류!", e);
		}
		finally {
			close(pstmt);
		}
		
		boolean result = true;
		
		for(int i = 0; i<count.length; i++) {
			System.out.println("count = " + count[i]);
			if(count[i] == -3) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	// getTotalContentByCategoryNo = select count(*) from product where category_code = ?
	public int getTotalContentByCategoryNo(Connection conn, int categoryNo){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContentByCategoryNo");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			rset = pstmt.executeQuery();
			while(rset.next()) 
				totalContent = rset.getInt(1);
			
		} catch (SQLException e) {
			throw new AdminException("상품수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public List<ProductExt> findProductList(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("findProductList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)param.get("categoryNo"));
			pstmt.setInt(2, (int)param.get("start"));
			pstmt.setInt(3, (int)param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				productList.add(handleProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new AdminException("상품 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}
	
	// 상품코드로 상품 찾기 (상품상세페이지)
	// findProductByProductName = select * from product where product_name = ?
	public List<ProductExt> findProductByProductName(Connection conn, String productName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("findProductByProductName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				productList.add(handleProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new AdminException("상품상세페이지 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}
	
	private ProductExt handleProductResultSet(ResultSet rset) throws SQLException {
		ProductExt productExt = new ProductExt();
		productExt.setProductCode(rset.getString("product_code"));
		productExt.setCategoryCode(rset.getInt("category_code"));
		productExt.setProductName(rset.getString("product_name"));
		productExt.setProductPrice(rset.getInt("product_price"));
		productExt.setProductRegDate(rset.getTimestamp("product_reg_date"));
		productExt.setProductStock(rset.getInt("product_stock"));
		productExt.setProductSize(ProductSize.valueOf(rset.getString("product_size")));
		productExt.setProductColor(rset.getString("product_color"));
		productExt.setProductInfo(rset.getString("product_info"));
		productExt.setProductPluspoint(rset.getDouble("product_pluspoint"));
		return productExt;
	}

	public List<String> findProductAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> list = new ArrayList<>();
		String sql = prop.getProperty("findProductAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(rset.getString("product_name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int getTotalContentBySearchKeyword(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContentBySearchKeyword");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) 
				totalContent = rset.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public List<ProductExt> findProductLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("findProductLikeList");
		//select p.* from ( select row_number() over (order by product_reg_date) rnum, 
		//p.* from product p where product_name like ?) p where rnum between ? and ?
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + (String) param.get("searchKeyword") + "%");
			pstmt.setInt(2, (int)param.get("start"));
			pstmt.setInt(3, (int)param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				productList.add(handleProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new AdminException("상품 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}

	public List<ProductAttachment> findAttachmentLike(Connection conn, String productCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductAttachment> attachments = new ArrayList<>();
		String sql = prop.getProperty("findAttachmentByProductCode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productCode);
			rset = pstmt.executeQuery();
			while(rset.next())
				attachments.add(handleAttachmentResultSet(rset));
		}
		catch (SQLException e) {
			throw new AdminException("게시글별 첨부파일 조회 오류", e);
		}
		finally {
			close(rset);
			close(pstmt);			
		}
		
		return attachments;
	}

	public List<ProductExt> newProduct(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("newProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();
			while(rset.next()) {
				productList.add(handleProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new AdminException("상품 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}
	
	// findProductCodeList = select product_code from product
	public List<String> findProductCodeList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> list = new ArrayList<>();
		String sql = prop.getProperty("findProductCodeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(rset.getString("product_code"));
			}
		} catch (SQLException e) {
			throw new AdminException("상품코드 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//select * from codi_board where codi_board_no = ?
	public String getCodiImg(Connection conn, int codiBoardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String codiImg = null;
		String sql = prop.getProperty("getCodiImg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, codiBoardNo);

			rset = pstmt.executeQuery();
			while(rset.next()) {
				codiImg = rset.getString("filename");
			}
		} catch (SQLException e) {
			throw new AdminException("코디이미지 불러오기 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return codiImg;
	}
	public List<ProductExt> mainProductByCategoryNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("mainProductByCategoryNo");
		//select * from (select rownum rnum, p.* from (select * from product where category_code = ? 
		//order by product_reg_date desc) p ) p where rnum between 1 and 10
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rset = pstmt.executeQuery();
			while(rset.next()) {
				productList.add(handleProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new AdminException("상품 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}
	
	// 주문 상품 재고 삭제
	// deleteOrderProductStock = insert into product_io values (seq_product_io_no.nextval, ?, 'O', ?)
	public int deleteOrderProductStock(Connection conn, Map<String, Object> product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteOrderProductStock");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)product.get("productCode"));
			pstmt.setInt(2, Integer.parseInt((String)product.get("productAmount")));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AdminException("주문 상품 재고 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 주문 리스트 조회하기
	// findOrderListByOrderStatus = select a.* from ( select rownum rnum, a.* from ( select * from product_order o join member_order m on o.order_no = m.order_no join product_order_product a on o.order_no = a.order_no join product p on a.product_code = p.product_code join member b on m.member_id = b.member_id ) a) a where a.order_status = ? and rnum between ? and ? order by a.order_date
	public List<MemberOrderProductManager> findOrderListByOrderStatus(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberOrderProductManager> list = new ArrayList<>();
		String sql = prop.getProperty("findOrderListByOrderStatus");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("status"));
			pstmt.setInt(2, (int)data.get("start"));
			pstmt.setInt(3, (int)data.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(handleMemberOrderProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new AdminException("관리자 주문 상태 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 주문 테이블 개수 조회
	// getTotalContentOrderList = select count(*) from product_order where order_status = ?
	public int getTotalContentByOrderStatus(Connection conn, String status) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContentOrderList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new AdminException("상품 개수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
	
	// 주문 상태 변경
	// updateOrderStatus = update product_order set order_status = ? where order_no = ?
	public int updateOrderStatus(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateOrderStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("updateStatus"));
			pstmt.setString(2, (String)data.get("orderNo"));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AdminException("주문 상태 변경 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 배송을 포함한 리스트
	// findOrderListDeliveryContains = select a.* from ( select rownum rnum, a.* from ( select * from product_order o join member_order m on o.order_no = m.order_no join product_order_product a on o.order_no = a.order_no join product p on a.product_code = p.product_code join member b on m.member_id = b.member_id ) a) a where a.rnum between ? and ? and a.order_status like ? order by a.order_date
	public List<MemberOrderProductManager> findOrderListDeliveryContains(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberOrderProductManager> list = new ArrayList<>();
		String sql = prop.getProperty("findOrderListDeliveryContains");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)data.get("start"));
			pstmt.setInt(2, (int)data.get("end"));
			pstmt.setString(3, "%배송%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(handleMemberOrderProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new AdminException("관리자 주문 상태 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 배송 포함한 리스트 개수 조회
	// getTotalContentDeliveryContains = select count(*) from product_order where order_status like ?
	public int getTotalContentDeliveryContains(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContentDeliveryContains");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%배송%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new AdminException("상품 개수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	// 모든 회원 주문 리스트 조회
	// findAllOrderList = select a.* from ( select p.*, a.*, b.*, row_number() over (order by p.order_date desc) rnum from product_order p, member_order m, product_order_product o, product a, member b where m.order_no = o.order_no and o.product_code = a.product_code and m.member_id = b.member_id) a where a.rnum between ? and ?
	public List<MemberOrderProductManager> findAllOrderList(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		List<MemberOrderProductManager> list = new ArrayList<>();
		ResultSet rset = null;
		String sql = prop.getProperty("findAllOrderList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)data.get("start"));
			pstmt.setInt(2, (int)data.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(handleMemberOrderProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new AdminException("모든 회원 주문리스트 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 모든 회원 주문리스트 개수 조회
	// getTotalContentAllOrderList = select count(*) from product_order
	public int getTotalContentAllOrderList(Connection conn) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String sql = prop.getProperty("getTotalContentAllOrderList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new AdminException("모든 회원 주문리스트 개수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
	
	// 키워드로 주문리스트 조회
	// findOrderListBySearchKeyword = select a.* from ( select rownum rnum, a.* from ( select * from product_order o join member_order m on o.order_no = m.order_no join product_order_product a on o.order_no = a.order_no join product p on a.product_code = p.product_code join member b on m.member_id = b.member_id where m.% like ? ) a ) a where a.rnum between ? and ? order by a.order_date
	public List<MemberOrderProductManager> findOrderListBySearchKeyword(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		List<MemberOrderProductManager> list = new ArrayList<>();
		ResultSet rset = null;
		String sql = prop.getProperty("findOrderListBySearchKeyword");
		sql = sql.replace("%", (String)data.get("searchKeyword"));

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + (String)data.get("searchValue") + "%");
			pstmt.setInt(2, (int)data.get("start"));
			pstmt.setInt(3, (int)data.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(handleMemberOrderProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new AdminException("키워드 주문리스트 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
		
	// 키워드 주문리스트 개수 조회
	// getTotalContentBySearchKeyword = select count(*) from product_order o join member_order m on o.order_no = m.order_no where m.% like ?
	public int getTotalContentBySearchKeyword(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String sql = prop.getProperty("getTotalContentBySearchKeyword");
		sql = sql.replace("%", (String)data.get("searchKeyword"));

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + (String)data.get("searchValue") + "%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt(1);
			}
		} catch(Exception e) {
			throw new AdminException("키워드 주문리스트 개수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	// 좋아요 여부 확인
	// findLikedByCodiBoardNo = select * from like_that where member_id = ? and codi_board_no = ?
	public LikeThat findLikedByCodiBoardNo(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		LikeThat liked = null;
		String sql = prop.getProperty("findLikedByCodiBoardNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("memberId"));
			pstmt.setInt(2, (int)data.get("codiBoardNo"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				liked = handleLikeThatResultSet(rset);
			}
		} catch (SQLException e) {
			throw new AdminException("좋아요 여부 확인 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return liked;
	}

	private LikeThat handleLikeThatResultSet(ResultSet rset) throws SQLException {
		LikeThat likeThat = new LikeThat();
		likeThat.setLikeNo(rset.getInt("like_no"));
		likeThat.setMemberId(rset.getString("member_id"));
		likeThat.setCodiBoardNo(rset.getInt("codi_board_no"));
		return likeThat;
	}
}
