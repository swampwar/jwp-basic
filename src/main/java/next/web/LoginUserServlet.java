package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LoginUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	HttpSession session = req.getSession();
    	String userId = req.getParameter("userId");
    	String password = req.getParameter("password");
    	
    	log.debug("login parameter : userId={}, password={} ", userId, password);
    	
    	User user = DataBase.findUserById(userId);
    	
    	if(user == null){
    		log.debug("login fail : DataBase User is null");
    	}else if(!user.getPassword().equals(password)){
    		log.debug("login fail : password is not correct");
    	}else{
    		log.debug("login success");
    		session.setAttribute("user", user);
    	}
    	
    	resp.sendRedirect("/user/list");
        
    }
}
