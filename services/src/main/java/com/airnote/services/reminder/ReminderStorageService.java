package com.airnote.services.reminder;

import com.airnote.services.notes.NoteMetaInfo;
import com.airnote.services.notes.NoteMetadata;

import java.util.List;
import java.util.Optional;

public interface ReminderStorageService {
    NoteMetaInfo storeNoteInfo(NoteMetadata noteUploadResponse, String title, String userId);

    List<NoteMetaInfo> fetchAllNoteMetaInfoByUserId(String userId, Integer limit);

    Optional<NoteMetaInfo> fetchNoteInfoBy(String userId, String noteId);

    NoteMetaInfo updateNoteInfo(NoteMetadata noteUploadResponse, String title, String userId, String noteId);

    void deleteNoteMetaInfo(String userId, String noteId);
}
