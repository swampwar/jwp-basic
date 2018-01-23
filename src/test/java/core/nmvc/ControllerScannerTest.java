package core.nmvc;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerScannerTest {
    private static final Logger logger = LoggerFactory.getLogger(ControllerScannerTest.class);
	
    @Test
	public void init(){
    	ControllerScanner controllerScanner = new ControllerScanner();
    	Map<Class<?>, Object> map = controllerScanner.getControllers();
    	
    	Set<Class<?>> set = map.keySet();
		Iterator<?> iterator = (Iterator<?>)set.iterator();
		while(iterator.hasNext()){
			Class<?> clazz = (Class<?>)iterator.next();
			logger.debug(clazz.getName());
			logger.debug(map.get(clazz).toString());
		}
		
	}
}
