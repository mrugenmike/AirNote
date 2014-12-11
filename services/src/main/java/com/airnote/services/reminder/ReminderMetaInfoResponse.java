package com.airnote.services.reminder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReminderMetaInfoResponse {
    private final @JsonProperty
    List<ReminderMetaInfo> reminder;
    private final @JsonProperty Long totalReminder;

    public ReminderMetaInfoResponse(List<ReminderMetaInfo> reminder, Long totalReminder) {
        this.reminder = reminder;
        this.totalReminder = totalReminder;
    }
}
