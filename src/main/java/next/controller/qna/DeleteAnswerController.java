package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;

public class DeleteAnswerController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);
	
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	String answerId = req.getParameter("answerId");
    	log.debug("answerId : {}",answerId);
    	
    	AnswerDao answerDao = new AnswerDao(); 
    	Result rslt = answerDao.delete(answerId);
    	
    	// jackson : 자바클래스 <-> JSON 데이터 변경시 사용되는 라이브러리
    	ObjectMapper mapper = new ObjectMapper(); 
    	String rsltJsonData = mapper.writeValueAsString(rslt);
        log.debug("rsltJsonData : {}",rsltJsonData);
    	
    	resp.setContentType("application/json;charset=UTF-8");
    	PrintWriter out = resp.getWriter();
    	out.print(rsltJsonData);
    	
        return null; // 페이지이동 없음
    }
}
