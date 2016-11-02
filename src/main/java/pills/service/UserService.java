package pills.service;

import pills.models.AddUserModel;
import pills.models.LoginResponse;
import pills.models.UserModel;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;

/**
 * Interface handling methods pertaining to User Service
 * @author satya
 *
 */
public interface UserService{
  /**
   * Method to add user to the repository
   * @param userModel {@link AddUserModel}  Details of the user to be added
   * @return {@link UserModel}
   * @throws BadRequestException Thrown when userName is null or empty
   */
	public UserModel addUser(AddUserModel userModel) throws BadRequestException;
	
    /**
     * Method to delete user from the repository
     * @param id Id of the user to be deleted
     * @throws BadRequestException Thrown when id is null or non-integer value
     */
	public void deleteUser(Integer id) throws BadRequestException;
	
    /**
     * Method to view details of a particular user
     * @param id of the user to be viewed
     * @return {@link UserModel} contains all the details of user 
     * @throws BadRequestException Thrown when id is null or non-integer value
     */
	public UserModel viewUser(Integer id) throws BadRequestException;
	
    /**
     * Method to retrieve all the users from the repository
     * @return List of {@link UserModel}
     */
	public List<UserModel> viewAll();
	
    /**
     * Method to update the details of the user
     * @param userId Id of the user to be updated
     * @param user {@link UserModel} Details to be updated
     * @return {@link UserModel}
     * @throws BadRequestException Thrown when id is null or non-integer value
     */
	public UserModel updateUser(Integer userId,UserModel user) throws BadRequestException;
	
	/**
	 * Method to add new user and update existing user
	 * @param user Details of the user fetched from session
	 * @return Id of the user who is added/updated
	 */
	public Integer addOrUpdate(LoginResponse user);
}