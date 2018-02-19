package core.nmvc;

import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import core.annotation.Controller;

public class ControllerScanner {
	private static final Logger logger = LoggerFactory.getLogger(ControllerScanner.class);
	public Reflections reflections;
	
	public ControllerScanner(Object... basePackage){
		reflections = new Reflections(basePackage);
	}
	
	public Map<Class<?>, Object> getControllers(){
		Set<Class<?>> preInitiatedControllers = reflections.getTypesAnnotatedWith(Controller.class);
		Map<Class<?>, Object> controllers = instantiateControllers(preInitiatedControllers);
		logger.debug("controllers scan success !");
		return controllers;
	}
	
	public Map<Class<?>, Object> instantiateControllers(Set<Class<?>> preInitiatedControllers){
		Map<Class<?>, Object> controllers = Maps.newHashMap();
		try {
			for(Class<?> clazz : preInitiatedControllers)
				controllers.put(clazz, clazz.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(e.getMessage());
		}
		return controllers;
	}

}
