package com.airnote.services.reminder;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Maithili on 12/9/2014.
 */
@EnableAutoConfiguration
@ComponentScan
public interface ReminderEmailService {


    void sendGridEmail(String fromEmail, String toEmail, String subject, String body);
}
