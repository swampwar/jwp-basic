package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UpdateJDBCTemplate {
	
	public void update(User user, UserDao dao) throws SQLException{

    	Connection con = null;
    	PreparedStatement pstmt = null;

        try {
        	con = ConnectionManager.getConnection();
        	String sql = dao.createQueryForUpdate();
            pstmt = con.prepareStatement(sql);
            dao.setValuesForUpdate(user, pstmt);
            
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
	
	
}
