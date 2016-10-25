package pills.service;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import inti.ws.spring.exception.client.UnauthorizedException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public Integer validateSession(HttpSession session) throws UnauthorizedException {
		if (session == null)
			throw new UnauthorizedException("Please login to continue");
		Integer id = (Integer) session.getAttribute("id");

		if (id == null) {
			throw new UnauthorizedException("Please login to continue");
		} else {
			return id;
		}
	}

	public void validateAdmin(Integer id) throws UnauthorizedException{
	    if(id!=1){
	    	throw new UnauthorizedException("Admin cannot login to User Portal");
	    }else
	    	return;
	}
	public void validateUser(Integer id) throws UnauthorizedException{
	    if(id==1){
	    	throw new UnauthorizedException("User cannot login to Admin Portal");
	    }else
	    	return;
	}
}