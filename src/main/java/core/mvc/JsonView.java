package core.mvc;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonView implements View{
	
	@Override
	public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setContentType("application/json;charset=UTF-8");

		ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(model));
	}
}
