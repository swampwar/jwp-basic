package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

public class ListQuestionApiController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(ListQuestionApiController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
    	
    	List<Question> qList = questionDao.findAll();
        log.debug("question List : {}",qList);
        
        return jsonView().addObject("qList", qList);
    }
}
