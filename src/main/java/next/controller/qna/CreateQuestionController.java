package next.controller.qna;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

public class CreateQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreateQuestionController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        // 로그인 체크
    	// TODO 세션을 만든적이 없는데 반환되는 세션객체는 뭐지..??
    	HttpSession session = req.getSession(false);
    	if(session == null || session.getAttribute("user") == null){
    		log.debug("세션에 user 속성없음");
    		throw new AccessDeniedException("세션에 user 속성 없음");
    	}
    	
    	// 세션의 name을 글쓴이로 한다.
    	User loginUser = (User) session.getAttribute("user");
    	Question question = new Question(loginUser.getName(),req.getParameter("title"),req.getParameter("contents"));
        log.debug("question : {}",question);
        
        questionDao.insert(question);

        return jsonView().addObject("insertRslt", true);
    }
}
