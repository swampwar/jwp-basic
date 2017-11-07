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
	
	public List query(String sql,PreparedStatementSetter pss, RowMapper rowMapper) throws DataAccessException{
    	ResultSet rs = null;
        List<Object> list = new ArrayList<Object>();
        try(Connection con = ConnectionManager.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql)) {
            
        	pss.setValues(pstmt);
            rs = pstmt.executeQuery();
            
            // ResultSet을 List로 변환
            while(rs.next()){
            	Object obj = rowMapper.mapRow(rs);
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

	public Object queryForObject(String sql,PreparedStatementSetter pss, RowMapper rowMapper) throws DataAccessException{
		List list = query(sql,pss,rowMapper);
		if(list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}

}
