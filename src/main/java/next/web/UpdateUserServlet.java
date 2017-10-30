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

@WebServlet("/user/updateUser")
public class UpdateUserServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String userId = req.getParameter("userId");
    	String password = req.getParameter("password");
    	String name = req.getParameter("name");
    	String email = req.getParameter("email");
    	  
    	User user = new User(userId,password,name,email);
    	DataBase.addUser(user);
    	
    	resp.sendRedirect("/user/list");
    }
    
    
}
