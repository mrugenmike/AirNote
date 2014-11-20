package com.airnote.services.notes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteUpdationRequest {

        @JsonProperty("content") String content;
        @JsonProperty("title") String title;
        @JsonProperty("userId") String userId;
        @JsonProperty("noteId") String noteId;

        public String getUserId() {
            return userId;
        }

        public String getContent() {
            return content;
        }

        public String getTitle(){
            return title;
        }

        public String getNoteId() {
            return noteId;
        }

}
