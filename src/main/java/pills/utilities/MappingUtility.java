package pills.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pills.entity.Alarm;
import pills.entity.Alternative;
import pills.entity.Category;
import pills.entity.Pill;
import pills.entity.User;
import pills.models.AddAlarmModel;
import pills.models.AddPillModel;
import pills.models.AddUserModel;
import pills.models.AlarmModel;
import pills.models.CategoryModel;
import pills.models.PillModel;
import pills.models.ReminderModel;
import pills.models.UserAlarmModel;
import pills.models.UserModel;
import pills.service.PillService;

@Service
public class MappingUtility {

	@Autowired
	PillService service ;
	
	/**
	 * Converts list of Category to list of CategoryModel
	 * @param categories
	 * @return CategoryModel
	 */
	public List<CategoryModel> mapCategories(List<Category> categories) {
		List<CategoryModel> categoryModels = new ArrayList<>();
		for (Category category : categories) {
			categoryModels.add(mapCategory(category));
		}
		return categoryModels;
	}
	
	/**
	 * 
	 * @param category
	 * @return
	 */
	public CategoryModel mapCategory(Category category) {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setCategoryId(category.getCategoryId());
		categoryModel.setCategoryName(category.getCategoryName());
		return categoryModel;
	}
	
	/**
	 * 
	 * @param categoryModel
	 * @return
	 */
	public Category mapCategoryModel(CategoryModel categoryModel){
		Category category = new Category();
		category.setCategoryId(categoryModel.getCategoryId());
		category.setCategoryName(categoryModel.getCategoryName());
		return category;
	}
	
	/**
	 * 
	 * @param alternatives
	 * @return
	 */
	public List<PillModel> mapAlternatives(List<Alternative> alternatives){
		List<PillModel> pills = new ArrayList<>();
		for(Alternative alternative:alternatives){
			System.out.println("alt id "+alternative.getAltId());
			System.out.println(alternative.getPillByAlternatePillId().getPillName());
			pills.add(mapPill(alternative.getPillByAlternatePillId()));
		}
		return pills;
		
	}
	
	/**
	 * 
	 * @param pills
	 * @return
	 */
	public List<PillModel> mapPills(List<Pill> pills) {
		List<PillModel> pillModels = new ArrayList<>();
		for (Pill pill : pills) {
			pillModels.add(mapPill(pill));
		}
		return pillModels;
	}
	
	/**
	 * 
	 * @param pill
	 * @return
	 */
	public PillModel mapPill(Pill pill) {
		PillModel pillModel = new PillModel();
		pillModel.setPillId(pill.getPillId());
		pillModel.setPillName(pill.getPillName());
		pillModel.setPillCategoryId(pill.getCategory().getCategoryId());
		return pillModel;
	}
	/**
	 * 
	 * @param pillModel
	 * @return
	 */
	public Pill mapPillModel(PillModel pillModel){
		Pill pill = new Pill();
		pill.setPillId(pillModel.getPillId());
		pill.setPillName(pillModel.getPillName());
		pill.setCategory(new Category(pillModel.getPillCategoryId()));
		return pill;
	}
	
	/**
	 * 
	 * @param pillModel
	 * @return
	 */
	public Pill mapAddPillModel(AddPillModel pillModel){
		Pill pill = new Pill();
		pill.setPillName(pillModel.getPillName());
		pill.setCategory(new Category(pillModel.getPillCategoryId()));
		return pill;
	}
	/**
	 * 
	 * @param alarms
	 * @return
	 */
	public List<AlarmModel> mapAlarms(List<Alarm> alarms) {
		List<AlarmModel> alarmModels = new ArrayList<>();
		for (Alarm alarm : alarms) {
			alarmModels.add(mapAlarm(alarm));
		}
		return alarmModels;
	}
	
	/**
	 * 
	 * @param alarm
	 * @return
	 */
	public AlarmModel mapAlarm(Alarm alarm) {
		AlarmModel alarmModel = new AlarmModel();
		alarmModel.setAId(alarm.getAId());
		alarmModel.setAPillId(alarm.getPill().getPillId());
		alarmModel.setAUserId(alarm.getUser().getUserId());
		alarmModel.setAStartDate(alarm.getAStartDate());
		alarmModel.setAEndDate(alarm.getAEndDate());
		alarmModel.setARecurrence(alarm.getARecurrence());
		return alarmModel;
	}
	
