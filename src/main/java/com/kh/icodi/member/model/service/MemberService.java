package com.kh.icodi.member.model.service;

import static com.kh.icodi.common.JdbcTemplate.close;
import static com.kh.icodi.common.JdbcTemplate.commit;
import static com.kh.icodi.common.JdbcTemplate.getConnection;
import static com.kh.icodi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.icodi.admin.model.dto.ProductAttachment;
import com.kh.icodi.common.MemberCartProductManager;
import com.kh.icodi.common.MemberOrderProductManager;
import com.kh.icodi.common.MemberProductManager;
import com.kh.icodi.member.model.dao.MemberDao;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.dto.MemberCart;
import com.kh.icodi.member.model.exception.MemberException;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	public Member findById(String memberId) {
		Connection conn = getConnection(); // 커넥션 요청
		Member member = memberDao.findById(conn, memberId); // dao 반환
		close(conn);
		return member;
	}
	
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return result;
	}

	public String findMemberId(Member member) {
		Connection conn = getConnection();
		String memberId = memberDao.findMemberId(conn, member);
		close(conn);
		return memberId;
	}

	public String findMemberPw(Member member, String newPwd) {
		Connection conn = getConnection();
		String memberPw = memberDao.findMemberPw(conn, member);
		if(memberPw != null) {
			int result = memberDao.updateMemberPw(conn, member, newPwd);
		}
		close(conn);
		return memberPw;
	}

	public List<Member> findMemberLike(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Member> list = memberDao.findMemberLike(conn, param);
		close(conn);
		return list;
	}

	public int getTotalContentLike(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent = memberDao.getTotalContentLike(conn, param);
		close(conn);
		return totalContent;
	}

	public List<Member> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Member> list = memberDao.findAll(conn, param);
		close(conn);
		return list;
	}

	public int getTotalContent() {
		Connection conn = getConnection();
		int totalContent = memberDao.getTotalContent(conn);
		close(conn);
		return totalContent;
	}

	public int updateMemberRole(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMemberRole(conn, member);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try{
			result = memberDao.deleteMember(conn, memberId);
			if(result == 0)
				throw new MemberException("해당 회원은 존재하지 않습니다.");
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);			
		}
		return result;
	}

	public int updatePassword(String memberId, String newPassword) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updatePassword(conn, memberId, newPassword);
			commit(conn);
		} 
		catch(Exception e) {
			rollback(conn);
			throw e;
		} 
		finally {
			close(conn);
		}
		return result;
	}

	public int checkId(String memberId) {
		Connection conn = getConnection();
		int result = memberDao.checkId(conn, memberId);
		close(conn);
		return result;
	}

	public MemberProductManager buyItNow(Map<String, Object> data) {
		Connection conn = getConnection();
		int cartNo = 0;
		MemberProductManager order = null;
		try {
			int result = memberDao.insertCartBuyItNo(conn, data);
			cartNo = memberDao.findCartNoBySeq(conn);
			order = memberDao.findOrderListByCartNo(conn, cartNo);
						
			String productCode = order.getProductExt().getProductCode();
			List<ProductAttachment> attachments = memberDao.findAttachmentByProductCode(conn, productCode);
			if(attachments != null && !attachments.isEmpty()) {
				for(ProductAttachment attach : attachments) {
					order.getProductExt().addAttachment(attach);
				}
			}
			order.getMemberCart().setCartNo(cartNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return order;
	}

	public int insertCart(Map<String, Object> data) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.insertCart(conn, data);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertProductOrder(Map<String, Object> data) {
		Connection conn = getConnection();
		String orderNo = (String)data.get("orderNo");
		int result = 0;
		try {
			result = memberDao.insertProductOrder(conn, data);
			result = memberDao.insertMemberOrder(conn, data, orderNo);
			result = memberDao.insertOrderProduct(conn, data, orderNo);
			result = memberDao.updateMemberPoint(conn, data);
			result = memberDao.deleteOrderCartNo(conn, data);
			result = (int)data.get("usePoint") != 0 ? memberDao.deleteMemberPointUse(conn, data) : 0;
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<MemberCartProductManager> findCartListByMemberId(String memberId) {
		Connection conn = getConnection();
		List<MemberCartProductManager> cartList = memberDao.findCartListByMemberId(conn, memberId);
		if(cartList != null && !cartList.isEmpty()) {
			for(MemberCartProductManager cart : cartList) {
				List<ProductAttachment> attachment = memberDao.findAttachmentByProductCode(conn, cart.getProduct().getProductCode());
				if(attachment != null && !attachment.isEmpty()) {
					for(ProductAttachment attach : attachment) {
						cart.getProduct().addAttachment(attach);
					}
				}
			}
		}
		close(conn);
		return cartList;
	}

	public int deleteCart(int[] cartNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.deleteCart(conn, cartNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public List<MemberProductManager> findOrderListByCartNo(int[] cartNo) {
		Connection conn = getConnection();
		List<MemberProductManager> order = new ArrayList<>();
		for(int no : cartNo) {
			order.add(memberDao.findOrderListByCartNo(conn, no));
			for(MemberProductManager manager : order) {
				String productCode = manager.getProductExt().getProductCode();
				List<ProductAttachment> attachments = memberDao.findAttachmentByProductCode(conn, productCode);
				if(attachments != null && !attachments.isEmpty()) {
					for(ProductAttachment attach : attachments) {
						manager.getProductExt().addAttachment(attach);
					}
				}
			}
		}
		return order;
	}

	public List<MemberOrderProductManager> findOrderListByMemberId(Map<String, Object> data) {
		Connection conn = getConnection();
		List<MemberOrderProductManager> orderList = memberDao.findOrderListByMemberId(conn, data);
		close(conn);
		return orderList;
	}

	public MemberCart findCartByProductCode(Map<String, Object> cart) {
		Connection conn = getConnection();
		MemberCart cartList = memberDao.findCartByProductCode(conn, cart);
		close(conn);
		return cartList;
	}
}
