package com.airnote.services.notes;

public class NoteCreationRequest {
String accessToken;
String content;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getContent() {
        return content;
    }
}
