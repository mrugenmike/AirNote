package com.airnote.services.notes;

import com.airnote.services.integration.DropBoxClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class NotesService {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    DropBoxClient dropBoxClient;

    @Autowired
    NoteStorageService storageService;

    public NoteMetaInfo createNote(NoteCreationRequest request,String accessToken) {
        final NoteUploadResponse noteUploadResponse = dropBoxClient.storeNote(accessToken, request.getTitle(), request.getContent());
        return storageService.storeNoteInfo(noteUploadResponse,request.getTitle(),request.getUserId());
    }
}
