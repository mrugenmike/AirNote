package com.airnote.services;

import com.airnote.services.notes.NoteMetaInfo;
import com.airnote.services.notes.NoteStorageService;
import com.airnote.services.notes.NoteUploadResponse;
import com.mongodb.DBCollection;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteStorageServiceImpl implements NoteStorageService {

    private DBCollection noteMetaInfo;

    @Autowired
    public NoteStorageServiceImpl(DBCollection noteMetaInfo){
        this.noteMetaInfo = noteMetaInfo;
    }

    @Override
    public NoteMetaInfo storeNoteInfo(NoteUploadResponse noteUploadResponse, String title, String userId) {
        final NoteMetaInfo metaInfo = new NoteMetaInfo(noteUploadResponse,title,userId);
        noteMetaInfo.insert(metaInfo.asDBObject(), WriteConcern.ACKNOWLEDGED);
        return metaInfo;
    }
}
