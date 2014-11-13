package com.airnote.services.integration;

import com.airnote.services.notes.Note;
import com.airnote.services.notes.NoteMetadata;
import org.springframework.stereotype.Component;

@Component
public interface DropBoxClient {
    NoteMetadata storeNote(String accessToken,String title,String content);

    String fetchNoteContent(String filePath, String accessToken) throws FileContentException;

    void deleteNote(String filePath, String accessToken) throws NoteDeletionException;
}
