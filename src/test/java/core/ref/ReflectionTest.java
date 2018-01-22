package core.ref;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;

        Field[] declFields = clazz.getDeclaredFields();
        logger.debug("Question Field ############");
        for(Field f : declFields) {
        	logger.debug(f.toString());
        }
        
        Constructor[] constructors = clazz.getConstructors();
        logger.debug("Question Constructor ############");
        for(Constructor c : constructors) {
        	logger.debug(c.toString());
        }
        
        Method[] methods = clazz.getMethods();
        logger.debug("Question Method ############");
        for(Method m : methods) {
        	logger.debug(m.toString());
        }
        
        logger.debug(clazz.getName());
    }
    
    @Test
    public void newInstanceWithConstructorArgs() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
    }
    
    @Test
    public void privateFieldAccess() {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
    }
}
