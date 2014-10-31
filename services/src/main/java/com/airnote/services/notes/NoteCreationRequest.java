package com.airnote.services.notes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NoteCreationRequest {

    @JsonProperty("content") String content;
    @JsonProperty("title") String title;
    @JsonProperty("creationTime")Date creationTime;

    public Date getCreationTime() {
        return creationTime;
    }

    public String getContent() {
        return content;
    }

    public String getTitle(){
        return title;
    }
}
