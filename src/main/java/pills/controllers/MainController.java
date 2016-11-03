package pills.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import inti.ws.spring.exception.client.UnauthorizedException;
import pills.service.AuthenticationService;
import pills.service.UserService;
import pills.models.FeedModel;

@Controller
public class MainController{

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	UserService userService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "login";
	}

	/**
	 * Controller to render admin category Page
	 * 
	 * @param session
	 * @return
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/categoryManager", method = RequestMethod.GET)
	public String questionView(HttpSession session) throws UnauthorizedException {
		Integer id = authenticationService.validateSession(session);
		authenticationService.validateAdmin(id);
		return "CategoryManager";
	}

	/**
	 * Controller to render admin Pills page
	 * 
	 * @param session
	 * @return
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/pillsManager", method = RequestMethod.GET)
	public String pillView(HttpSession session) throws UnauthorizedException {
		Integer id = authenticationService.validateSession(session);
		authenticationService.validateAdmin(id);
		return "PillsManager";
	}
	
	@RequestMapping(value = "/altManager", method = RequestMethod.GET)
    public String altView(HttpSession session) throws UnauthorizedException {
        Integer id = authenticationService.validateSession(session);
        authenticationService.validateAdmin(id);
        return "AlternateManager";
    }
	/**
	 * Controller to render User Home page
	 * 
	 * @param session
	 * @return
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String userView(HttpSession session) throws UnauthorizedException {
		Integer id = authenticationService.validateSession(session);
		authenticationService.validateUser(id);
		return "UserHome";
	}

	/**
	 * Controller to render User alarm manager page
	 * 
	 * @param session
	 * @return
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/alarmManager", method = RequestMethod.GET)
	public String alarmView(HttpSession session) throws UnauthorizedException {
		Integer id = authenticationService.validateSession(session);
		authenticationService.validateUser(id);
		return "AlarmManager";
	}
	
	   @RequestMapping("/feed")
	    @ResponseBody
	    public List<FeedModel> getFeed()  {
	        try {
	          URL feedUrl = new URL("https://health.practo.com/feed/");
	          List<FeedModel> modelList = new ArrayList<FeedModel>();
	          SyndFeedInput input = new SyndFeedInput();
	           SyndFeed feed = input.build(new XmlReader(feedUrl));
	           for (final Iterator<SyndEntry> iter = feed.getEntries().iterator();
	               iter.hasNext(); )
	          {
	             FeedModel model = new FeedModel();
	              final SyndEntry entry = (SyndEntry) iter.next();
	              String title = entry.getTitle();
	              model.setTitle(title);
	              String description=entry.getDescription().getValue();
	              model.setDescription(description);
	              modelList.add(model);
	          }
	           return modelList;
	           
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    return null;    
	    }
}
