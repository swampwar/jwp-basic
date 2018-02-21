package core.di.factory;

import java.lang.reflect.Field;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldInjector extends AbstractInjector{
    private static final Logger logger = LoggerFactory.getLogger(FieldInjector.class);

	@Override
	public void inject(Class<?> clazz){
		instantiateClass(clazz);
		Set<Field> injectedFields = BeanFactoryUtils.getInjectedFields(clazz);
		
		for(Field field : injectedFields){
            Class<?> concreteClazz = BeanFactoryUtils.findConcreteClass(field.getDeclaringClass(), beanFactory.getPreInstanticateBeans());
            Object bean = beanFactory.getBean(concreteClazz);
            if(bean == null) {
            	bean = instantiateClass(clazz);
            }
            
            try {
            	field.setAccessible(true);
				field.set(beanFactory.getBean(field.getDeclaringClass()), bean);
			} catch (IllegalArgumentException | IllegalAccessException e) {
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
