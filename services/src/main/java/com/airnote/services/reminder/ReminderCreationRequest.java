package com.airnote.services.reminder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ReminderCreationRequest {

    @JsonProperty("userId") String userId;
    @JsonProperty("emailId") String emailId;
    @JsonProperty("eventAt") String eventAt;
    @JsonProperty("content") String content;

    public String getUserId() {
        return userId;
    }

    public String getEmailId(){
        return emailId;
    }

    public String getEventAt() {
        return eventAt;
    }

    public String getContent() {
        return content;
    }

}
