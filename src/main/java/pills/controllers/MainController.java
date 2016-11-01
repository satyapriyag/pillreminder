package pills.controllers;

import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.ForbiddenException;
import inti.ws.spring.exception.client.UnauthorizedException;
import pills.models.LoginResponse;
import pills.service.AuthenticationService;
import pills.service.UserService;
import pills.models.FeedModel;

@EnableOAuth2Sso
@Controller
public class MainController extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	UserService userService;

	/**
	 * 
	 * @param principal
	 * @param session
	 *            to validate user
	 * @return LoginResponse containing user info
	 * @throws UnauthorizedException
	 *             thrown when either user has no valid session or when he
	 *             accesses admin portal
	 * @throws ForbiddenException
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public LoginResponse loginUser(Principal principal, HttpSession session)
			throws UnauthorizedException, ForbiddenException, BadRequestException {
		if (principal == null)
			throw new ForbiddenException("Access forbiden");
		OAuth2Authentication auth = (OAuth2Authentication) principal;

		if (auth.isAuthenticated()) {
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, String> details = (LinkedHashMap<String, String>) auth.getUserAuthentication()
					.getDetails();

			// String domain = details.get("hd");
			// if (!"practo.com".equalsIgnoreCase(domain))
			// throw new UnauthorizedException("Unauthorized user");

			String email = details.get("email");
			String name = details.get("name");

			LoginResponse user = new LoginResponse();
			user.setUserEmail(email);
			user.setUserName(name);

			if ("satyapriya.g@gmail.com".equalsIgnoreCase(email)) {
				session.setAttribute("id", 1);
				user.setUserRole(1);
				user.setUserId(1);
				return user;
			} else {
				user.setUserRole(2);
			}

			Integer id = userService.addOrUpdate(user);
			user.setUserId(id);
			session.setAttribute("id", id);
			return user;

		} else {
			throw new UnauthorizedException("Login failed. Please try again");
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/**", "/login**", "/webjars/**", "/js/**").permitAll()
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf().disable();
	}

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
