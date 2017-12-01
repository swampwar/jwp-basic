package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

public class ShowController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        
        ModelAndView mv = new ModelAndView(new JspView("/qna/show.jsp"));
        mv.addObject("question", new QuestionDao().findById(questionId));
        mv.addObject("answers", new AnswerDao().findAllByQuestionId(questionId));
        
        return mv;
    }
}
