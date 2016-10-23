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
import pills.models.UserAlarmModel;
import pills.models.UserModel;
import pills.service.PillService;

@Service
public class MappingUtility {

	@Autowired
	PillService service ;
	
	public List<CategoryModel> mapCategories(List<Category> categories) {
		List<CategoryModel> categoryModels = new ArrayList<>();
		for (Category category : categories) {
			categoryModels.add(mapCategory(category));
		}
		return categoryModels;
	}
	
	public CategoryModel mapCategory(Category category) {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setCategoryId(category.getCategoryId());
		categoryModel.setCategoryName(category.getCategoryName());
		return categoryModel;
	}
	
	public Category mapCategoryModel(CategoryModel categoryModel){
		Category category = new Category();
		category.setCategoryId(categoryModel.getCategoryId());
		category.setCategoryName(categoryModel.getCategoryName());
		return category;
	}
	
	public List<PillModel> mapAlternatives(List<Alternative> alternatives){
		List<PillModel> pills = new ArrayList<>();
		for(Alternative alternative:alternatives){
			System.out.println("alt id "+alternative.getAltId());
			System.out.println(alternative.getPillByAlternatePillId().getPillName());
			pills.add(mapPill(alternative.getPillByAlternatePillId()));
		}
		return pills;
		
	}
	public List<PillModel> mapPills(List<Pill> pills) {
		List<PillModel> pillModels = new ArrayList<>();
		for (Pill pill : pills) {
			pillModels.add(mapPill(pill));
		}
		return pillModels;
	}
	
	public PillModel mapPill(Pill pill) {
		PillModel pillModel = new PillModel();
		pillModel.setPillId(pill.getPillId());
		pillModel.setPillName(pill.getPillName());
		pillModel.setPillCategoryId(pill.getCategory().getCategoryId());
		return pillModel;
	}
	
	public Pill mapPillModel(PillModel pillModel){
		Pill pill = new Pill();
		pill.setPillId(pillModel.getPillId());
		pill.setPillName(pillModel.getPillName());
		pill.setCategory(new Category(pillModel.getPillCategoryId()));
		return pill;
	}
	
	public Pill mapAddPillModel(AddPillModel pillModel){
		Pill pill = new Pill();
		pill.setPillName(pillModel.getPillName());
		pill.setCategory(new Category(pillModel.getPillCategoryId()));
		return pill;
	}
	public List<AlarmModel> mapAlarms(List<Alarm> alarms) {
		List<AlarmModel> alarmModels = new ArrayList<>();
		for (Alarm alarm : alarms) {
			alarmModels.add(mapAlarm(alarm));
		}
		return alarmModels;
	}
	
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
	
	public List<UserAlarmModel> mapAlarmsForUser(List<Alarm> alarms) {
		List<UserAlarmModel> alarmModels = new ArrayList<>();
		for (Alarm alarm : alarms) {
			alarmModels.add(mapAlarmForUser(alarm));
		}
		return alarmModels;
	}
	
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
	
	public Alarm mapAddAlarmModel(AddAlarmModel alarmModel){
		Alarm alarm = new Alarm();
		System.out.println(alarmModel.getAPillId());
		/*PillModel pillModel = service.viewPill(alarmModel.getPillId());//new Pill(alarmModel.getPillId());
		Pill pill = new Pill();
		pill.setPillName(pillModel.getPillName());
		pill.setCategory(new Category(pillModel.getPillCategoryId()));
		System.out.println(pill.getPillName()+" "+alarmModel.getPillId());*/
		alarm.setUser(new User(alarmModel.getAUserId()));
		alarm.setPill(new Pill(alarmModel.getAPillId()));
		
		alarm.setAStartDate(alarmModel.getAStartDate());
		alarm.setAEndDate(alarmModel.getAEndDate());
		alarm.setARecurrence(alarmModel.getARecurrence());
		return alarm;
	}
	
	public List<UserModel> mapUsers(List<User> users) {
		List<UserModel> userModels = new ArrayList<>();
		for (User user : users) {
			userModels.add(mapUser(user));
		}
		return userModels;
	}
	
	public UserModel mapUser(User user) {
		UserModel userModel = new UserModel();
		userModel.setUserId(user.getUserId());
		userModel.setUserName(user.getUserName());
		userModel.setUserEmail(user.getUserEmail());
		userModel.setUserContact(user.getUserContact());
		return userModel;
	}
	
	public User mapUserModel(UserModel userModel){
		User user = new User();
		user.setUserId(userModel.getUserId());
		user.setUserName(userModel.getUserName());
		user.setUserEmail(userModel.getUserEmail());
		user.setUserContact(userModel.getUserContact());
		return user;
	}
	
	public User mapAddUserModel(AddUserModel userModel){
		User user = new User();
		user.setUserName(userModel.getUserName());
		user.setUserEmail(userModel.getUserEmail());
		user.setUserContact(userModel.getUserContact());
		return user;
	}
	
}
