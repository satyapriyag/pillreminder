package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inti.ws.spring.exception.client.BadRequestException;
import pills.dao.RoleDao;
import pills.dao.UserDao;
import pills.dao.UserRoleDao;
import pills.entity.User;
import pills.entity.UserRole;
import pills.models.AddUserModel;
import pills.models.LoginResponse;
import pills.models.UserModel;
import pills.utilities.MappingUtility;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private MappingUtility mapUtility;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public UserModel addUser(AddUserModel userModel) throws BadRequestException{
		if (userModel.getUserName() == null || userModel.getUserName().equals("") ||
				userModel.getUserEmail() == null ||	userModel.getUserEmail().equals(""))
			throw new BadRequestException("Required parameters are either missing or invalid");
	    User user = mapUtility.mapAddUserModel(userModel);
		userDao.save(user);
		return mapUtility.mapUser(user);
	}
	public void deleteUser(Integer id) throws BadRequestException{
		if (id <= 0)
			throw new BadRequestException("Required parameters are either missing or invalid");
		User user = new User(id);
		userDao.delete(user);
	}
	public UserModel viewUser(Integer id) throws BadRequestException{
		if (id <= 0)
			throw new BadRequestException("Required parameters are either missing or invalid");
		return mapUtility.mapUser(userDao.getById(id));
	}
	public List<UserModel> viewAll(){
		return mapUtility.mapUsers(userDao.getAll());
	}
	public UserModel updateUser(Integer id,UserModel user) throws BadRequestException{
		if (user.getUserId() <= 0 || user.getUserName()==null || user.getUserName().equals("") ||
				user.getUserEmail() == null ||	user.getUserEmail().equals(""))
			throw new BadRequestException("Required parameters are either missing or invalid");
		User updatedUser = mapUtility.mapUserModel(user);
		userDao.update(updatedUser);
		return mapUtility.mapUser(userDao.getById(id));
	}
	public Integer addOrUpdate(LoginResponse user){
		Integer id;
		try{
			id = userDao.getByMail(user.getUserEmail()).getUserId();
		}
		catch(NullPointerException e){
			User userModel = new User();
			userModel.setUserContact(user.getUserContact());
			userModel.setUserEmail(user.getUserEmail());
			userModel.setUserName(user.getUserName());
			userDao.save(userModel);
			id = (userDao.getByMail(user.getUserEmail())).getUserId();
			UserRole userRole = new UserRole(roleDao.getById(2),userDao.getByMail(user.getUserEmail()));
			userRoleDao.save(userRole);
		}
		return id;
	}
}