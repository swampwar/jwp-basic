package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Question;
import next.model.User;

public class QuestionDao {
	
    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO QUESTIONS (writer,title,contents,createdDate,countOfAnswer) VALUES (?, ?, ?, SYSDATE, 0)";
        jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents());
    }

    public Question findByQuestionId(int questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId = ?";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getString("writer"), rs.getString("title"), rs.getString("contents"),
                		rs.getInt("questionId"),rs.getString("createdDate"), rs.getInt("countOfAnswer"));
            }
        };

        return jdbcTemplate.queryForObject(sql, rm, questionId);
    }

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getString("writer"), rs.getString("title"), rs.getString("contents"),
                		rs.getInt("questionId"),rs.getString("createdDate"), rs.getInt("countOfAnswer"));
            }
        };

        return jdbcTemplate.query(sql, rm);
    }

    public void update(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE ANSWERS set title = ?, contents = ? WHERE questionId = ?";
        jdbcTemplate.update(sql, question.getTitle(), question.getContents());
    }

}
