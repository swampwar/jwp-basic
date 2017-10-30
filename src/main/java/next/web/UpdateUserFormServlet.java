package next.web;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/updateUserForm")
public class UpdateUserFormServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String queryString = (String)req.getQueryString();
    	
    	String[] param = queryString.split("=");
    	String userId = param[1];
    
    	log.debug("UpdateUserFormServlet queryString : " + queryString); // userId=user2
    	log.debug("UpdateUserFormServlet userId : " + userId);
    	
    	User user =  DataBase.findUserById(userId);
    	req.setAttribute("user", user);
    	
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
    }
    
    
}
