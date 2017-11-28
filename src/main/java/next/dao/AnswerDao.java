package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;

public class AnswerDao {
	
    public void insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS (writer,contents,createdDate,questionId) VALUES (?, ?, SYSDATE, ?)";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getQuestionId());
    }

    public List<Answer> findByQuestionId(int questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE questionId = ?";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getInt("answerId"), rs.getString("writer"), rs.getString("contents"), rs.getString("createdDate"), rs.getInt("questionId"));
            }
        };

        return jdbcTemplate.query(sql, rm, questionId);
    }

    public List<Answer> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getInt("answerId"), rs.getString("writer"), rs.getString("contents"), rs.getString("createdDate"), rs.getInt("questionId"));
            }
        };

        return jdbcTemplate.query(sql, rm);
    }

    public void update(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE ANSWERS set contents = ? WHERE answerId = ?";
        jdbcTemplate.update(sql, answer.getContents());
    }

}
