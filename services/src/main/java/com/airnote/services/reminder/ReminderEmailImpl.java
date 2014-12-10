package com.airnote.services.reminder;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ReminderEmailImpl implements ReminderEmailService {
    private final SendGrid sendGrid;

    @Autowired
    public ReminderEmailImpl(@Qualifier("sendGrid") SendGrid sendGrid){
        this.sendGrid = sendGrid;
    }

    public void send(String fromEmail, String toEmail, String subject, String body) {
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(toEmail);
        email.setFrom(fromEmail);
        email.setSubject(subject);
        email.setText(body);
        email.setHtml("<b>"+body+"</b>");
        try {
            SendGrid.Response response = sendGrid.send(email);
            System.out.println(response.getMessage());
        }
        catch (SendGridException e) {
            System.err.println(e);
        }
    }
}
