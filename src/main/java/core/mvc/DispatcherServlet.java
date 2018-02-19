package core.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.nmvc.AnnotationHandlerMapping;
import core.nmvc.ControllerHandlerAdaptor;
import core.nmvc.HandlerAdaptor;
import core.nmvc.HandlerExecutionHandlerAdaptor;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> mappings = new ArrayList<>();
    private List<HandlerAdaptor> handlerAdaptors = new ArrayList<>();
    
    @Override
    public void init() throws ServletException {
    	LegacyHandlerMapping legacyHandlerMapping = new LegacyHandlerMapping();
        legacyHandlerMapping.initMapping();
        mappings.add(legacyHandlerMapping);
        
        AnnotationHandlerMapping annotationHandlerMapping = new AnnotationHandlerMapping("next.controller");
        annotationHandlerMapping.initialize();
        mappings.add(annotationHandlerMapping);
        
        handlerAdaptors.add(new ControllerHandlerAdaptor());
        handlerAdaptors.add(new HandlerExecutionHandlerAdaptor());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);

        Object handler = getHandler(req);
        if(handler == null){
        	throw new IllegalArgumentException("존재하지 않는 URL");
        }
	        
	    try {
	        ModelAndView mav = execute(handler, req, resp);
            View view = mav.getView();
            view.render(mav.getModel(), req, resp);
        } catch (Throwable e) {
            logger.error("Exception : {}", e);
            throw new ServletException(e.getMessage());
        }
    }
    
    private Object getHandler(HttpServletRequest req){
    	for(HandlerMapping handlerMapping : mappings){
    		Object handler = handlerMapping.getHandler(req);
    		if(handler != null){
    			return handler;
    		}
    	}
    	return null;
    }
    
    private ModelAndView execute(Object handler, HttpServletRequest req, HttpServletResponse resp) throws Exception{
    	ModelAndView mav = null;
    	for(HandlerAdaptor ha : handlerAdaptors){
    		if(ha.supports(handler))
    			mav = ha.handle(req, resp, handler);
    	}
    	return mav;
    }
}
