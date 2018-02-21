package core.di.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetterInjector extends AbstractInjector {
    private static final Logger logger = LoggerFactory.getLogger(SetterInjector.class);
	
	@Override
	public void inject(Class<?> clazz){
		instantiateClass(clazz);
		Set<Method> injectedMethods = BeanFactoryUtils.getInjectedMethods(clazz);
		for(Method method : injectedMethods){
			logger.debug("invoke method : {} ", method);
			Class<?>[] paramTypes = method.getParameterTypes();
			if(paramTypes.length != 1){
				throw new IllegalStateException("DI할 메소드 인자는 하나여야 합니다.");
			}
			
			Class<?> concreteClazz = BeanFactoryUtils.findConcreteClass(paramTypes[0], beanFactory.getPreInstanticateBeans());
			Object bean = beanFactory.getBean(concreteClazz);
			if(bean == null) {
				bean = instantiateClass(clazz);
			}
			
			try {
				method.invoke(beanFactory.getBean(method.getDeclaringClass()), bean);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				logger.error(e.getMessage());
			}
		}
		
        
		
		
	}

	@Override
	public Set<?> getInjectedBeans(Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getBeanClass(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inject(Object object1, Object object2, BeanFactory beanFactory) {
		// TODO Auto-generated method stub
		
	}

}
