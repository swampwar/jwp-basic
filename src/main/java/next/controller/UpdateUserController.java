package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

public class UpdateUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
	public String excecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnURL = "";
		String requestURL = request.getRequestURI();
		
        String userId = request.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (!UserSessionUtils.isSameUser(request.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }
        
		if(requestURL.startsWith("/users/updateForm")){
			request.setAttribute("user", user);
			returnURL = "/user/updateForm.jsp";
			
		}else if(requestURL.startsWith("/users/update")){
	        User updateUser = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
	        		request.getParameter("email"));
	        log.debug("Update User : {}", updateUser);
	        user.update(updateUser);
	        
	        returnURL = "/";
		}
		
		return returnURL;
	}
}
