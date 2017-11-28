package next.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;
import core.mvc.DispatcherServlet;
import next.model.Question;
import next.model.User;

public class QuestionDaoTest {
    private static final Logger log = LoggerFactory.getLogger(QuestionDaoTest.class);

    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

//    @Test
//    public void crud() throws Exception {
//        User expected = new User("userId", "password", "name", "javajigi@email.com");
//        UserDao userDao = new UserDao();
//        userDao.insert(expected);
//        User actual = userDao.findByUserId(expected.getUserId());
//        assertEquals(expected, actual);
//
//        expected.update(new User("userId", "password2", "name2", "sanjigi@email.com"));
//        userDao.update(expected);
//        actual = userDao.findByUserId(expected.getUserId());
//        assertEquals(expected, actual);
//    }

    @Test
    public void findAll() throws Exception {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();
        for(Question q : questions){
        	log.debug(q.toString());
        }
        
        assertEquals(8, questions.size());
    }
    
    @Test
    public void create() throws Exception {
        QuestionDao questionDao = new QuestionDao();
        Question q = new Question("신규유저", "신규타이틀", "신규내용");
        questionDao.insert(q);
        
        Question p = new Question("신규유저", "신규타이틀", "신규내용");
        questionDao.insert(p);
    }
}