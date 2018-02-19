package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.service.QnaService;

public class ApiListQuestionController extends AbstractController {
    private QnaService qnaService;
    
    public ApiListQuestionController(QnaService qnaService){
    	this.qnaService = qnaService;
    }

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return jsonView().addObject("questions", qnaService.findAll());
    }
}
