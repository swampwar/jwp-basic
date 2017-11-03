package next.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.Controller;
import next.controller.RequestMapping;

@WebServlet(name="dispatcher", urlPatterns="/", loadOnStartup=1)
public class DispatcherServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    
    @Override
    public void init() throws ServletException {
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String reqMethod = req.getMethod();
    	StringBuffer requestURLBuffer = req.getRequestURL();
    	String requestURI = req.getRequestURI();
    	String returnURL = "";
    	
    	log.debug("reqMethod : {}", reqMethod);
    	log.debug("requestURL : {}", requestURLBuffer.toString());
    	log.debug("requestURI : {}", requestURI);
    	
    	// controller exceute
        Controller controller = RequestMapping.getController(requestURI);
        if(controller != null){
        	try {
        		returnURL = controller.excecute(req, resp);
			} catch (Exception e) {
				log.error("Exception : {}", e);
				throw new ServletException(e.getMessage());
			}
        }else{
        	log.error("not found controller-url mappping, check url");
        	returnURL = "/index.jsp";
        }
    	
    	// move
        if(returnURL.startsWith(DEFAULT_REDIRECT_PREFIX)){ // sendRedirect
        	String sendRedirectURL = returnURL.substring(DEFAULT_REDIRECT_PREFIX.length()); 
        	log.debug("sendRedirect URL : {}",sendRedirectURL);
        	resp.sendRedirect(sendRedirectURL);
        }else{ // forward
        	RequestDispatcher rd = req.getRequestDispatcher(returnURL);
        	log.debug("foward URL : {}",returnURL);
        	rd.forward(req, resp);
        }

    }
	
}
