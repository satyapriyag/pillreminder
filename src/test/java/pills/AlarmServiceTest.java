package pills;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateObjectRetrievalFailureException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import inti.ws.spring.exception.client.BadRequestException;
import pills.models.AddAlarmModel;
import pills.models.AlarmModel;
import pills.models.UserAlarmModel;
import pills.service.AlarmService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes={TestDatabaseConfig.class})
public class AlarmServiceTest{
	@Autowired
	private AlarmService alarmService;
	
	//Test Cases For Alarm Service
	
	@Test
	@Transactional
	@Rollback(true)
	public void addAlarmTest() throws BadRequestException {
		
		AddAlarmModel addAlarm = new AddAlarmModel();
		addAlarm.setAUserId(1);
		addAlarm.setAPillId(4);
		addAlarm.setARecurrence(3);
		addAlarm.setAStartDate(null);
		addAlarm.setAEndDate(null);
		// Save and Get Test
		AlarmModel alarmModel = alarmService.addAlarm(addAlarm);

		assertTrue(alarmModel.getAId() > 0);
	}
	
	@Test(expected = HibernateObjectRetrievalFailureException.class)
	@Rollback(true)
	public void deleteAlarmTest() throws BadRequestException {

		 //Save and Get Test
		AddAlarmModel addAlarm = new AddAlarmModel();
		addAlarm.setAUserId(1);
		addAlarm.setAPillId(4);
		addAlarm.setARecurrence(3);
		addAlarm.setAStartDate(null);
		addAlarm.setAEndDate(null);
		AlarmModel alarmModel = alarmService.addAlarm(addAlarm);
		assertTrue(alarmModel.getAId() > 0);

		alarmService.deleteAlarm(alarmModel.getAId());
		UserAlarmModel ualarmModel = alarmService.viewAlarm(alarmModel.getAId());

	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void getAllAlarmsTest() throws BadRequestException {
		List<AlarmModel> alarmModels = alarmService.viewAll();
		int size = alarmModels.size();

		AddAlarmModel addAlarm = new AddAlarmModel();
		addAlarm.setAUserId(1);
		addAlarm.setAPillId(2);
		addAlarm.setARecurrence(3);
		addAlarm.setAStartDate(null);
		addAlarm.setAEndDate(null);
		// Save and Get Test
		AlarmModel alarmModel = alarmService.addAlarm(addAlarm);
		assertTrue(alarmModel.getAId() > 0);

		alarmModels = alarmService.viewAll();
		assertEquals(size + 1, alarmModels.size());
	}
	


//	@Test(expected = BadRequestException.class)
//	@Transactional
//	@Rollback(true)
//	public void deleteAlarmInvalidId() throws BadRequestException {
//		alarmService.deleteAlarm(-1);
//	}
//
//
	@Test
	public void updateAlarmRecurrenceTest() throws BadRequestException {

		AlarmModel alarmModel = new AlarmModel();
		alarmModel.setAId(10);
		alarmModel.setAUserId(2);
		alarmModel.setAPillId(4);
		alarmModel.setARecurrence(3);
		alarmModel.setAStartDate(new Date());
		alarmModel.setAEndDate(new Date());
		alarmService.updateAlarm(alarmModel);
		System.out.println("test");
		UserAlarmModel ualarmModel = alarmService.viewAlarm(10);
		assertEquals(ualarmModel.getARecurrence(),3);
	}

	@Test
	public void updateAlarmPillTest() throws BadRequestException {
		AlarmModel alarmModel = new AlarmModel();
		alarmModel.setAId(1);
		alarmModel.setAUserId(1);
		alarmModel.setAPillId(4);
		alarmModel.setARecurrence(3);
		alarmModel.setAStartDate(null);
		alarmModel.setAEndDate(null);
		alarmService.updateAlarm(alarmModel);
		UserAlarmModel ualarmModel = alarmService.viewAlarm(1);

		assertEquals(Integer.valueOf(ualarmModel.getAPillId()),Integer.valueOf(4));
	}
	

}