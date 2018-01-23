package core.nmvc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.annotation.Controller;

public class ControllerScanner {
	private static final Logger logger = LoggerFactory.getLogger(ControllerScanner.class);
	public Reflections reflections = new Reflections("core.nmvc");
	Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);
	
	public Map<Class<?>, Object> getControllers(){
		Map<Class<?>, Object> controllers = new HashMap<>();
		
		Iterator<?> iterator = annotated.iterator();
		while(iterator.hasNext()){
			Class<?> clazz = (Class<?>) iterator.next();
			try {
				controllers.put(clazz, clazz.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return controllers;
	}
	
	public void instantiateControllers(){
		
	}

}
