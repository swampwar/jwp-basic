package core.di.factory;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import core.annotation.Controller;

public class BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);
    
    private Set<Class<?>> preInstanticateBeans;
    private Map<Class<?>, Object> beans = Maps.newHashMap();
    private List<Injector> injectors;
    
    public BeanFactory(Set<Class<?>> preInstanticateBeans) {
        this.preInstanticateBeans = preInstanticateBeans;
        
        injectors.add(new ConstructorInjector()); // 생성자를 이용한 DI
    }
    
    public Set<Class<?>> getPreInstanticateBeans(){
    	return this.preInstanticateBeans;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }
    
    public void registerBean(Class<?> clazz, Object object){
    	beans.put(clazz, object);
    }

    public void initialize() {
        for (Class<?> clazz : preInstanticateBeans) {
            if (getBean(clazz) == null) {
                logger.debug("instantiated Class : {}", clazz);
                // 빈 생성
                inject(clazz);
            }
        }
    }
    
    public void inject(Class<?> clazz){
    	/*
    	 *  3가지 방법으로 DI 실행
    	 */
    	
    	injectors.get(0).inject(clazz);
    }
    
    public Map<Class<?>, Object> getControllers() {
        Map<Class<?>, Object> controllers = Maps.newHashMap();
        for (Class<?> clazz : preInstanticateBeans) {
            if (clazz.isAnnotationPresent(Controller.class)) {
                controllers.put(clazz, beans.get(clazz));
            }
        }
        return controllers;
    }
    
    public void clear(){
    	
    }
}
