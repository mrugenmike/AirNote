package com.airnote.services.reminder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ReminderMetaInfo {
    @JsonProperty private String reminderId = new ObjectId().toString();
    @JsonProperty private final String userId;
    @JsonProperty private final String emailId;
    @JsonProperty private final @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") Date eventAt;
    @JsonProperty private final String content;
    @JsonProperty private Date createdAt;
    @JsonProperty private Date modifiedAt;
    @JsonProperty private boolean emailSent = false;
    private Date today = Calendar.getInstance().getTime();

    public ReminderMetaInfo(String userId, String emailId, Date eventAt, String content) {
        this.userId = userId;
        this.emailId = emailId;
        this.eventAt = eventAt;
        this.content = content;
        this.createdAt = today;
        this.modifiedAt = today;
    }

    public void setModifiedAt() {
        this.modifiedAt = Calendar.getInstance().getTime();
    }

    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String id) {
        this.reminderId = id;
    }

    public DBObject asDBObject() {
        return new BasicDBObjectBuilder()
                .add("_id", reminderId)
                .add("userId",userId)
                .add("emailId",emailId)
                .add("eventAt",eventAt)
                .add("content",content)
                .add("createdAt",createdAt)
                .add("modifiedAt",modifiedAt)
                .add("emailSent",emailSent)
                .get();
    }

    public static ReminderMetaInfo instance(DBObject info) {

        ReminderMetaInfo reminderMetaInfo = new ReminderMetaInfo(info.get("userId").toString(), info.get("emailId").toString(), (Date)info.get("eventAt"), info.get("content").toString());
        reminderMetaInfo.setReminderId(info.get("_id").toString());
        return reminderMetaInfo;
    }
}
