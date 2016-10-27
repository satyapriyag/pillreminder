package pills.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

import pills.models.AddUserModel;
import pills.models.LoginResponse;
import pills.models.UserModel;
import pills.models.UserAlarmModel;
import pills.service.UserService;
import pills.service.AlarmService;

@RestController
@RequestMapping(value="/users")
public class UserController {

  @Autowired
  private UserService userService;
  
  @Autowired
  private AlarmService alarmService;
  

  /**
 * @return
 * @throws BadRequestException
 */
@RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<UserModel> viewAll() throws BadRequestException{
	  return userService.viewAll();
  }
  
  /**
 * @param user
 * @return
 * @throws BadRequestException
 */
@RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public UserModel create(@RequestBody AddUserModel user) throws BadRequestException {
     return userService.addUser(user);
  }
  
  /**
 * @param id
 * @return
 * @throws NotFoundException
 * @throws BadRequestException
 */
@RequestMapping(value="/{id}",method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public UserModel view(@PathVariable Integer id) throws NotFoundException, BadRequestException{
	 return userService.viewUser(id);
  }
  
  /**
 * @param user
 * @return
 * @throws BadRequestException
 */
@RequestMapping(value="/{id}",method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public UserModel update(@PathVariable Integer id,@RequestBody UserModel user) throws BadRequestException  {
	  return userService.updateUser(id,user);
  }
  
 /**
 * @param id
 * @throws NotFoundException
 * @throws BadRequestException
 */
@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Integer id) throws NotFoundException,BadRequestException {
      //User user = new User(id);
      userService.deleteUser(id);
  }

@RequestMapping(value="/test",method = RequestMethod.POST)
@ResponseStatus(HttpStatus.OK)
public Integer test(@RequestBody LoginResponse user) {
    //User user = new User(id);
    return userService.addOrUpdate(user);
}

@RequestMapping(value="/{id}/alarms",method = RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
public List<UserAlarmModel> alarms(@PathVariable Integer id) throws BadRequestException {
    //User user = new User(id);
    return alarmService.getByUserId(id);
}

@RequestMapping(value="/{id}/today",method = RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
public List<UserAlarmModel> alarmsForToday(@PathVariable Integer id) throws BadRequestException {
    //User user = new User(id);
    return alarmService.getForToday(id);
}
} 