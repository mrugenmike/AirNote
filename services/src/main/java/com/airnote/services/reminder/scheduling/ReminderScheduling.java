package com.airnote.services.reminder.scheduling;

import com.airnote.services.reminder.ReminderEmailService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@EnableScheduling
@Component
public class ReminderScheduling {
    private final DBCollection reminderCollection;
    private final ReminderEmailService reminderEmailService;

    @Autowired
    ReminderScheduling(@Qualifier("reminderMetaInfo")DBCollection reminderCollection,ReminderEmailService reminderEmailService){
        this.reminderCollection = reminderCollection;
        this.reminderEmailService = reminderEmailService;
    }

    @Scheduled(fixedRate = 10000)
    public void sendReminderEmails() {
        Calendar cal = Calendar.getInstance(Locale.UK);
        Date now = cal.getTime();
        int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
        cal.set(Calendar.HOUR_OF_DAY,hourOfDay+1);
        Date after1Hour = cal.getTime();
        BasicDBObject nextReminders = new BasicDBObject("eventAt",new BasicDBObject("$gt",now ).append("$lte",after1Hour)).append("emailSent",false);
        reminderCollection.find(nextReminders).toArray().stream().forEach(reminder -> {
            String content = reminder.get("content").toString();
            reminderEmailService.send("airnote273@gmail.com",reminder.get("emailId").toString(),"Reminder: "+ content.substring(0, 10),"Reminder: \n<b>"+content+"</b>\n Time:<b> "+reminder.get("eventAt")+"</b>");
            reminderCollection.findAndModify(new BasicDBObject("_id",reminder.get("_id")),new BasicDBObject("$set",new BasicDBObject("emailSent", true)));
        });


    }

}
