package com.airnote.services.notes;

import com.airnote.services.integration.DropBoxClient;
import com.airnote.services.integration.FileContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Component
public class NotesService {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    DropBoxClient dropBoxClient;

    @Autowired
    NoteStorageService storageService;

    public NoteMetaInfo createNote(NoteCreationRequest request, String accessToken) {
        final NoteMetadata noteUploadResponse = dropBoxClient.storeNote(accessToken, request.getTitle(), request.getContent());
        return storageService.storeNoteInfo(noteUploadResponse, request.getTitle(), request.getUserId());
    }

    public List<NoteMetaInfo> fetchNotesMetaInfo(String userId, Integer limit) {
        return storageService.fetchAllNoteMetaInfoByUserId(userId, limit);
    }

    public Optional<Note> fetchNote(String userId, String noteId, String accessToken){
        return storageService.fetchNoteInfoBy(userId, noteId).flatMap(noteMetaInfo -> {
            try {
                String content = dropBoxClient.fetchNoteContent(noteMetaInfo.getFilePath(), accessToken);
                return (!content.isEmpty()) ? Optional.of(new Note(userId,noteId,content,noteMetaInfo.getTitle())):Optional.empty();
            } catch (FileContentException e) {
                return Optional.empty();
            }
        });
    }
}
