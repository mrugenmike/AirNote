package com.airnote.services.notes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteUpdationRequest {

        @JsonProperty("content") String content;
        @JsonProperty("title") String title;

        public String getContent() {
            return content;
        }

        public String getTitle(){
            return title;
        }

}
