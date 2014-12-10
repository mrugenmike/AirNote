package com.airnote.services.reminder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Reminder {
    @JsonProperty private String reminderId;
    @JsonProperty private final String userId;
    @JsonProperty private final String emailId;
    @JsonProperty private final Date eventAt;
    @JsonProperty private final String content;
    @JsonProperty private final boolean emailSent;

    public Reminder(String reminderId,String userId, String emailId, Date eventAt, String content, boolean emailSent) {
        this.reminderId = reminderId;
        this.userId = userId;
        this.emailId = emailId;
        this.eventAt = eventAt;
        this.content = content;
        this.emailSent = emailSent;
    }
}
