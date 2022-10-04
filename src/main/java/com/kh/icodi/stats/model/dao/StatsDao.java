package com.kh.icodi.stats.model.dao;

import static com.kh.icodi.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.icodi.admin.model.exception.AdminException;
import com.kh.icodi.stats.model.dto.Stats;

public class StatsDao {
	private Properties prop =  new Properties();
	
	public StatsDao() {
		String filename = StatsDao.class.getResource("/sql/stats/stat-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertVisitMember(Connection conn, Stats stats) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertVisitMember");
		LocalDate now = LocalDate.now();
		String myDateFormat = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		try {
			pstmt = conn.prepareStatement(sql);
//			insert into stat values(?,?)
			pstmt.setString(1, stats.getVisitMemberId());
			pstmt.setString(2, myDateFormat);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AdminException("방문자수 조회 오류!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Stats> findVisitStats(Connection conn, String searchWhen) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Stats> list = new ArrayList<>();
		String sql = prop.getProperty("findVisitStats");
		
		//select distinct (visit_member_id) from stat where to_char(visit_date, 'YYYY-MM-DD') = ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWhen);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String visitMemberId = rset.getString("visit_member_id");
				Stats stats = new Stats(visitMemberId, null, 0);
				list.add(stats);
			}
		} catch (SQLException e) {
			throw new AdminException("날짜별 방문자수 조회 오류!");
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}
