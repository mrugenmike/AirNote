package com.airnote.services.notes;

import java.util.List;
import java.util.Optional;

public interface NoteStorageService {
    NoteMetaInfo storeNoteInfo(NoteMetadata noteUploadResponse, String title, String userId);

    List<NoteMetaInfo> fetchAllNoteMetaInfoByUserId(String userId, Integer limit, Integer skip);

    Optional<NoteMetaInfo> fetchNoteInfoBy(String userId, String noteId);

    NoteMetaInfo updateNoteInfo(NoteMetadata noteUploadResponse, String title, String userId, String noteId);

    void deleteNoteMetaInfo(String userId, String noteId);
}
