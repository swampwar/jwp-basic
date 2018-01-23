package core.nmvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.reflections.ReflectionUtils;

import com.google.common.collect.Maps;

import core.annotation.RequestMapping;
import core.annotation.RequestMethod;

public class AnnotationHandlerMapping {
    private Object[] basePackage;

    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
    	// ControllerScanner로 @Controller 클래스 메소드중 @RequestMapping 애노테이션이 붙은 모든 메소드를 찾는다.  
    	ControllerScanner sc = new ControllerScanner();
    	Map<Class<?>, Object> controllers = sc.getControllers();
    	
    	Iterator<Class<?>> iterator = controllers.keySet().iterator();
    	while(iterator.hasNext()){
    		Class<?> clazz = iterator.next();
    		Set<Method> methodSet = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(RequestMapping.class));
    		
    		// HandlerKey : url, requestMethod
    		
    	}
    }

    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
        return handlerExecutions.get(new HandlerKey(requestUri, rm));
    }
}
