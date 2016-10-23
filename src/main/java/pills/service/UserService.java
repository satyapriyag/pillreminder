package pills.service;

import pills.models.AddUserModel;
import pills.models.UserModel;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;

public interface UserService{
	public UserModel addUser(AddUserModel userModel) throws BadRequestException;
	public void deleteUser(Integer id) throws BadRequestException;
	public UserModel viewUser(Integer id) throws BadRequestException;
	public List<UserModel> viewAll();
	public UserModel updateUser(Integer userId,UserModel user) throws BadRequestException;
	
}