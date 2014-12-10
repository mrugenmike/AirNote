package com.airnote.services.reminder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component("reminderService")
public class ReminderService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired @Qualifier("reminderStorageService")
    ReminderStorageService reminderStorageService;

    public ReminderMetaInfo createReminder(ReminderCreationRequest request) {
        return reminderStorageService.storeRemainderInfo(request.getUserId(), request.getEmailId(), request.getEventAt(), request.getContent());
    }

    public List<ReminderMetaInfo> fetchReminders(String userId, Integer skip, Integer limit) {
        return reminderStorageService.fetchAllRemindersByUserId(userId,skip,limit);
    }

    public void deleteReminder(String userId, String reminderId) {
        reminderStorageService.removeReminder(userId,reminderId);
    }
}
