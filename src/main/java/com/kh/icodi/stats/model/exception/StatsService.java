package com.kh.icodi.stats.model.exception;

import static com.kh.icodi.common.JdbcTemplate.close;
import static com.kh.icodi.common.JdbcTemplate.commit;
import static com.kh.icodi.common.JdbcTemplate.getConnection;
import static com.kh.icodi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.icodi.stats.model.dao.StatsDao;
import com.kh.icodi.stats.model.dto.Stats;

public class StatsService {
	private StatsDao statsDao = new StatsDao();
	
	public int insertVisitMember(Stats stats) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = statsDao.insertVisitMember(conn,stats);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return result;
	}

	public List<Stats> findVisitStats(String searchWhen) {
		Connection conn = getConnection();
		List<Stats> list = statsDao.findVisitStats(conn,searchWhen);
		close(conn);
		return list;
	}



}
