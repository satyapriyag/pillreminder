package pills.service;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import inti.ws.spring.exception.client.UnauthorizedException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger LOG = Logger.getLogger(AuthenticationServiceImpl.class);

	@Override
	public Integer validateSession(HttpSession session) throws UnauthorizedException {
		LOG.info("Received request for authentication");
		if (session == null) {
			LOG.error("Session has been expired.Please login to continue");
			throw new UnauthorizedException("Please login to continue");
		}
		Integer id = (Integer) session.getAttribute("id");

		if (id == null) {
			LOG.error("Session has been expired/Credentials are wrong.Please login to continue");
			throw new UnauthorizedException("Please login to continue");
		} else {
			return id;
		}
	}

	public void validateAdmin(Integer id) throws UnauthorizedException {
		if (id != 1) {
			LOG.error("Admin has no access to User Portal.");
			throw new UnauthorizedException("Admin cannot login to User Portal");
		} else
			return;
	}

	public void validateUser(Integer id) throws UnauthorizedException {
		if (id == 1) {
			LOG.error("User has no access to Admin Portal.");
			throw new UnauthorizedException("User cannot login to Admin Portal");
		} else
			return;
	}
}