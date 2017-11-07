package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return null;
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

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return null;
			}
		};
		
		String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userID = ?";
    	jdbcTemplate.update(sql);
    }

    public List<User> findAll() throws SQLException {
    	JdbcTemplate selectJdbcTemplate = new JdbcTemplate() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
			}
			
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),rs.getString("email"));
				return user;
			}
		};
    	
    	String sql = "SELECT userId, password, name, email FROM USERS WHERE 1=1";
        ArrayList<User> userList = (ArrayList<User>)selectJdbcTemplate.query(sql);
    	
        return userList;
    }

    public User findByUserId(String userId) throws SQLException {
    	JdbcTemplate selectJdbcTemplate = new JdbcTemplate() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}

			@Override
			public Object mapRow(ResultSet rs) throws SQLException{
				User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),rs.getString("email"));
				return user;
			}
		};
		
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		User user = (User)selectJdbcTemplate.queryForObject(sql);
		
		return user;
    }

}
