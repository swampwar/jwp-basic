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

	public List select(String sql) throws SQLException{

    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<Object> list = new ArrayList<>();

        try {
        	con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            setValues(pstmt);
            
            rs = pstmt.executeQuery();
            
            // ResultSet을 List로 변환
//            
//            if (rs.next()) {
//                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
//                        rs.getString("email"));
//            }
            
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
        
        return list;

	}

	public abstract void setValues(PreparedStatement pstmt) throws SQLException;
	
}
