package pills.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inti.ws.spring.exception.client.BadRequestException;
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
	
	private static final Logger LOG = Logger.getLogger(AlarmServiceImpl.class);
	
	@Autowired
	private MappingUtility mapUtility;
	
	@Autowired
	private AlarmDao alarmDao;
	
	public AlarmModel addAlarm(AddAlarmModel alarmModel){
	    Alarm alarm = mapUtility.mapAddAlarmModel(alarmModel);
		alarmDao.save(alarm);
		LOG.info("Added alarm with aid "+ alarm.getAId());
		return mapUtility.mapAlarm(alarm);
	}
	
	public void deleteAlarm(Integer id) throws BadRequestException{
		if(id<=0 || id ==null ) throw new BadRequestException("Required parameters are either missing or invalid");
		Alarm alarm = new Alarm(id);
		alarmDao.delete(alarm);
		LOG.info("Deleted the alarm with alarmId = " + id);
	}
	
	public UserAlarmModel viewAlarm(Integer id)throws BadRequestException{
		if(id<=0 || id ==null ) throw new BadRequestException("Required parameters are either missing or invalid");
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
		LOG.info("Updated the alarm with alarmId = " + alarmModel.getAId());
	}
	
	public List<UserAlarmModel> getByUserId(Integer userId) throws BadRequestException{
		if(userId<=0 || userId ==null ) throw new BadRequestException("Required parameters are either missing or invalid");
		List<Alarm> alarms = alarmDao.getByUserId(userId);
		return mapUtility.mapAlarmsForUser(alarms);
	}
	public List<ReminderModel> getByRecurrence(int interval) throws BadRequestException{
		if(interval<=0 ) throw new BadRequestException("Required parameters are either missing or invalid");
		List<Alarm> alarms = alarmDao.getByRecurrence(interval);
		return mapUtility.mapReminders(alarms);
	}
	
	public List<UserAlarmModel> getForToday(Integer userId) throws BadRequestException{
		if(userId<=0 || userId ==null ) throw new BadRequestException("Required parameters are either missing or invalid");
		List<Alarm> alarms = alarmDao.getForToday(userId);
		return mapUtility.mapAlarmsForUser(alarms);
	}

}