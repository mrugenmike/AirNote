package com.airnote.services.reminder;


public interface ReminderStorageService {
    ReminderMetaInfo storeRemainderInfo(String userId, String emailId, String eventAt, String content);
}
