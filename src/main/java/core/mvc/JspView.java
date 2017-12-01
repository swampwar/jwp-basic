package core.mvc;

import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements View {
	private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
	private String viewName;
	
	public JspView(String viewName){
		if(viewName == null){
			throw new NullPointerException("viewName is Null.");
		}
		this.viewName = viewName;
	}
	
	@Override
	public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            resp.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }
        
        Set<String> keySet = model.keySet();
        for(String key : keySet){
        	req.setAttribute(key, model.get(key));
        }

        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, resp);
	}
}
