package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

public class LoginController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    
	@Override
	public String excecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnURL = "";
		
		String requestURL = request.getRequestURI();
		if(requestURL.startsWith("/users/loginForm")){
			
			log.debug("loginForm 오픈");
			returnURL = "/user/login.jsp";
		
		}else if(requestURL.startsWith("/users/login")){
			
			log.debug("login 실행");
	        String userId = request.getParameter("userId");
	        String password = request.getParameter("password");
	        User user = DataBase.findUserById(userId);
	        
	        if (user == null) {
	        	
	        	log.debug("등록된 userId 없음 : {}", userId);
	        	request.setAttribute("loginFailed", true);
	        	returnURL = "/user/login.jsp";
	        	
	        } else if(user.matchPassword(password)){
	        	
	        	log.debug("로그인 성공 : {}", userId);
	            HttpSession session = request.getSession();
	            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
	            returnURL = "/";
	            
	        } else {
	        	
	        	log.debug("로그인 실패 : {}", userId);
	        	request.setAttribute("loginFailed", true);
	        	returnURL = "/user/login.jsp";
	        	
	        }
		}
		
		return returnURL;
	}
}
