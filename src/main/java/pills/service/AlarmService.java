/**
 * 
 */
package pills.service;

import java.util.List;

import pills.entity.Alarm;
import pills.models.AddAlarmModel;
import pills.models.AlarmModel;

/**
 * @author satya
 *
 */
public interface AlarmService{
	public AlarmModel addAlarm(AddAlarmModel alarm);
	public void deleteAlarm(Alarm alarm);
	public List<AlarmModel> viewAll();
	public AlarmModel viewAlarm(Integer id);
	public void updateAlarm(AlarmModel alarm);
	public List<AlarmModel> getByUserId(Integer userId);
	public List<AlarmModel> getByRecurrence(int interval);
}
