package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/list")
public class ListUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ListUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	/*
    	 * 세션으로 로그인 여부를 판단하여 로그인이 된 사용자만 사용자 리스트를 보여준다.
    	 */
    	HttpSession session = req.getSession();
    	User user = (User)session.getAttribute("user");
    	String returnUrl = null;
    	
    	if(user == null) { // 비로그인
    		log.debug("need login, goto login.jsp");
    		returnUrl = "/user/login.jsp";
    	}else { // 로그인
    		req.setAttribute("users", DataBase.findAll());
    		returnUrl = "/user/list.jsp";
    	}
    	
    	RequestDispatcher rd = req.getRequestDispatcher(returnUrl);
        rd.forward(req, resp);
    }
}
