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
import next.model.User;

public class UserDaoTest {
    private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);

    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void crud() throws Exception {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        UserDao userDao = new UserDao();
        userDao.insert(expected);
        User actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);
        
        log.debug("insert actual user : " + actual.toString());

        expected.update(new User("userId", "password2", "name2", "janjigi2@email.com"));
        userDao.update(expected);
        actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);
        
        log.debug("update actual user : " + actual.toString());
    }

    @Test
    public void findAll() throws Exception {
        UserDao userDao = new UserDao();
        
        User expected = new User("userIdy", "passwordy", "namey", "javajigi@email.com");
        userDao.insert(expected);
        
        List<User> users = userDao.findAll();
        assertEquals(2, users.size());
        
        int idx = 0;
        for(User tmp : users){
        	log.debug("findAll actual user{} : {}",idx,tmp.toString());
        	idx++;
        }
    }
}