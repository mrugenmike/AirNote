package com.airnote.services.notes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.DBObject;

import java.util.Date;

public class NoteMetadata {
    @JsonProperty("size") String size;
    @JsonProperty("rev") String rev;
    @JsonProperty("thumb_exists") Boolean thumb_exists;
    @JsonProperty("bytes") Integer bytes;
    @JsonProperty("modified") Date modified;
    @JsonProperty("path") String path;
    @JsonProperty("is_dir") Boolean is_dir;
    @JsonProperty("icon") String icon;
    @JsonProperty("root") String root;
    @JsonProperty("mime_type") String mime_type;
    @JsonProperty("revision") Integer revision;
    @JsonProperty("client_mtime") Date client_mtime;

    public String getSize() {
        return size;
    }

    public String getRev() {
        return rev;
    }

    public Boolean getThumb_exists() {
        return thumb_exists;
    }

    public Integer getBytes() {
        return bytes;
    }

    public Date getModified() {
        return modified;
    }

    public String getPath() {
        return path;
    }

    public Boolean getIs_dir() {
        return is_dir;
    }

    public String getIcon() {
        return icon;
    }

    public String getRoot() {
        return root;
    }

    public String getMime_type() {
        return mime_type;
    }

    public Integer getRevision() {
        return revision;
    }

    public Date getClient_mtime() {
        return client_mtime;
    }

    public static NoteMetadata instance(DBObject info) {
        final NoteMetadata noteUploadResponse = new NoteMetadata();
        noteUploadResponse.setBytes((Integer) info.get("bytes"));
        noteUploadResponse.setSize((String) info.get("size"));
        noteUploadResponse.setRev((String) info.get("rev"));
        noteUploadResponse.setRev((String) info.get("rev"));
        return noteUploadResponse;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public void setThumb_exists(Boolean thumb_exists) {
        this.thumb_exists = thumb_exists;
    }

    public void setBytes(Integer bytes) {
        this.bytes = bytes;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setIs_dir(Boolean is_dir) {
        this.is_dir = is_dir;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public void setClient_mtime(Date client_mtime) {
        this.client_mtime = client_mtime;
    }
}

/**
 * {"revision": 188,
 * "bytes": 1546,
 * "thumb_exists": false,
 * "rev": "bc2c1cd4dd",
 * "modified": "Fri, 31 Oct 2014 10:40:37 +0000",
 * "mime_type": "text/plain",
 * "path": "/Airnote/text.txt",
 * "is_dir": false, "size": "1.5 KB",
 * "root": "dropbox",
 * "client_mtime": "Fri, 31 Oct 2014 10:40:37 +0000",
 * "icon": "page_white_text"}
 * */