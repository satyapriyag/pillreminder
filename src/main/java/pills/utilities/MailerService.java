package pills.utilities;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import pills.service.MailContentBuilder;

@Component
public class MailerService {

  @Autowired
  private JavaMailSender javaMailSender;
  @Autowired
  private MailContentBuilder mailContentBuilder;

  public void send(String to, String subject, String body, String name) throws MessagingException {

    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper;

    helper = new MimeMessageHelper(message, true); // true indicates
                                                   // multipart message
    helper.setSubject(subject);
    helper.setTo(to);
    String content = mailContentBuilder.build(body, name);
    helper.setText(content, true); // true indicates html
    helper.setFrom("PillReminder");

    javaMailSender.send(message);


  }

}
