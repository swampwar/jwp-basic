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
    	InsertJDBCTemplate jdbcTemplate = new InsertJDBCTemplate();
    	jdbcTemplate.Insert(user, this);
    }
    
    public void update(User user) throws SQLException {
    	UpdateJDBCTemplate jdbcTemplate = new UpdateJDBCTemplate();
    	jdbcTemplate.update(user, this);
    }
    
    public String createQueryForInsert(){
    	String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    	return sql;
    }
    
    public String createQueryForUpdate(){
    	String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userID = ?";
    	return sql;
    }
    
    public void setValuesForInsert(User user, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, user.getUserId());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
    }
    
    public void setValuesForUpdate(User user, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, user.getPassword());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getUserId());
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
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
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
    
    
}
