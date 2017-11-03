package next.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RequestMapping.class);
    
	private static Map<String,Controller> reqMap = new HashMap<String,Controller>();
	static {
		reqMap.put("/", new HomeController());
		reqMap.put("/users/form", new FowardController("/user/form.jsp"));
		reqMap.put("/users/loginForm", new FowardController("/user/login.jsp"));
		reqMap.put("/users", new ListUserController());
		reqMap.put("/users/login", new LoginController());
		reqMap.put("/users/profile", new ProfileController());
		reqMap.put("/users/logout", new LogoutController());
		reqMap.put("/users/create", new CreateUserController());
		reqMap.put("/users/updateForm", new UpdateUserController());
		reqMap.put("/users/update", new UpdateUserController());
	}
	
	public static Controller getController(String url){
		return reqMap.get(url);
	}
	
    public static void putController(String url, Controller controller){
    	reqMap.put(url, controller);
    }
}
