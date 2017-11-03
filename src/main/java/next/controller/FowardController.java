package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FowardController implements Controller{
	private String fowardUrl = "";
	
	public FowardController(String url){
		this.fowardUrl = url;
		
		if(this.fowardUrl == null){
			throw new NullPointerException("fowardUrl is Null!");
		}
	}
	
	@Override
	public String excecute(HttpServletRequest request, HttpServletResponse response) {
		return fowardUrl;
	}

}
