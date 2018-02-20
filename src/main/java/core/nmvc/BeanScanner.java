package core.nmvc;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import core.annotation.Controller;
import core.annotation.Repository;
import core.annotation.Service;

public class BeanScanner {
	private static final Logger logger = LoggerFactory.getLogger(BeanScanner.class);
	
	private Reflections reflections; 
	
	public BeanScanner(Object... basePackage){
		reflections = new Reflections(basePackage); 
	}
	
	@SuppressWarnings("unchecked")
	public Set<Class<?>> scan(){
		return getTypesAnnotatedWith(Controller.class, Service.class, Repository.class);
	}
	
	@SuppressWarnings("unchecked")
	public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations){
		Set<Class<?>> preInstantiateBeans = Sets.newHashSet();
		for(Class<? extends Annotation> annotation : annotations){
			preInstantiateBeans.addAll(reflections.getTypesAnnotatedWith(annotation));
		}
		return preInstantiateBeans;
	}

}
