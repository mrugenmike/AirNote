package com.airnote.services.reminder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class ReminderUpdationRequest {
    @JsonProperty("eventAt") @JsonDeserialize(using = CustomDateSerializer.class) Date eventAt;
    @JsonProperty("content") String content;

    public Date getEventAt() {
        return eventAt;
    }

    public String getContent() {
        return content;
    }
}

