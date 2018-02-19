package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.annotation.Controller;
import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.service.QnaService;


@Controller
public class ShowQuestionController extends AbstractController {;
    private QnaService qnaService;
    
    public ShowQuestionController(QnaService qnaService){
    	this.qnaService = qnaService;
    }

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        long questionId = Long.parseLong(req.getParameter("questionId"));

        Question question = qnaService.findById(questionId);
        List<Answer> answers = qnaService.findAllByQuestionId(questionId);

        ModelAndView mav = jspView("/qna/show.jsp");
        mav.addObject("question", question);
        mav.addObject("answers", answers);
        return mav;
    }
    
    @RequestMapping(value = "/qna/show", method = RequestMethod.GET)
    public ModelAndView showQuestion(HttpServletRequest req, HttpServletResponse response){
        long questionId = Long.parseLong(req.getParameter("questionId"));

        Question question = qnaService.findById(questionId);
        List<Answer> answers = qnaService.findAllByQuestionId(questionId);

        ModelAndView mav = jspView("/qna/show.jsp");
        mav.addObject("question", question);
        mav.addObject("answers", answers);
        return mav;
    }
}
