package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pills.dao.AlarmDao;
import pills.entity.Alarm;
import pills.models.AddAlarmModel;
import pills.models.AlarmModel;
import pills.models.ReminderModel;
import pills.models.UserAlarmModel;
import pills.utilities.MappingUtility;

@Service
@Transactional
public class AlarmServiceImpl implements AlarmService{
	
	@Autowired
	private MappingUtility mapUtility;
	
	@Autowired
	private AlarmDao alarmDao;
	
	public AlarmModel addAlarm(AddAlarmModel alarmModel){
	    Alarm alarm = mapUtility.mapAddAlarmModel(alarmModel);
		alarmDao.save(alarm);
		return mapUtility.mapAlarm(alarm);
	}
	public void deleteAlarm(Alarm alarm){
		alarmDao.delete(alarm);
	}
	public UserAlarmModel viewAlarm(Integer id){
		Alarm alarm =alarmDao.getById(id);
		return mapUtility.mapAlarmForUser(alarm);
	}
	public List<AlarmModel> viewAll(){
		List<Alarm> alarms =alarmDao.getAll();
		return mapUtility.mapAlarms(alarms);
	}
	public void updateAlarm(AlarmModel alarmModel){
		Alarm alarm = mapUtility.mapAlarmModel(alarmModel);
		alarmDao.update(alarm);
	}
	public List<UserAlarmModel> getByUserId(Integer userId){
		List<Alarm> alarms = alarmDao.getByUserId(userId);
		return mapUtility.mapAlarmsForUser(alarms);
	}
	public List<ReminderModel> getByRecurrence(int interval){
		List<Alarm> alarms = alarmDao.getByRecurrence(interval);
		return mapUtility.mapReminders(alarms);
	}
	public List<UserAlarmModel> getForToday(Integer userId){
		List<Alarm> alarms = alarmDao.getForToday(userId);
		return mapUtility.mapAlarmsForUser(alarms);
	}

}