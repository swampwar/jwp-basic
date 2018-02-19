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
    	Map<Class<?>, Object> controllers = (new ControllerScanner("next.controller")).getControllers();
    	Set<Class<?>> controllerKeySet = controllers.keySet();
    	
    	for(Class<?> keyClazz : controllerKeySet)
    		logger.debug(keyClazz.getName());
	}
}
