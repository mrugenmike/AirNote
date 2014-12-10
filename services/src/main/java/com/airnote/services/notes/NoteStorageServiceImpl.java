package com.airnote.services.notes;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("mongoNoteStorageService")
public class NoteStorageServiceImpl implements NoteStorageService {

    private DBCollection noteMetaInfos;

    @Autowired
    public NoteStorageServiceImpl(@Qualifier("noteMetaInfo") DBCollection noteMetaInfos){
        this.noteMetaInfos = noteMetaInfos;
    }

    @Override
    public NoteMetaInfo storeNoteInfo(NoteMetadata noteUploadResponse, String title, String userId) {
        final NoteMetaInfo metaInfo = new NoteMetaInfo(noteUploadResponse,title,userId);
        noteMetaInfos.insert(metaInfo.asDBObject(), WriteConcern.ACKNOWLEDGED);
        return metaInfo;
    }

    @Override
    public List<NoteMetaInfo> fetchAllNoteMetaInfoByUserId(String userId, Integer limit, Integer skip) {
        DBObject byUserId = QueryBuilder.start("userId").is(userId).get();
        DBObject byUpdatedDate = new BasicDBObject("modified",-1);
        return noteMetaInfos.find(byUserId).limit(limit).skip(skip).sort(byUpdatedDate).toArray().stream().map(info -> NoteMetaInfo.instance(info)).collect(Collectors.toList());
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
    public NoteMetaInfo updateNoteInfo(NoteMetadata noteUpdateResponse, String title, String userId, String noteId){
        final NoteMetaInfo metaInfo = new NoteMetaInfo(noteUpdateResponse,title,userId);
        metaInfo.setNoteId(noteId);
        DBObject byNoteIdAndUserId = QueryBuilder.start("_id").is(noteId).and("userId").is(userId).get();
        noteMetaInfos.update(byNoteIdAndUserId, metaInfo.asDBObject(), false, false, WriteConcern.ACKNOWLEDGED);
        return metaInfo;
    }

    @Override
    public void deleteNoteMetaInfo(String userId, String noteId) {
        DBObject byIdandUserId = QueryBuilder.start("_id").is(noteId).and("userId").is(userId).get();
        noteMetaInfos.remove(byIdandUserId,WriteConcern.ACKNOWLEDGED);
    }

    @Override
    public Long findTotalNotesBy(String userId) {
        return noteMetaInfos.count(new BasicDBObject("userId",userId));
    }
}
