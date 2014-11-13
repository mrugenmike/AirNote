package com.airnote.services.notes;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("mongoStorageService")
public class NoteStorageServiceImpl implements NoteStorageService {

    private DBCollection noteMetaInfos;

    @Autowired
    public NoteStorageServiceImpl(DBCollection noteMetaInfos){
        this.noteMetaInfos = noteMetaInfos;
    }

    @Override
    public NoteMetaInfo storeNoteInfo(NoteMetadata noteUploadResponse, String title, String userId) {
        final NoteMetaInfo metaInfo = new NoteMetaInfo(noteUploadResponse,title,userId);
        noteMetaInfos.insert(metaInfo.asDBObject(), WriteConcern.ACKNOWLEDGED);
        return metaInfo;
    }

    @Override
    public List<NoteMetaInfo> fetchAllNoteMetaInfoByUserId(String userId, Integer limit) {
        DBObject byUserId = QueryBuilder.start("userId").is(userId).get();
        return noteMetaInfos.find(byUserId).limit(limit).toArray().stream().map(info-> NoteMetaInfo.instance(info)).collect(Collectors.toList());
    }

    @Override
    public Optional<NoteMetaInfo> fetchNoteInfoBy(String userId, String noteId) {
        DBObject byNoteIdAndUserId = QueryBuilder.start("_id").is(noteId).and("userId").is(userId).get();
        DBObject noteMetaInfoOne = noteMetaInfos.findOne(byNoteIdAndUserId);
        if (noteMetaInfoOne!=null){
            return Optional.of(NoteMetaInfo.instance(noteMetaInfoOne));
        }
        return Optional.empty();
    }

    @Override
    public void deleteNoteMetaInfo(String userId, String noteId) {
        DBObject byIdandUserId = QueryBuilder.start("_id").is(noteId).and("userId").is(userId).get();
        noteMetaInfos.remove(byIdandUserId,WriteConcern.ACKNOWLEDGED);
    }
}
