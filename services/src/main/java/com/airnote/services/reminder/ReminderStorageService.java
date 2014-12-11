package com.airnote.services.reminder;

import java.util.List;
import java.util.Date;
import java.util.Optional;

public interface ReminderStorageService {
    ReminderMetaInfo storeRemainderInfo(String userId, String emailId, Date eventAt, String content, boolean emailSent);

    List<ReminderMetaInfo> fetchAllRemindersByUserId(String userId, Integer skip, Integer limit);

    Optional<ReminderMetaInfo> fetchReminderInfoBy(String userId, String reminderId);

    ReminderMetaInfo updateReminderInfo(ReminderMetaInfo reminderInfoFetched, Date eventAt, String content);

    void removeReminder(String userId, String reminderId);

    Long findTotalRemindersBy(String userId);
}
