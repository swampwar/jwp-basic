package core.ref;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

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
    public void newInstanceWithConstructorArgs() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<User> clazz = User.class;
        User user = null;
        logger.debug(clazz.getName());
        
    	// User 클래스의 객체를 생성해본다.(기본 생성자 없음)
    	Constructor[] cons = clazz.getDeclaredConstructors();
    	for(Constructor constructor : cons){
    		// 기본생성자가 없기때문에 newInstance()로 생성할 수 없다.
    		user = (User)constructor.newInstance(new Object[]{new String("userId"),new String("pw"),new String("name"),new String("email")});
    	}
    	
    	logger.debug(user.toString());
    }
    
    @Test
    public void privateFieldAccess() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
        
        // 리플렉션 API를 이용하여 private 필드에 접근한다.
        Student student = new Student();
        Field nameField = clazz.getDeclaredField("name");
        Field ageField = clazz.getDeclaredField("age");
        
        nameField.setAccessible(true);
        nameField.set(student, "name_");
        
        ageField.setAccessible(true);
        ageField.set(student, 31);
        
        logger.debug("name : {}, age : {}", student.getName(), student.getAge());
        
    }
}
