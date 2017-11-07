package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public abstract class SelectJdbcTemplate {

	public List query(String sql) throws SQLException{
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
        List<Object> list = new ArrayList<Object>();

        try {
        	con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            setValues(pstmt);
            
            rs = pstmt.executeQuery();
            
            // ResultSet을 List로 변환
            while(rs.next()){
            	Object obj = mapRow(rs);
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
	
	public Object queryForObject(String sql) throws SQLException{
		List list = query(sql);
		if(list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}

	public abstract void setValues(PreparedStatement pstmt) throws SQLException;
	public abstract Object mapRow(ResultSet rs) throws SQLException;
	
}
