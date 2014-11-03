package com.airnote.services.notes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

public class NoteMetaInfo {
    @JsonProperty private final String noteId = new ObjectId().toString();
    @JsonProperty private final NoteMetadata noteUploadResponse;
    @JsonProperty private final String title;
    @JsonProperty private final String userId;

    public String getNoteId() {
        return noteId;
    }

    public NoteMetadata getNoteUploadResponse() {
        return noteUploadResponse;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public NoteMetaInfo(NoteMetadata noteUploadResponse, String title, String userId) {
        this.noteUploadResponse = noteUploadResponse;
        this.title = title;
        this.userId = userId;
    }

    public DBObject asDBObject() {
        return new BasicDBObjectBuilder()
                .add("_id", noteId)
                .add("title",title)
                .add("userId",userId)
                .add("bytes",noteUploadResponse.getBytes())
                .add("modified",noteUploadResponse.getModified())
                .add("isDir",noteUploadResponse.getIs_dir())
                .add("size",noteUploadResponse.getSize())
                .add("path",noteUploadResponse.getPath())
                .add("root",noteUploadResponse.getRoot())
                .add("mimeType",noteUploadResponse.getMime_type())
                .add("clientMTime",noteUploadResponse.getClient_mtime())
                .add("thumbExists",noteUploadResponse.getThumb_exists())
                .add("rev",noteUploadResponse.getRev())
                .get();
    }

    public static NoteMetaInfo instance(DBObject info) {
        final NoteMetadata noteUploadResponse = new NoteMetadata();
        noteUploadResponse.setBytes((Integer) info.get("bytes"));
        noteUploadResponse.setModified((Date) info.get("modified"));
        noteUploadResponse.setIs_dir((Boolean) info.get("isDir"));
        noteUploadResponse.setSize((String) info.get("size"));
        noteUploadResponse.setPath((String) info.get("path"));
        noteUploadResponse.setRoot((String) info.get("root"));
        noteUploadResponse.setMime_type((String) info.get("mimeType"));
        noteUploadResponse.setClient_mtime((Date) info.get("clientMTime"));
        noteUploadResponse.setThumb_exists((Boolean) info.get("thumbExists"));
        noteUploadResponse.setRev((String) info.get("rev"));

        return new NoteMetaInfo(noteUploadResponse,info.get("title").toString(),info.get("userId").toString());
    }

    public String getFilePath() {
        return noteUploadResponse.getPath();
    }
}
