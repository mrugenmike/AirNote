package com.airnote.services.notes;

public interface NoteStorageService {
    NoteMetaInfo storeNoteInfo(NoteUploadResponse noteUploadResponse, String title, String userId);
}
