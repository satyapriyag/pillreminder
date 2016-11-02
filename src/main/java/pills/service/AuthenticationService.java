package pills.service;
import javax.servlet.http.HttpSession;
import inti.ws.spring.exception.client.UnauthorizedException;

/**
 * Interface to handle all the authentication Service methods
 * @author satya
 *
 */
public interface AuthenticationService {
    
    /**
     * Method to validate session 
     * @param session Details of the session to be validated
     * @return Integer 1 for authorized session and 0 if not
     * @throws UnauthorizedException Thrown when session is not authorized
     */
	Integer validateSession(HttpSession session) throws UnauthorizedException;
	
	/**
	 * Method to validate whether user is admin 
	 * @param id of the user accessing the page
	 * @throws UnauthorizedException Thrown when User accesses admin page
	 */
	void validateAdmin(Integer id) throws UnauthorizedException;
	
	/**
     * Method to validate User portal pages 
     * @param id of the user accessing the page
     * @throws UnauthorizedException Thrown when admin accesses User page
     */
	void validateUser(Integer id) throws UnauthorizedException;
	
}