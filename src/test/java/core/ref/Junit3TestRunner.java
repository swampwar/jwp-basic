package core.ref;

import java.lang.reflect.Method;

import org.junit.Test;

public class Junit3TestRunner {
    @Test
    public void run() throws Exception {
//    	Junit3Test 의 test로 시작하는 모든 메소드를 실행한다.
        Class<Junit3Test> clazz = Junit3Test.class;
        
        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
        	String methodName = method.getName();
        	if(methodName.startsWith("test")) {
        		method.invoke(clazz.newInstance());
        	}
        }
    }
}
