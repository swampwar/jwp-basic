package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.service.QnaService;

public class HomeController extends AbstractController {
    private QnaService qnaService;
    
    public HomeController(QnaService qnaService) {
    	this.qnaService = qnaService;
	}

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return jspView("home.jsp").addObject("questions", qnaService.findAll());
    }
}
