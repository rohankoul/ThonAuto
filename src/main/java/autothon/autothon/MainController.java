package autothon.autothon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import autothon.autothon.uiAutomation.Steps;

@RestController
public class MainController {

	private static final int MAX_T = 20;
	static List<JSONObject> results_window = new ArrayList<JSONObject>(); 
	static List<JSONObject> results_mobile = new ArrayList<JSONObject>(); 
	@RequestMapping("/")
    public String postiveResponse(){
        return "<h1>Welcome to Tests</h1>";
    }
	
	@RequestMapping("/api/trigger")
    public String trigger(){
       
		
		ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
		
		Runnable s1 = new Steps("chrome","windows");
		//Runnable s2 = new Steps("chrome","android");
		
		pool.execute(s1);	
		//pool.execute(s2);	
		return "200";
		
    }
	
	@RequestMapping("/api/result/window")
    public List<JSONObject> resultWindow(){
       	return results_window;
	}
	
	@RequestMapping("/api/result/mobile")
    public List<JSONObject> resultMobile(){
       	return results_window;
	}
	
	public static void generateResultsWindow(int step, String action, String label, String value, String status, String log, String tech)
	{
		JSONObject stepDetails = new JSONObject();
		stepDetails.put("step", step);
		stepDetails.put("actionType", action);
		stepDetails.put("label", label);
		stepDetails.put("value", value);
		stepDetails.put("status", status);
		stepDetails.put("log", log);
		stepDetails.put("tech", tech);
		
		results_window.add(stepDetails);	
	}
	
	public static void generateResultsMobile(int step, String action, String label, String value, String status, String log, String tech)
	{
		JSONObject stepDetails = new JSONObject();
		stepDetails.put("step", step);
		stepDetails.put("actionType", action);
		stepDetails.put("label", label);
		stepDetails.put("value", value);
		stepDetails.put("status", status);
		stepDetails.put("log", log);
		stepDetails.put("tech", tech);
		
		results_mobile.add(stepDetails);	
	}
	
/*	@RequestMapping("/api/result")
	public */
	
}
