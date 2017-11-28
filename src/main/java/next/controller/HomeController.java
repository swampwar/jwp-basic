package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

public class HomeController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	QuestionDao questionDao = new QuestionDao();
    	List<Question> questionList = questionDao.findAll();
    	req.setAttribute("questionList", questionList);
    	
        return "home.jsp";
    }
}
