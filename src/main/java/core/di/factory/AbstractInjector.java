package core.di.factory;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

public abstract class AbstractInjector implements Injector{
    private static final Logger logger = LoggerFactory.getLogger(AbstractInjector.class);
    protected BeanFactory beanFactory;
    
    public abstract Set<?> getInjectedBeans(Class<?> clazz);
    
    public abstract Class<?> getBeanClass(Object object);
    
    public abstract void inject(Object object1, Object object2, BeanFactory beanFactory);

    @Override
    public void inject(Class<?> clazz) {
    	// 구현
    	
    	
    	
    }
    
    protected Object instantiateClass(Class<?> clazz) {
        Object bean = beanFactory.getBean(clazz);
        if (bean != null) {
            return bean;
        }

        Constructor<?> injectedConstructor = BeanFactoryUtils.getInjectedConstructor(clazz);
        if (injectedConstructor == null) {
            bean = BeanUtils.instantiate(clazz);
            beanFactory.registerBean(clazz, bean);
            return bean;
        }

        logger.debug("Constructor : {}", injectedConstructor);
        bean = instantiateConstructor(injectedConstructor);
        beanFactory.registerBean(clazz, bean);
        return bean;
    }

    protected Object instantiateConstructor(Constructor<?> constructor) {
        Class<?>[] pTypes = constructor.getParameterTypes();
        List<Object> args = Lists.newArrayList();
        for (Class<?> clazz : pTypes) {
            Class<?> concreteClazz = BeanFactoryUtils.findConcreteClass(clazz, beanFactory.getPreInstanticateBeans());
            if (!beanFactory.getPreInstanticateBeans().contains(concreteClazz)) {
                throw new IllegalStateException(clazz + "는 Bean이 아니다.");
            }

            Object bean = beanFactory.getBean(concreteClazz);
            if (bean == null) {
                bean = instantiateClass(concreteClazz);
            }
            args.add(bean);
        }
        return BeanUtils.instantiateClass(constructor, args.toArray());
    }
    
    private Class<?> findBeanClass(Class<?> clazz, Set<Class<?>> clazzSet){
    	// 구현
    	return null;
    }
}
