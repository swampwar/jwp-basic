package core.mvc;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	private View view;
	HashMap<String, Object> model = new HashMap<>();
	
	public ModelAndView(View view){
		this.view = view;
	}
	
	public ModelAndView addObject(String name, Object value){
		model.put(name, value);
		return this;
	}
	
	public Map<String, Object> getModel(){
		return this.model;
	}
	
	public View getView(){
		return this.view;
	}
}
