package next.controller;

import core.db.DataBase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListUserController implements Controller {
    private static final long serialVersionUID = 1L;

	@Override
	public String excecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        if (!UserSessionUtils.isLogined(request.getSession())) {
        	return "redirect:/users/loginForm";
        }

        request.setAttribute("users", DataBase.findAll());
		return "/user/list.jsp";
	}
}
