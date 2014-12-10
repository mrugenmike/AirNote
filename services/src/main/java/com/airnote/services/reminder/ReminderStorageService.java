package com.airnote.services.reminder;


import java.util.List;

public interface ReminderStorageService {
    ReminderMetaInfo storeRemainderInfo(String userId, String emailId, String eventAt, String content);

    List<ReminderMetaInfo> fetchAllRemindersByUserId(String userId, Integer skip, Integer limit);

    void removeReminder(String userId, String reminderId);
}
