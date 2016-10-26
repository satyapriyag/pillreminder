/**
 * 
 */
package pills.service;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;
import pills.entity.Alarm;
import pills.models.AddAlarmModel;
import pills.models.AlarmModel;
import pills.models.ReminderModel;
import pills.models.UserAlarmModel;

/**
 * @author satya
 *
 */
public interface AlarmService{
	public AlarmModel addAlarm(AddAlarmModel alarm);
	public void deleteAlarm (Integer id) throws BadRequestException;
	public List<AlarmModel> viewAll();
	public UserAlarmModel viewAlarm(Integer id) throws BadRequestException;
	public void updateAlarm(AlarmModel alarm);
	public List<UserAlarmModel> getByUserId(Integer userId) throws BadRequestException;
	public List<ReminderModel> getByRecurrence(int interval) throws BadRequestException;
	public List<UserAlarmModel> getForToday(Integer userId) throws BadRequestException;
}
