package core.mvc;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ModelAndView {
	HashMap<String, Object> model = new HashMap<>();
	void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	void putModel(String key, Object value);
	Object getModel(String key);
	boolean isMove();
}
