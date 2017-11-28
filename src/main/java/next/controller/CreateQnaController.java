package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

public class CreateQnaController implements Controller{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String writer = req.getParameter("writer");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		Question question = new Question(writer, title, contents);
		
		QuestionDao dao = new QuestionDao();
		dao.insert(question);
		
		return "redirect:/";
	}
	
}
