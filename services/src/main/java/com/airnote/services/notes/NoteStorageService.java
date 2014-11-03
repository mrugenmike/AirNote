package com.airnote.services.notes;

import java.util.List;
import java.util.Optional;

public interface NoteStorageService {
    NoteMetaInfo storeNoteInfo(NoteMetadata noteUploadResponse, String title, String userId);

    List<NoteMetaInfo> fetchAllNoteMetaInfoByUserId(String userId, Integer limit);

    Optional<NoteMetaInfo> fetchNoteInfoBy(String userId, String noteId);
}
