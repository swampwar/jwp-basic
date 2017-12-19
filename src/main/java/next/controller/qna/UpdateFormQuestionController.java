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

public class UpdateFormQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UpdateFormQuestionController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
    	Long questionId = Long.parseLong(req.getParameter("questionId"));
    	Question originQ = questionDao.findById(questionId);
    	log.debug("questionId : {}",Long.toString(questionId));
    	
    	// 로그인 체크 && 사용자ID 체크
    	HttpSession session = req.getSession(false);
    	if(session == null || session.getAttribute("user") == null){
    		log.debug("세션에 user 속성없음");
    		throw new AccessDeniedException("세션에 user 속성 없음");
    	}else{
    		User user = (User)session.getAttribute("user");
    		String ssUserId = user.getUserId();
    		if(!originQ.equals(ssUserId)){
    			log.debug("세션에 userId와 Qeustion userId가 다름");
    			throw new AccessDeniedException("세션에 userId와 Qeustion userId가 다름");
    		}
    	}
    	
    	// 질문폼으로 이동
        return jspView("/qna/form");
    }
}