	/**
	 * 
	 * @param alarms
	 * @return
	 */
	public List<UserAlarmModel> mapAlarmsForUser(List<Alarm> alarms) {
		List<UserAlarmModel> alarmModels = new ArrayList<>();
		for (Alarm alarm : alarms) {
			alarmModels.add(mapAlarmForUser(alarm));
		}
		return alarmModels;
	}
	
	/**
	 * 
	 * @param alarm
	 * @return
	 */
	public UserAlarmModel mapAlarmForUser(Alarm alarm) {
		UserAlarmModel alarmModel = new UserAlarmModel();
		alarmModel.setAId(alarm.getAId());
		alarmModel.setAPillName(alarm.getPill().getPillName());
		alarmModel.setAPillId(alarm.getPill().getPillId());
		alarmModel.setACategorylId(alarm.getPill().getCategory().getCategoryId());
		alarmModel.setAUserId(alarm.getUser().getUserId());
		alarmModel.setAStartDate(alarm.getAStartDate());
		alarmModel.setAEndDate(alarm.getAEndDate());
		alarmModel.setARecurrence(alarm.getARecurrence());
		return alarmModel;
	}
	
	/**
	 * 
	 * @param alarmModel
	 * @return
	 */
	public Alarm mapAlarmModel(AlarmModel alarmModel){
		Alarm alarm = new Alarm();
		alarm.setAId(alarmModel.getAId());
		alarm.setPill(new Pill(alarmModel.getAPillId()));
		alarm.setUser(new User(alarmModel.getAUserId()));
		alarm.setAStartDate(alarmModel.getAStartDate());
		alarm.setAEndDate(alarmModel.getAEndDate());
		alarm.setARecurrence(alarmModel.getARecurrence());
		return alarm;
	}
	
	/**
	 * 
	 * @param alarmModel
	 * @return
	 */
	public Alarm mapAddAlarmModel(AddAlarmModel alarmModel){
		Alarm alarm = new Alarm();
		System.out.println(alarmModel.getAPillId());
		alarm.setUser(new User(alarmModel.getAUserId()));
		alarm.setPill(new Pill(alarmModel.getAPillId()));
		
		alarm.setAStartDate(alarmModel.getAStartDate());
		alarm.setAEndDate(alarmModel.getAEndDate());
		alarm.setARecurrence(alarmModel.getARecurrence());
		return alarm;
	}
	
	/**
	 * 
	 * @param alarms
	 * @return
	 */
	public List<ReminderModel> mapReminders(List<Alarm> alarms) {
		List<ReminderModel> alarmModels = new ArrayList<>();
		for (Alarm alarm : alarms) {
			alarmModels.add(mapReminder(alarm));
		}
		return alarmModels;
	}
	
	/**
	 * 
	 * @param alarm
	 * @return
	 */
	public ReminderModel mapReminder(Alarm alarm) {
		ReminderModel alarmModel = new ReminderModel();
//		String pillname =(alarm.getPill().getPillName());
		alarmModel.setPillName(alarm.getPill().getPillName());
		alarmModel.setUserMail(alarm.getUser().getUserEmail());
		alarmModel.setUserName(alarm.getUser().getUserName());
		return alarmModel;
	}
	
	/**
	 * 
	 * @param users
	 * @return
	 */
	public List<UserModel> mapUsers(List<User> users) {
		List<UserModel> userModels = new ArrayList<>();
		for (User user : users) {
			userModels.add(mapUser(user));
		}
		return userModels;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public UserModel mapUser(User user) {
		UserModel userModel = new UserModel();
		userModel.setUserId(user.getUserId());
		userModel.setUserName(user.getUserName());
		userModel.setUserEmail(user.getUserEmail());
		userModel.setUserContact(user.getUserContact());
		return userModel;
	}
	
	/**
	 * 
	 * @param userModel
	 * @return
	 */
	public User mapUserModel(UserModel userModel){
		User user = new User();
		user.setUserId(userModel.getUserId());
		user.setUserName(userModel.getUserName());
		user.setUserEmail(userModel.getUserEmail());
		user.setUserContact(userModel.getUserContact());
		return user;
	}
	
	/**
	 * 
	 * @param userModel
	 * @return
	 */
	public User mapAddUserModel(AddUserModel userModel){
		User user = new User();
		user.setUserName(userModel.getUserName());
		user.setUserEmail(userModel.getUserEmail());
		user.setUserContact(userModel.getUserContact());
		return user;
	}
	
}
