package com.airnote.services.reminder;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Maithili on 12/9/2014.
 */
public class ReminderEmailImpl implements ReminderEmailService {
    private final SendGrid sendGrid;

    @Autowired
    public ReminderEmailImpl(SendGrid sendGrid){
        this.sendGrid = sendGrid;
    }

    @Autowired
    public void sendGridEmail(String fromEmail, String toEmail, String subject, String body) {
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(toEmail);
        email.setFrom(fromEmail);
        email.setSubject(subject);
        email.setText(body);
        try {
            SendGrid.Response response = sendGrid.send(email);
            System.out.println(response.getMessage());
        }
        catch (SendGridException e) {
            System.err.println(e);
        }
    }
}
