package com.airnote.services.reminder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReminderMetaInfo {
    @JsonProperty private String remainderId = new ObjectId().toString();
    @JsonProperty private final String userId;
    @JsonProperty private final String emailId;
    @JsonProperty private final String eventAt;
    @JsonProperty private final String content;
    @JsonProperty private Date createdAt;
    @JsonProperty private Date modifiedAt;
    @JsonProperty private boolean emailSent = false;
    private Date today = Calendar.getInstance().getTime();

    public ReminderMetaInfo(String userId, String emailId, String eventAt, String content) {
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

    public String getRemainderId() {
        return remainderId;
    }

    public void setRemainderId(String id) {
        this.remainderId = id;
    }

    public DBObject asDBObject() {
        return new BasicDBObjectBuilder()
                .add("_id", remainderId)
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

        ReminderMetaInfo reminderMetaInfo = new ReminderMetaInfo(info.get("userId").toString(), info.get("emailId").toString(), info.get("eventAt").toString(), info.get("content").toString());
        reminderMetaInfo.setRemainderId(info.get("_id").toString());
        return reminderMetaInfo;
    }
}
