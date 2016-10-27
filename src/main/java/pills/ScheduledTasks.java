package pills;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import inti.ws.spring.exception.client.BadRequestException;
import pills.models.ReminderModel;
import pills.service.AlarmService;
import pills.utilities.MailerService;


@Component
public class ScheduledTasks {

  @Autowired
  private AlarmService alarmService;

  @Autowired
  private MailerService smtpMailSender;

  public void sendMail(String mail, String name, String pill) throws MessagingException {
    smtpMailSender.send(mail, "PillReminder", "Hi " + name + "!\n Time to take " + pill);
  }

  @Scheduled(cron = "0 0 9 * * *")
  public void mailInMorning() throws MessagingException, BadRequestException {
    List<ReminderModel> reminders = alarmService.getByRecurrence(1);
    for (ReminderModel reminder : reminders) {
      sendMail(reminder.getUserMail(), reminder.getUserName(), reminder.getPillName());
    }
  }

  @Scheduled(cron = "0 0 13 * * *")
  public void mailInAfternoon() throws MessagingException, BadRequestException {
    List<ReminderModel> reminders = alarmService.getByRecurrence(2);
    for (ReminderModel reminder : reminders) {
      sendMail(reminder.getUserMail(), reminder.getUserName(), reminder.getPillName());
    }
  }

  @Scheduled(cron = "0 0 19 * * *")
  public void mailInEvening() throws MessagingException, BadRequestException {
    List<ReminderModel> reminders = alarmService.getByRecurrence(3);
    for (ReminderModel reminder : reminders) {
      sendMail(reminder.getUserMail(), reminder.getUserName(), reminder.getPillName());
    }
  }
  // @Scheduled(cron="*/5 * * * * *")
  // public void reporttest() {
  // log.info("test The time is now {}", dateFormat.format(new Date()));
  // }

}
