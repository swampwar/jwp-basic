package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class DetailQnaController implements Controller{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String questionIdStr = req.getParameter("questionId");
		int questionId = Integer.parseInt(questionIdStr);
		
		QuestionDao dao = new QuestionDao();
		Question question = dao.findByQuestionId(questionId);
		req.setAttribute("question", question);
		
		AnswerDao answerDao = new AnswerDao();
		List<Answer> answerList = answerDao.findByQuestionId(questionId);
		req.setAttribute("answerList", answerList);
		
		return "/qna/show.jsp";
	}
	
}
