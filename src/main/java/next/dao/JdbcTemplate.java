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
	
	public void update(String sql, Object... parameters) throws DataAccessException{
        try(Connection con = ConnectionManager.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql)) {
            
        	for(int idx=0;idx<parameters.length;idx++){
        		pstmt.setObject(idx+1, parameters[idx]);
        	}
            pstmt.executeUpdate();
            
        }catch(SQLException e){
        	throw new DataAccessException(e);
        }
	}
	
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException{
    	ResultSet rs = null;
        List<T> list = new ArrayList<T>();
        try(Connection con = ConnectionManager.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql)) {
            
        	if(parameters != null){
        		for(int idx=0;idx<parameters.length;idx++){
        			pstmt.setObject(idx+1, parameters[idx]);
        		}
        	}
        	
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

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException{
		List<T> list = query(sql, rowMapper, parameters);
		if(list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}

}
