package pills;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pills.models.ReminderModel;
import pills.service.AlarmService;
import pills.service.MailerService;


@Component
public class ScheduledTasks {
	
	@Autowired
	private AlarmService alarmService;

	@Autowired
	private MailerService smtpMailSender;
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	public void sendMail(String mail, String name, String pill) throws MessagingException {
			smtpMailSender.send(mail, "PillReminder", "Hi "+name+"!\n Time to take "+pill);
			}

    @Scheduled(cron="0 0 9 * * *")
    public void mailInMorning() throws MessagingException {
    	List<ReminderModel> reminders= alarmService.getByRecurrence(1);
    	for(ReminderModel reminder: reminders){
			sendMail(reminder.getUserMail(),reminder.getUserName(),reminder.getPillName());    		
    	}
//        log.info("The time is now {}", dateFormat.format(new Date()));
    }
    @Scheduled(cron="0 0 13 * * *")
    public void mailInAfternoon() throws MessagingException {
    	List<ReminderModel> reminders= alarmService.getByRecurrence(2);
    	for(ReminderModel reminder: reminders){
			sendMail(reminder.getUserMail(),reminder.getUserName(),reminder.getPillName());    		
    	}
    }
    
    @Scheduled(cron="0 0 19 * * *")
    public void mailInEvening() throws MessagingException {
    	List<ReminderModel> reminders= alarmService.getByRecurrence(3);
    	for(ReminderModel reminder: reminders){
			sendMail(reminder.getUserMail(),reminder.getUserName(),reminder.getPillName());    		
    	}
    }
//        @Scheduled(cron="*/5 * * * * *")
//        public void reporttest() {
//            log.info("test The time is now {}", dateFormat.format(new Date()));
//        }
   
}