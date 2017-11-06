package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import core.mvc.Controller;
import core.mvc.DispatcherServlet;

public class HomeController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setAttribute("users", DataBase.findAll());
        logger.info("HomeController In");
        
        return "home.jsp";
    }
}
