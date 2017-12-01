package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;

public class HomeController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        ModelAndView mv = new ModelAndView(new JspView("home.jsp"));
        mv.addObject("questions", new QuestionDao().findAll());
        
        return mv;
    }
}
