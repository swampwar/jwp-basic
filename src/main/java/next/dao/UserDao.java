package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
	
    public void insert(User user) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate() {
    		
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
		        pstmt.setString(1, user.getUserId());
		        pstmt.setString(2, user.getPassword());
		        pstmt.setString(3, user.getName());
		        pstmt.setString(4, user.getEmail());
			}
		};
		
    	String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    	jdbcTemplate.update(sql);
    }
    
    public void update(User user) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
		        pstmt.setString(1, user.getPassword());
		        pstmt.setString(2, user.getName());
		        pstmt.setString(3, user.getEmail());
		        pstmt.setString(4, user.getUserId());
			}
		};
		
		String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userID = ?";
    	jdbcTemplate.update(sql);
    }

    public List<User> findAll() throws SQLException {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	String sql = "SELECT userId, password, name, email FROM USERS WHERE 1=1";
        List<User> userList = new ArrayList<User>();
    	
    	try{
    		con = ConnectionManager.getConnection();
    		pstmt = con.prepareStatement(sql);
    		ResultSet rs = pstmt.executeQuery();
    		
    		while(rs.next()){
    			userList.add(new User(rs.getString("userId"),rs.getString("password"),rs.getString("name"),rs.getString("email")));
    		}
    		
    	}finally{
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
    	}
    	
        return userList;
    }

    public User findByUserId(String userId) throws SQLException {
    	
    	SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		
		ArrayList<User> userList = new ArrayList<>();
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		userList = (ArrayList<User>) selectJdbcTemplate.select(sql);
		
		return userList.get(0);
        
    }

}
