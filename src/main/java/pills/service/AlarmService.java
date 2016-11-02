/**
 * Interface for Alarm Service that handles all the services related to {@link AlarmModel}
 * 
 */
package pills.service;

import java.util.List;

import inti.ws.spring.exception.client.BadRequestException;
import pills.models.AddAlarmModel;
import pills.models.AlarmModel;
import pills.models.ReminderModel;
import pills.models.UserAlarmModel;

/**
 * @author satya
 *
 */
public interface AlarmService{
    /**
     * Method to add alarm to the repository
     * @param alarm {@link AddAlarmModel} Alarm details of the alarm to be added
     * @return {@link AlarmModel}
     */
	public AlarmModel addAlarm(AddAlarmModel alarm);
	
	/**
	 * Method to delete alarm from the repository
	 * @param id Id of the alarm to be deleted
	 * @throws BadRequestException Thrown when id is null or non-integer value
	 */
	public void deleteAlarm (Integer id) throws BadRequestException;
	
	/**
	 * Method to retrieve all the alarms from the repository
	 * @return List of {@link AlarmModel}
	 */
	public List<AlarmModel> viewAll();
	
	/**
	 * Method to view details of a particular alarm
	 * @param id of the alarm to be viewed
	 * @return {@link UserAlarmModel} contains all the details of alarm to be displayed on User page
	 * @throws BadRequestException Thrown when id is null or non-integer value
	 */
	public UserAlarmModel viewAlarm(Integer id) throws BadRequestException;
	
	/**
	 * Method to update the details of the alarm
	 * @param alarm {@link AlarmModel} Details to be updated
	 */
	public void updateAlarm(AlarmModel alarm);
	
	/**
	 * Method to retrieve the alarms for a particular user
	 * @param userId Id of the user whose alarms need to be fetched
	 * @return List of {@link UserAlarmModel} List of all alarms added by the user
	 * @throws BadRequestException Thrown when userId is null or non-integer value
	 */
	public List<UserAlarmModel> getByUserId(Integer userId) throws BadRequestException;
	
	/**
	 * Method to retrieve the reminders based on slot of the day(morning,afternoon,evening)
	 * @param interval Slot of the day for which alarms need to be retrieved
	 * @return List of {@link ReminderModel} List of alarms based on slot of the day
	 * @throws BadRequestException Thrown when interval is null or non-integer value
	 */
	public List<ReminderModel> getByRecurrence(int interval) throws BadRequestException;
	
	/**
	 * Method to retrieve the alarms for current day for particular user
	 * @param userId Id of the user
	 * @return List of {@link UserAlarmModel} List of alarms for a particular user for today
	 * @throws BadRequestException Thrown when userId is null or non-integer value
	 */
	public List<UserAlarmModel> getForToday(Integer userId) throws BadRequestException;
}
