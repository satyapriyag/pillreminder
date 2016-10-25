package pills;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.orm.hibernate5.HibernateObjectRetrievalFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import inti.ws.spring.exception.client.BadRequestException;
import pills.models.AddUserModel;
import pills.models.UserModel;
import pills.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes={TestDatabaseConfig.class})
public class UserServiceTest{
	@Autowired
	private UserService service;
	
	
	@Test(expected = HibernateObjectRetrievalFailureException.class)
	@Rollback(true)
	public void deleteUserTest() throws BadRequestException {

		 //Save and Get Test
		AddUserModel addUser = new AddUserModel();
		addUser.setUserName("deleteUserTest");
		addUser.setUserEmail("deleteUser@example.com");
		addUser.setUserRole(2);
		addUser.setUserContact("999999999");
		UserModel userModel = service.addUser(addUser);
		assertTrue(userModel.getUserId() > 0);

		service.deleteUser(userModel.getUserId());
		userModel = service.viewUser(userModel.getUserId());

	}

	@Test(expected = BadRequestException.class)
	@Transactional
	@Rollback(true)
	public void deleteUserInvalidId() throws BadRequestException {
		service.deleteUser(-1);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void addUserTest() throws BadRequestException {
		
		AddUserModel addUser = new AddUserModel();
		addUser.setUserName("Shravya");
		addUser.setUserEmail("shravyareddyk@gmail.com");
		addUser.setUserContact("9919191919");
		// Save and Get Test
		UserModel userModel = service.addUser(addUser);

		assertTrue(userModel.getUserId() > 0);
	}


	@Test
	@Transactional
	@Rollback(true)
	public void updateUserNameTest() throws BadRequestException {

		UserModel userModel = new UserModel();
		userModel.setUserId(1);
		userModel.setUserName("Shravya");
		userModel.setUserEmail("shravyareddyk@gmail.com");
		userModel.setUserContact("9919191919");
		service.updateUser(1, userModel);
		userModel = service.viewUser(1);

		assertEquals(userModel.getUserName(),"Shravya");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void updateUserEmaiTest() throws BadRequestException {

		UserModel userModel = new UserModel();
		userModel.setUserId(1);
		userModel.setUserName("Shravya");
		userModel.setUserEmail("shravyareddyk@gmail.com");
		userModel.setUserContact("9919191919");
		service.updateUser(1, userModel);
		userModel = service.viewUser(1);

		assertEquals(userModel.getUserEmail(),"shravyareddyk@gmail.com");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void updateUserContactTest() throws BadRequestException {

		UserModel userModel = new UserModel();
		userModel.setUserId(1);
		userModel.setUserName("Shravya");
		userModel.setUserEmail("shravyareddyk@gmail.com");
		userModel.setUserContact("9919191919");
		service.updateUser(1, userModel);
		userModel = service.viewUser(1);

		assertEquals(userModel.getUserContact(),"9919191919");
	}
	@Test
	@Transactional
	@Rollback(true)
	public void getAllUsersTest() throws BadRequestException {
		List<UserModel> userModels = service.viewAll();
		int size = userModels.size();

		AddUserModel addUser = new AddUserModel();
		addUser.setUserName("Shravya");
		addUser.setUserEmail("shravyareddyk@gmail.com");
		addUser.setUserContact("9919191919");
		// Save and Get Test
		UserModel userModel = service.addUser(addUser);
		assertTrue(userModel.getUserId() > 0);

		userModels = service.viewAll();
		assertEquals(size + 1, userModels.size());
	}
}