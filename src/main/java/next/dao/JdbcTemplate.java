package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;

public class JdbcTemplate {
	
	public void update(String sql,PreparedStatementSetter pss) throws SQLException{
    	Connection con = null;
    	PreparedStatement pstmt = null;
        try {
        	con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setValues(pstmt);
            
            pstmt.executeUpdate();
            
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }

	}
	
	public List query(String sql,PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException{
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
        List<Object> list = new ArrayList<Object>();
        try {
        	con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setValues(pstmt);
            
            rs = pstmt.executeQuery();
            
            // ResultSet을 List로 변환
            while(rs.next()){
            	Object obj = rowMapper.mapRow(rs);
            	list.add(obj);
            }
            return list;
            
        } finally {
            if (rs != null) {
                rs.close();
            }  
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
	}
	
	public Object queryForObject(String sql,PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException{
		List list = query(sql,pss,rowMapper);
		if(list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}

}
