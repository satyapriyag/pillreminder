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
import pills.entity.Alarm;
import pills.models.AddAlarmModel;
import pills.models.AlarmModel;
import pills.models.UserAlarmModel;
import pills.service.AlarmService;

@RestController
@RequestMapping(value="/alarms")
public class AlarmController {

	  @Autowired
	  private AlarmService alarmService;
	  

	  /**
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(method = RequestMethod.GET)
	  @ResponseStatus(HttpStatus.OK)
	  public List<AlarmModel> viewAll() throws BadRequestException{
		  return alarmService.viewAll();
	  }
	  
	  /**
	 * @param alarm
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(method = RequestMethod.POST)
	  @ResponseStatus(HttpStatus.CREATED)
	  public AlarmModel create(@RequestBody AddAlarmModel alarm) throws BadRequestException {
		System.out.println(alarm.getAPillId()+""+alarm.getARecurrence());
		//return null;
	   return alarmService.addAlarm(alarm);
	  }
	  
	  /**
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	  @ResponseStatus(HttpStatus.OK)
	  public UserAlarmModel view(@PathVariable Integer id) throws NotFoundException{
		 return alarmService.viewAlarm(id);
	  }
	  
	  /**
	 * @param alarm
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.PATCH)
	  @ResponseStatus(HttpStatus.OK)
	  public AlarmModel update(@RequestBody AlarmModel alarm) throws BadRequestException  {
		  alarmService.updateAlarm(alarm);
		  return alarm;
	  }
	  
	 /**
	 * @param id
	 * @throws NotFoundException
	 * @throws BadRequestException
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	  @ResponseStatus(HttpStatus.OK)
	  public void delete(@PathVariable Integer id) throws NotFoundException,BadRequestException {
	      Alarm alarm = new Alarm(id);
	      alarmService.deleteAlarm(alarm);
	  }


}
