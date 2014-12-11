package com.airnote.services.reminder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component("reminderService")
public class ReminderService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired @Qualifier("reminderStorageService")
    ReminderStorageService reminderStorageService;

    ReminderMetaInfo reminderInfoRecieved;

    public ReminderMetaInfo createReminder(ReminderCreationRequest request) {
        return reminderStorageService.storeRemainderInfo(request.getUserId(), request.getEmailId(), request.getEventAt(), request.getContent(), request.isEmailSent());
    }

    public List<ReminderMetaInfo> fetchReminders(String userId, Integer skip, Integer limit) {
        return reminderStorageService.fetchAllRemindersByUserId(userId,skip,limit);
    }

    public ReminderMetaInfo fetchReminderContent(String userId, String reminderId){
        return reminderStorageService.fetchReminderInfoBy(userId, reminderId).get();
    }

    public  ReminderMetaInfo updateReminder(ReminderUpdationRequest request, String userId, String reminderId) {
        Optional<ReminderMetaInfo> reminderInfoFetched = reminderStorageService.fetchReminderInfoBy(userId, reminderId);
        if (reminderInfoFetched.isPresent()) {
            boolean eventHasChanged = !(reminderInfoFetched.get().getEventAt()).equals(request.getEventAt());
            if (eventHasChanged) {
                reminderInfoFetched.get().setEmailSent(false);
            }
            reminderInfoRecieved = reminderStorageService.updateReminderInfo(reminderInfoFetched.get(), request.getEventAt(), request.getContent());
        }
        return reminderInfoRecieved;
    }

    public void deleteReminder(String userId, String reminderId) {
        reminderStorageService.removeReminder(userId,reminderId);
    }

    public Long totalRemindersByUserId(String userId) {
        return reminderStorageService.findTotalRemindersBy(userId);
    }
}