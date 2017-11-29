package core.mvc;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements ModelAndView {
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Iterator<String> it = model.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			req.setAttribute(key, getModel(key));
		}
	}
	
	@Override
	public void putModel(String key, Object value){
		model.put(key, value);
	}

	@Override
	public Object getModel(String key) {
		return model.get(key);
	}
	
	@Override
	public boolean isMove() {
		return true;
	}


}
