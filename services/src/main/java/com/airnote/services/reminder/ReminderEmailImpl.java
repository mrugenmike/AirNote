package com.airnote.services.reminder;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReminderEmailImpl implements ReminderEmailService {
    private final SendGrid sendGrid;
    private final String template;

    @Autowired
    public ReminderEmailImpl(@Qualifier("sendGrid") SendGrid sendGrid,@Value("email.template")String template){
        this.sendGrid = sendGrid;
        this.template = template;
    }

    public void send(String fromEmail, String toEmail, String subject, String body) {
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(toEmail);
        email.setFrom(fromEmail);
        email.setSubject(subject);
        email.setText(body);
        email.setHtml(template);
        try {
            SendGrid.Response response = sendGrid.send(email);
            System.out.println(response.getMessage());
        }
        catch (SendGridException e) {
            System.err.println(e);
        }
    }
}
