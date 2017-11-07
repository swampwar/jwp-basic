package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.exception.DataAccessException;

public class JdbcTemplate {
	
	public void update(String sql,PreparedStatementSetter pss) throws DataAccessException{
        try(Connection con = ConnectionManager.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql)) {
            
        	pss.setValues(pstmt);
            pstmt.executeUpdate();
            
        }catch(SQLException e){
        	throw new DataAccessException(e);
        }
	}
	
	public <T> List<T> query(String sql,PreparedStatementSetter pss, RowMapper<T> rowMapper) throws DataAccessException{
    	ResultSet rs = null;
        List<T> list = new ArrayList<T>();
        try(Connection con = ConnectionManager.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql)) {
            
        	pss.setValues(pstmt);
            rs = pstmt.executeQuery();
            
            // ResultSet을 List로 변환
            while(rs.next()){
            	T obj = rowMapper.mapRow(rs);
            	list.add(obj);
            }
            return list;
            
        }catch(SQLException e){
        	throw new DataAccessException(e);
        }finally {
            if (rs != null) {
                try {
					rs.close();
				} catch (SQLException e) {
					throw new DataAccessException(e);
				}
            }  
        }
	}

	public <T> T queryForObject(String sql,PreparedStatementSetter pss, RowMapper<T> rowMapper) throws DataAccessException{
		List<T> list = query(sql,pss,rowMapper);
		if(list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}

}
