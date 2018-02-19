package core.nmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.ModelAndView;

public class HandlerExecutionHandlerAdaptor implements HandlerAdaptor{

	@Override
	public boolean supports(Object handler) {
		return handler instanceof HandlerExecution;
	}

	@Override
	public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		return ((HandlerExecution)handler).handle(req, resp);
	}

}
