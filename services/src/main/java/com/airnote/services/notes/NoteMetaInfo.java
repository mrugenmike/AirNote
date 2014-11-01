package com.airnote.services.notes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.List;

public class NoteMetaInfo {
    @JsonProperty private final String noteId = new ObjectId().toString();
    @JsonProperty private final NoteUploadResponse noteUploadResponse;
    @JsonProperty private final String title;
    @JsonProperty private final String userId;

    public NoteMetaInfo(NoteUploadResponse noteUploadResponse, String title, String userId) {
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
}
