package core.ref;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

import next.model.User;

public class Junit4TestRunner {
    @Test
    public void run() throws Exception {
    	// Junit4Test.java의 @MyTest 어노테이션이 붙은 메서드를 실행한다.
        Class<Junit4Test> clazz = Junit4Test.class;
        Method[] methods = clazz.getMethods();
        
        for(Method method : methods){
        	if(method.isAnnotationPresent(MyTest.class)){
        		method.invoke(clazz.newInstance(), null);
        	}
        }
    }
}
