package com.kh.icodi.member.model.dao;


import static com.kh.icodi.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.icodi.admin.model.dto.ProductAttachment;
import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.dto.ProductOrder;
import com.kh.icodi.admin.model.dto.ProductSize;
import com.kh.icodi.common.MemberCartProductManager;
import com.kh.icodi.common.MemberOrderProductManager;
import com.kh.icodi.common.MemberProductManager;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.dto.MemberCart;
import com.kh.icodi.member.model.dto.MemberRole;
import com.kh.icodi.member.model.exception.MemberException;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {	
		String filename = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Member findById(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String sql = prop.getProperty("findById");	
		// select * from member where member_id = ?
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				member = handleMemberResultSet(rset);
			}
			
		} catch (SQLException e) {
			throw new MemberException("회원 아이디 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}
	
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getAddressEx());
			
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new MemberException("회원가입 오류", e);
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	public String findMemberId(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String memberId = null;
		String sql = prop.getProperty("findMemberId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getPhone());
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				memberId = rset.getString("member_id"); 				
			}
		} catch (SQLException e) {
			throw new MemberException ("회원 이름 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberId;
	}


	public String findMemberPw(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String memberPw = null;
		String sql = prop.getProperty("findMemberPw");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPhone());
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				memberPw = rset.getString("member_pwd");
			}
		} catch (SQLException e) {
			throw new MemberException ("회원 비밀번호 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberPw;
	}

	// updateMemberPw = update member set member_pwd = ? where member_id = ?
	public int updateMemberPw(Connection conn, Member member, String newPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMemberPw");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("비밀번호 입시 발급 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Member> findMemberLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		String sql = prop.getProperty("findMemberLike");
		String col = (String) param.get("searchType");
		String val = (String) param.get("searchKeyword");
		int start = (int) param.get("start");
		int end = (int) param.get("end");
		
		sql = sql.replace("#", col);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + val + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next())
				list.add(handleMemberResultSet(rset));
		}
		catch (SQLException e) {
			throw new MemberException("관리자 회원검색 오류!", e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int getTotalContentLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContentLike");
		String col = (String) param.get("searchType");
		String val = (String) param.get("searchKeyword");
		sql = sql.replace("#", col);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + val + "%");
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContent = rset.getInt(1);
		}
		catch (SQLException e) {
			throw new MemberException("관리자 검색된 회원수 조회 오류!", e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}


	public List<Member> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member member = handleMemberResultSet(rset);
				list.add(member);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int getTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContent");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContent = rset.getInt(1); // 컬럼명 or 컬럼인덱스 사용
		} catch (SQLException e) {
			throw new MemberException("전체회원수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}


	public int updateMemberRole(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMemberRole");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberRole().name());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		}
		catch(Exception e) {
			throw new MemberException("회원권한정보 수정 오류!", e);
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMember");
		// update member set email = ?, phone = ?, address = ?, addressEx = ? where member_id = ?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getAddressEx());
			pstmt.setString(5, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원정보수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember"); 

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원 삭제 오류!", e);
		} finally {
			close(pstmt);
		} 
		
		return result;
	}

	public int updatePassword(Connection conn, String memberId, String newPassword) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updatePassword");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
			
		} 
		catch (SQLException e) {
			throw new MemberException("비밀번호 변경 오류!", e);
		} 
		finally {
			close(pstmt);
		}
		
		return result;
	}

	public int checkId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("findById");	
		// select * from member where member_id = ?
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next() || memberId.equals("")) {
				result = 0;
			} else {
				result = 1;
			}
		} catch (SQLException e) {
			throw new MemberException("회원 중복 검사 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	// 장바구니 추가
	// insertCart = insert into cart values (seq_cart_no.nextval, ?, ?, ?)
	public int insertCart(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertCart");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("productCode"));
			pstmt.setString(2, (String)data.get("memberId"));
			pstmt.setInt(3, Integer.parseInt((String)data.get("productCount")));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("장바구니 추가 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 장바구니(주문) 추가 
	// insertCart = insert into cart values (seq_cart_no.nextval, ?, ?, ?)
	public int insertCartBuyItNo(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertCart");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("productCode"));
			pstmt.setString(2, (String)data.get("memberId"));
			pstmt.setInt(3, (int)data.get("productCount"));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("장바구니 추가 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 최근 추가된 장바구니 번호 찾기
	// findCartNoBySeq = select seq_cart_no.currval from dual
	public int findCartNoBySeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int cartNo = 0;
		String sql = prop.getProperty("findCartNoBySeq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				cartNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new MemberException("장바구니 번호 찾기 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return cartNo;
	}

	// 장바구니 번호로 주문내역 가져오기
	// orderCartView = select m.*, p.*, c.* from member m, product p, cart c where m.member_id = ( select member_id from cart where cart_no = ? ) and p.product_code = ( select product_code from cart where cart_no = ? ) and c.cart_no = ?
	public MemberProductManager findOrderListByCartNo(Connection conn, int cartNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MemberProductManager order = null;
		String sql = prop.getProperty("orderCartView");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			pstmt.setInt(2, cartNo);
			pstmt.setInt(3, cartNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				order = handleMemberProductManagerResultSet(rset);
			}
 		} catch (SQLException e) {
 			throw new MemberException("주문내역 가져오기 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return order;
	}
	
	// 상품 코드로 첨부파일 가져오기
	// findAttachmentByProductCode = select * from product_attachment where product_code = ?
	public List<ProductAttachment> findAttachmentByProductCode(Connection conn, String productCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductAttachment> attachments = new ArrayList<>();
		String sql = prop.getProperty("findAttachmentByProductCode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productCode);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				attachments.add(handleProductAttachmentResultSet(rset));
			}
		} catch (SQLException e) {
			throw new MemberException("주문페이지 첨부파일 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attachments;
	}
	
	// 상품 주문 테이블 추가
	// insertProductOrder = insert into product_order values(?, ?, ?, default, ?, ?)
	public int insertProductOrder(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertProductOrder");
		String status = ((String)data.get("payment")).equals("cash") ? "입금대기" : "배송준비중";
		System.out.println("status = " + status);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("orderNo"));
			pstmt.setInt(2, (int)data.get("totalPrice"));
			pstmt.setString(3, (String)data.get("payment"));
			pstmt.setString(4, status);
			pstmt.setInt(5, (int)data.get("cartAmount"));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("주문 테이블 추가 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 방금 추가된 주문 번호 조회
	// findOrderNo = select seq_product_order_no.currval from dual
	public int findOrderNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int orderNo = 0;
		String sql = prop.getProperty("findOrderNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				orderNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new MemberException("주문 번호 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return orderNo;
	}
	
	// 회원_주문 테이블 추가
	// insertMemberOrder = insert into member_order values (?, ?)
	public int insertMemberOrder(Connection conn, Map<String, Object> data, String orderNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMemberOrder");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("memberId"));
			pstmt.setString(2, orderNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원_주문 테이블 추가 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 주문_상품 테이블 추가
	// insertOrderProduct = insert into product_order_product values(?, ?)
	public int insertOrderProduct(Connection conn, Map<String, Object> data, String orderNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertOrderProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			pstmt.setString(2, (String)data.get("productCode"));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("주문_상품 테이블 추가 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 회원 적립금 업데이트
	// updateMemberPoint = update member set member_point = member_point + ? where member_id = ?
	public int updateMemberPoint(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMemberPoint");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)data.get("addPoint"));
			pstmt.setString(2, (String)data.get("memberId"));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원 적립금 업데이트 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 주문한 상품 장바구니에서 삭제
	// deleteOrderCartNo = delete from cart where cart_no = ?
	public int deleteOrderCartNo(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteOrderCartNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)data.get("cartNo"));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("주문상품 장바구니 삭제 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 장바구니 리스트 보여주기
	// findCartListByMemberId = select c.*, p.*, m.* from cart c, product p, member m where c.product_code = p.product_code and c.member_id = ? and m.member_id = ?
	public List<MemberCartProductManager> findCartListByMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		List<MemberCartProductManager> cartList = new ArrayList<>();
		ResultSet rset = null;
		String sql = prop.getProperty("findCartListByMemberId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				cartList.add(handleMemberCartProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new MemberException("장바구니 리스트 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return cartList;
	}
	
	// 장바구니 상품 삭제
	// deleteCart = delete from cart where cart_no = ?
	public int deleteCart(Connection conn, int[] cartNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		int count[] = new int[cartNo.length];
		String sql = prop.getProperty("deleteCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < cartNo.length; i++) {
				pstmt.setInt(1, cartNo[i]);
				pstmt.addBatch();
			}
			count = pstmt.executeBatch();
		} catch (SQLException e) {
			throw new MemberException("장바구니 상품 삭제 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 사용 포인트 차감
	// deleteMemberPointUse = update member set member_point = member_point - ? where member_id = ?
	public int deleteMemberPointUse(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteMemberPointUse");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)data.get("usePoint"));
			pstmt.setString(2, (String)data.get("memberId"));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("사용 포인트 차감", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 주문내역 조회 (3개월 전 - 기본설정)
	// findOrderListByMemberId = select * from product_order o left join member_order m on o.order_no = m.order_no left join product_order_product a on o.order_no = a.order_no left join product p on a.product_code = p.product_code where (to_date(o.order_date, 'YY/MM/DD') between to_date(?, 'YY/MM/DD') and to_date(?, 'YY/MM/DD')) and m.member_id = ?
	public List<MemberOrderProductManager> findOrderListByMemberId(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberOrderProductManager> orderList = new ArrayList<>();
		String sql = prop.getProperty("findOrderListByMemberId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("start"));
			pstmt.setString(2, (String)data.get("end"));
			pstmt.setString(3, (String)data.get("memberId"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				orderList.add(handleOrderProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new MemberException("주문내역 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return orderList;
	}
	
	// 기존 장바구니 조회
	// findCartByProductCode = select * from cart where product_code = ? and member_id = ?
	public MemberCart findCartByProductCode(Connection conn, Map<String, Object> cart) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MemberCart cartList = null;
		String sql = prop.getProperty("findCartByProductCode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)cart.get("productCode"));
			pstmt.setString(2, (String)cart.get("memberId"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				cartList = handleMemberCartResultSet(rset);
			}
		} catch (SQLException e) {
			throw new MemberException("기존 장바구니 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return cartList;
	}
	
	public static MemberOrderProductManager handleOrderProductResultSet(ResultSet rset) throws SQLException {
		MemberOrderProductManager manager = new MemberOrderProductManager();
		ProductExt product = handleProductExtResultSet(rset);
		ProductOrder order = handleProductOrderResultSet(rset);
		manager.setProduct(product);
		manager.setProductOrder(order);
		return manager;
	}
	
	public static MemberOrderProductManager handleMemberOrderProductResultSet(ResultSet rset) throws SQLException {
		MemberOrderProductManager manager = new MemberOrderProductManager();
		Member member = handleMemberResultSet(rset);
		ProductExt product = handleProductExtResultSet(rset);
		ProductOrder order = handleProductOrderResultSet(rset);
		manager.setMember(member);
		manager.setProduct(product);
		manager.setProductOrder(order);
		return manager;
	}

	public static ProductOrder handleProductOrderResultSet(ResultSet rset) throws SQLException {
		ProductOrder order = new ProductOrder();
		order.setOrderNo(rset.getString("order_no"));
		order.setOrderTotalPrice(rset.getInt("order_total_price"));
		order.setOrderPayments(rset.getString("order_payments"));
		order.setOrderDate(rset.getDate("order_date"));
		order.setOrderStatus(rset.getString("order_status"));
		order.setOrderTotalCount(rset.getInt("order_total_count"));
		return order;
	}

	private MemberCartProductManager handleMemberCartProductResultSet(ResultSet rset) throws SQLException {
		MemberCartProductManager manager = new MemberCartProductManager();
		MemberCart cart = handleMemberCartResultSet(rset);
		ProductExt product = handleProductExtResultSet(rset);
		Member member = handleMemberResultSet(rset);
		manager.setCart(cart);
		manager.setProduct(product);
		manager.setMember(member);
		return manager;
	}

	private ProductAttachment handleProductAttachmentResultSet(ResultSet rset) throws SQLException {
		ProductAttachment attachments = new ProductAttachment();
		attachments.setProductAttachNo(rset.getInt("product_attach_no"));
		attachments.setProductCode(rset.getString("product_code"));
		attachments.setProductOriginalFilename(rset.getString("product_original_filename"));
		attachments.setProductRenamedFilename(rset.getString("product_renamed_filename"));
		attachments.setCodiOriginalFilename(rset.getString("codi_original_filename"));
		attachments.setCodiRenamedFilename(rset.getString("codi_renamed_filename"));
		return attachments;
	}

	private MemberProductManager handleMemberProductManagerResultSet(ResultSet rset) throws SQLException {
		MemberProductManager manager = new MemberProductManager();
		Member member = handleMemberResultSet(rset);
		ProductExt product = handleProductExtResultSet(rset);
		MemberCart cart = handleMemberCartResultSet(rset);
		manager.setMember(member);
		manager.setProductExt(product);
		manager.setMemberCart(cart);
		return manager;
	}
	
	public static ProductExt handleProductExtResultSet(ResultSet rset) throws SQLException {
		ProductExt product = new ProductExt();
		product.setProductCode(rset.getString("product_code"));
		product.setCategoryCode(rset.getInt("category_code"));
		product.setProductName(rset.getString("product_name"));
		product.setProductPrice(rset.getInt("product_price"));
		product.setProductRegDate(rset.getTimestamp("product_reg_date"));
		product.setProductStock(rset.getInt("product_stock"));
		product.setProductSize(ProductSize.valueOf(rset.getString("product_size")));
		product.setProductColor(rset.getString("product_color"));
		product.setProductInfo(rset.getString("product_info"));
		product.setProductPluspoint(rset.getDouble("product_pluspoint"));
		return product;
	}
	
	private MemberCart handleMemberCartResultSet(ResultSet rset) throws SQLException {
		MemberCart cart = new MemberCart();
		cart.setCartNo(rset.getInt("cart_no"));
		cart.setProductCode(rset.getString("product_code"));
		cart.setMemberId(rset.getString("member_id"));
		cart.setCartAmount(rset.getInt("cart_amount"));
		return cart;
	}
	
	public static Member handleMemberResultSet(ResultSet rset) throws SQLException {
		String memberId = rset.getString("member_id"); 
		String memberName = rset.getString("member_name"); 
		String password = rset.getString("member_pwd"); 
		String email = rset.getString("member_email"); 
		String phone = rset.getString("member_phone"); 
		Timestamp enrollDate = rset.getTimestamp("member_enroll_date"); 
		MemberRole memberRole = MemberRole.valueOf(rset.getString("member_role")); 
		int point = rset.getInt("member_point"); 
		String address = rset.getString("member_address");
		String addressEx = rset.getString("member_address_extra");
		return new Member(memberId, memberName, password, email, phone, enrollDate, memberRole, point, address, addressEx);
	}
}
