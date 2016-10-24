package pills.controllers;

import java.security.Principal;
import java.util.Enumeration;
import java.util.LinkedHashMap;

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
import org.springframework.web.bind.annotation.RestController;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.ForbiddenException;
import inti.ws.spring.exception.client.UnauthorizedException;
import pills.models.AddUserModel;
import pills.models.LoginResponse;
import pills.models.UserModel;
import pills.service.AuthenticationService;
import pills.service.UserService;

@EnableOAuth2Sso
@Controller
public class MainController extends WebSecurityConfigurerAdapter{

	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	UserService userService;
	
  @RequestMapping(value="/", method = RequestMethod.GET)
  public String index() {
	  return "login";
  }
  
  @RequestMapping(value = "/home", method = RequestMethod.GET)
	public String questionView(HttpSession session) throws UnauthorizedException{
	  Integer id = authenticationService.validateSession(session);
		return "CategoryManager";
	}
  
  @RequestMapping(value = "/pillsHome", method = RequestMethod.GET)
	public String pillView(HttpSession session) throws UnauthorizedException {
	  Integer id = authenticationService.validateSession(session);
	  return "PillsManager";
	}
  @RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String userView(HttpSession session) throws UnauthorizedException {
	  Integer id = authenticationService.validateSession(session);
		return "UserHome";
	}
  @RequestMapping(value = "/alarm", method = RequestMethod.GET)
	public String alarmView(HttpSession session) throws UnauthorizedException {
	  Integer id = authenticationService.validateSession(session);
		return "AlarmManager";
	}

@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AddUserModel loginUser(Principal principal, HttpSession session)
			throws UnauthorizedException, ForbiddenException, BadRequestException {
		if (principal == null)
			throw new ForbiddenException("Access forbiden");
		OAuth2Authentication auth = (OAuth2Authentication) principal;
		
		  
		if (auth.isAuthenticated()) {
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, String> details = (LinkedHashMap<String, String>) auth.getUserAuthentication()
					.getDetails();
			
//			String domain = details.get("hd");
//			 if (!"practo.com".equalsIgnoreCase(domain))
//			 throw new UnauthorizedException("Unauthorized user");

			String email = details.get("email");
			String name = details.get("name");


			AddUserModel user = new AddUserModel();
			user.setUserEmail(email);
			user.setUserName(name);
			
			if("satyapriya.g@gmail.com".equalsIgnoreCase(email)){
			session.setAttribute("id",1);
			user.setUserRole(1);
			return user;
			}else{
			user.setUserRole(2);
			}
			
			UserModel addUser = userService.addUser(user);

			session.setAttribute("id", addUser.getUserId() );
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
}
