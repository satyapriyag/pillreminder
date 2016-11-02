package pills.controllers;

import java.security.Principal;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.ForbiddenException;
import inti.ws.spring.exception.client.UnauthorizedException;
import pills.models.LoginResponse;
import pills.service.AuthenticationService;
import pills.service.UserService;

@EnableOAuth2Sso
@RestController
public class AuthController extends WebSecurityConfigurerAdapter {

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
    public LoginResponse loginUser(Principal principal, HttpSession session)
            throws UnauthorizedException, ForbiddenException, BadRequestException {
        if (principal == null)
            throw new ForbiddenException("Access forbiden");
        OAuth2Authentication auth = (OAuth2Authentication) principal;

        if (auth.isAuthenticated()) {
            @SuppressWarnings("unchecked")
            LinkedHashMap<String, String> details = (LinkedHashMap<String, String>) auth.getUserAuthentication()
                    .getDetails();
            System.out.println(details);
//             String domain = details.get("hd");
//             if (!"practo.com".equalsIgnoreCase(domain)){
//               System.out.println("domain"+domain);
//             throw new UnauthorizedException("Unauthorized user");}

            String email = details.get("email");
            String name = details.get("name");

            LoginResponse user = new LoginResponse();
            user.setUserEmail(email);
            user.setUserName(name);

            if ("satya.priya@practo.com".equalsIgnoreCase(email)) {
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
}