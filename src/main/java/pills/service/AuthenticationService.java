package pills.service;
import javax.servlet.http.HttpSession;
import inti.ws.spring.exception.client.UnauthorizedException;

public interface AuthenticationService {

	Integer validateSession(HttpSession session) throws UnauthorizedException;
	void validateAdmin(Integer id) throws UnauthorizedException;
	void validateUser(Integer id) throws UnauthorizedException;
	
}