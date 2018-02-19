package core.nmvc;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import core.mvc.HandlerMapping;

public class AnnotationHandlerMapping implements HandlerMapping{
	private static final Logger logger = LoggerFactory.getLogger(AnnotationHandlerMapping.class);
    private Object[] basePackage;
    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    // ControllerScanner 클래스로  @Controller 클래스 메소드중 @RequestMapping 애노테이션의 모든 메소드를 찾는다.  
    public void initialize() {
    	Map<Class<?>, Object> controllers =  new ControllerScanner(basePackage).getControllers();
    	Iterator<Class<?>> iterator = controllers.keySet().iterator();
    	
    	while(iterator.hasNext()){ // 스캔된 모든 컨트롤러 객체에서
    		Class<?> clazz = iterator.next();
    		Object declaredObject = controllers.get(clazz);
    		
    		// 컨트롤러 객체에서 @RequestMapping 메서드를 추출한다. 
    		Set<Method> methodSet = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(RequestMapping.class));
    		Method[] methods = getRequestMappingMethods(methodSet);
    		
    		// 메소드의 정보를 HandlerKey, HandlerExecution 객체로 생성하여 Map에 저장한다.
    		for(Method method : methods){ 
    		    HandlerKey handlerKey = createHandlerKey(method.getAnnotation(RequestMapping.class));
    		    HandlerExecution handlerExecution = new HandlerExecution(declaredObject, method);
    		    logger.debug("handlerExecution register method : {}, " + handlerKey.toString(), method.getName());
    		    handlerExecutions.put(handlerKey, handlerExecution);
    		}
    	}
    }
  
    // RequestMapping 어노테이션 객체에서 HandlerKey 객체를 생성하여 반환한다.
    public HandlerKey createHandlerKey(RequestMapping rm){
    	return new HandlerKey(rm.value(), rm.method()); // HandlerKey : String url, RequestMethod method
    }
    
    // Set<Method>를 Array로 변환하여 반환한다.
    public Method[] getRequestMappingMethods(Set<Method> methodSet){
    	return methodSet.toArray(new Method[methodSet.size()]);
    }

    // HttpServletRequest 에서 HandlerKey(url, method)를 추출하여 HandlerExecution 객체를 반환한다.
    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
        HandlerKey handlerKey = new HandlerKey(requestUri, rm);
        logger.debug("getHandler : " + handlerKey.toString());
        
        return handlerExecutions.get(handlerKey);
    }
}
