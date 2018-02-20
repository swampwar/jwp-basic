package core.di.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);
    private Set<Class<?>> preInstanticateBeans;
    private Map<Class<?>, Object> beans = Maps.newHashMap();
    public BeanFactory(Set<Class<?>> preInstanticateBeans) {
        this.preInstanticateBeans = preInstanticateBeans;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }

    public void initialize() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	// Map<Class<?>, Object> beans 에 대한 초기화 작업을 한다.
    	for(Class<?> clazz : preInstanticateBeans){
    		if(beans.get(clazz) == null){
    			instantiateClass(clazz);
    		}
    	}
    }
    
    private Object instantiateClass(Class<?> clazz) {
    	// 재귀함수의 시작부분
    	// @Inject 애노테이션이 설정되어 있는 생성자가 존재하면 instantiateConstructor()로 인스턴스 생성, 없으면 기존 생성자로 인스턴스 생성
    	Object bean = beans.get(clazz);
    	if(bean != null){
    		return bean;
    	}
    	
    	Constructor<?> injectedConstructor = BeanFactoryUtils.getInjectedConstructor(clazz);
    	if(injectedConstructor == null){
    		logger.debug("Bean 기본생성자 신규생성 : {}", clazz.getName());
    		bean = BeanUtils.instantiate(clazz);
    		beans.put(clazz, bean);
    		return bean;
    	}
    	
    	bean = instantiateConstructor(injectedConstructor);
    	beans.put(clazz, bean);
    	
    	return bean;
    }
    
    private Object instantiateConstructor(Constructor<?> constructor) {
    	// @Inject 애노테이션이 설정되어 있는 생성자가 존재하면 인스턴스 생성
    	// 생성자의 인자값 인스턴스가 Map에 존재하면 활용, 그렇지 않으면 instantiateClass()을 활용해 생성
    	Class<?>[] parameterTypes = constructor.getParameterTypes();
    	List<Object> args = Lists.newArrayList();
    	
    	for(Class<?> clazz : parameterTypes){
    		Class<?> concreteClazz = BeanFactoryUtils.findConcreteClass(clazz, preInstanticateBeans);
    		if(!preInstanticateBeans.contains(concreteClazz)){
    			throw new IllegalStateException(clazz + "는 Bean이 아니다.");
    		}
    		
    		Object bean = beans.get(concreteClazz);
    		if(bean == null){
    			bean = instantiateClass(concreteClazz);
    		}
    		args.add(bean);
    	}
    	
    	logger.debug("Bean Injected생성자 신규생성 : {}", constructor.getName());
    	return BeanUtils.instantiateClass(constructor, args.toArray());
    }
}
