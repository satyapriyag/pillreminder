/**
 * 
 */
package pills.service;

import java.util.List;

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
	public void deleteAlarm(Alarm alarm);
	public List<AlarmModel> viewAll();
	public UserAlarmModel viewAlarm(Integer id);
	public void updateAlarm(AlarmModel alarm);
	public List<UserAlarmModel> getByUserId(Integer userId);
	public List<ReminderModel> getByRecurrence(int interval);
	public List<UserAlarmModel> getForToday(Integer userId);
}
