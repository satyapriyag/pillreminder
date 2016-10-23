package pills.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "hello!";
  }

  @RequestMapping(value = "/home", method = RequestMethod.GET)
	public String questionView() {
		return "CategoryManager";
	}
  
  @RequestMapping(value = "/pillsHome", method = RequestMethod.GET)
	public String pillView() {
		return "PillsManager";
	}
  @RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userView() {
		return "UserHome";
	}
  @RequestMapping(value = "/alarm", method = RequestMethod.GET)
	public String alarmView() {
		return "AlarmManager";
	}
}
