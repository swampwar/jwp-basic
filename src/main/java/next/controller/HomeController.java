package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;

public class HomeController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Override
	public String excecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String returnURL = "index.jsp";
        request.setAttribute("users", DataBase.findAll());
        
        log.debug("completed !");
        return returnURL;
	}
}
