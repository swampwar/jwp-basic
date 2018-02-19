package core.nmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.ModelAndView;

public class ControllerHandlerAdaptor implements HandlerAdaptor{

	@Override
	public boolean supports(Object handler) {
		return handler instanceof Controller;
	}

	@Override
	public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		return ((Controller)handler).execute(req, resp);
	}

}
