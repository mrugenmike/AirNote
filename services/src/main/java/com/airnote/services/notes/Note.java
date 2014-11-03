package com.airnote.services.notes;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Note {
    @JsonProperty private final String userId;
    @JsonProperty private final String noteId;
    @JsonProperty private final String content;
    @JsonProperty private final String title;

    public Note(String userId, String noteId, String content, String title) {
        this.userId = userId;
        this.noteId = noteId;
        this.content = content;
        this.title = title;
    }
}
