package core.mvc;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import next.model.Result;

public class JsonView implements ModelAndView{
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setContentType("application/json;charset=UTF-8");

		ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(model));
	}
	
	@Override
	public void putModel(String key, Object value){
		model.put(key, value);
	}
	
	@Override
	public Object getModel(String key){
		return model.get(key);
	}

	@Override
	public boolean isMove() {
		return false;
	}

}
