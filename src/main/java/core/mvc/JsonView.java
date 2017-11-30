package core.mvc;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonView implements View{
	
	@Override
	public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setContentType("application/json;charset=UTF-8");

		ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(createModel(req)));
	}
	
	private HashMap<String,Object> createModel(HttpServletRequest req){
		Enumeration<String> names = req.getAttributeNames();
		HashMap<String,Object> model = new HashMap<>();

		while(names.hasMoreElements()){
			String name = names.nextElement();
			model.put(name, req.getAttribute(name));
		}

		return model;
	}
}
