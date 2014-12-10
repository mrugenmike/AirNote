package com.airnote.services.notes;

import com.airnote.services.integration.DropBoxClient;
import com.airnote.services.integration.FileContentException;
import com.airnote.services.integration.NoteDeletionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Component
public class NotesService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DropBoxClient dropBoxClient;

    @Autowired
    NoteStorageService storageService;

    NoteMetaInfo noteInfoRecieved;

    public NoteMetaInfo createNote(NoteCreationRequest request, String accessToken) {
        final NoteMetadata noteUploadResponse = dropBoxClient.storeNote(accessToken, request.getTitle(), request.getContent());
        return storageService.storeNoteInfo(noteUploadResponse, request.getTitle(), request.getUserId());
    }

    public List<NoteMetaInfo> fetchNotesMetaInfo(String userId, Integer limit, Integer skip) {
        return storageService.fetchAllNoteMetaInfoByUserId(userId, limit,skip);
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

    public  NoteMetaInfo updateNote(NoteUpdationRequest request, String userId, String noteId, String accessToken) throws NoteNotFoundException {
        Optional<NoteMetaInfo> noteInfoFetched = storageService.fetchNoteInfoBy(userId, noteId);
        if(noteInfoFetched.isPresent()) {
            boolean titleHasChanged = !(noteInfoFetched.get().getTitle()).equals(request.getTitle());
            if (titleHasChanged) {
                try {
                    dropBoxClient.deleteNote(noteInfoFetched.get().getFilePath(), accessToken);
                } catch (NoteDeletionException e) {
                    e.printStackTrace();
                }
                final NoteMetadata noteUploadResponse = dropBoxClient.storeNote(accessToken, request.getTitle(), request.getContent());
                noteInfoRecieved = storageService.updateNoteInfo(noteUploadResponse, request.getTitle(), userId, noteId);
            } else {
                NoteMetadata noteUpdateResponse = dropBoxClient.updateNote(noteInfoFetched.get().getFilePath(), accessToken, request.getContent());
                noteInfoRecieved = storageService.updateNoteInfo(noteUpdateResponse, request.getTitle(), userId, noteId);
            }
        } else {
            throw new NoteNotFoundException();
        }
        return noteInfoRecieved;
    }

    public void deleteNote(String userId, String noteId, String accessToken) throws NoteDeletionException {
        Optional<NoteMetaInfo> noteMetaInfo = storageService.fetchNoteInfoBy(userId, noteId);
        if (noteMetaInfo.isPresent()){
            dropBoxClient.deleteNote(noteMetaInfo.get().getFilePath(), accessToken);
            storageService.deleteNoteMetaInfo(userId, noteId);
        }else {
            throw new NoteDeletionException("No Note Found with id "+noteId);
        }
    }

    public Long totalNotesByUserId(String userId) {
        return storageService.findTotalNotesBy(userId);
    }
}
