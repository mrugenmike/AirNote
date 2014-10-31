package com.airnote.services;

import com.airnote.services.notes.NoteMetaInfo;
import com.airnote.services.notes.NoteStorageService;
import com.airnote.services.notes.NoteUploadResponse;
import org.springframework.stereotype.Service;

@Service
public class NoteStorageServiceImpl implements NoteStorageService {

    public NoteStorageServiceImpl(){

    }

    @Override
    public NoteMetaInfo storeNoteInfo(NoteUploadResponse noteUploadResponse) {

        return null;
    }
}
