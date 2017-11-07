package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user) {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
		        pstmt.setString(1, user.getUserId());
		        pstmt.setString(2, user.getPassword());
		        pstmt.setString(3, user.getName());
		        pstmt.setString(4, user.getEmail());
			}
		};
    	
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	jdbcTemplate.update(sql,pss);
    }
    
    public void update(User user) {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
		        pstmt.setString(1, user.getPassword());
		        pstmt.setString(2, user.getName());
		        pstmt.setString(3, user.getEmail());
		        pstmt.setString(4, user.getUserId());
			}
		};
		
		String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userID = ?";
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	jdbcTemplate.update(sql,pss);
    }

    public List<User> findAll() {
    	RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),rs.getString("email"));
				return user;
			}
		};
		
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
			}
		};
    	
		String sql = "SELECT userId, password, name, email FROM USERS WHERE 1=1";
    	JdbcTemplate selectJdbcTemplate = new JdbcTemplate();
        List<User> userList = selectJdbcTemplate.<User>query(sql,pss,rowMapper);
        
        return userList;
    }

    public User findByUserId(String userId) {
    	RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),rs.getString("email"));
				return user;
			}
		};
		
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
    	JdbcTemplate selectJdbcTemplate = new JdbcTemplate();
		User user = selectJdbcTemplate.queryForObject(sql,pss,rowMapper);
		
		return user;
    }

}
