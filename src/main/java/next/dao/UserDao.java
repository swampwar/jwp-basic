package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user) {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	jdbcTemplate.update(sql,user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
    }
    
    public void update(User user) {
		String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userID = ?";
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	jdbcTemplate.update(sql,user.getPassword(),user.getName(),user.getEmail(),user.getUserId());
    }

    public List<User> findAll() {
    	RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),rs.getString("email"));
				return user;
			}
		};

		String sql = "SELECT userId, password, name, email FROM USERS WHERE 1=1";
    	JdbcTemplate selectJdbcTemplate = new JdbcTemplate();
        List<User> userList = selectJdbcTemplate.<User>query(sql,rowMapper,null);
        
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
		User user = selectJdbcTemplate.queryForObject(sql, rowMapper, userId);
		
		return user;
    }

}
