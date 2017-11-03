package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String excecute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
