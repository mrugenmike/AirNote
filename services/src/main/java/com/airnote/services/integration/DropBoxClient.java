package com.airnote.services.integration;

import com.airnote.services.notes.NoteUploadResponse;
import org.springframework.stereotype.Component;

@Component
public interface DropBoxClient {
    NoteUploadResponse storeNote(String accessToken,String title,String content);
}
