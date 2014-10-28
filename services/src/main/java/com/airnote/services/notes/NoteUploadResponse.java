package com.airnote.services.notes;

import java.util.Date;

/**
 * Created by Ramya on 10/25/2014.
 */
public class NoteUploadResponse {
    public String size;
    public String rev;
    public Boolean thumb_exists;
    public Integer bytes;
    public Date modified;
    public String path;
    public Boolean is_dir;
    public String icon;
    public String root;
    public String mime_type;
    public Integer revision;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public Boolean getThumb_exists() {
        return thumb_exists;
    }

    public void setThumb_exists(Boolean thumb_exists) {
        this.thumb_exists = thumb_exists;
    }

    public Integer getBytes() {
        return bytes;
    }

    public void setBytes(Integer bytes) {
        this.bytes = bytes;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getIs_dir() {
        return is_dir;
    }

    public void setIs_dir(Boolean is_dir) {
        this.is_dir = is_dir;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }
}
/**
 * "size": "225.4KB",
 "rev": "35e97029684fe",
 "thumb_exists": false,
 "bytes": 230783,
 "modified": "Tue, 19 Jul 2011 21:55:38 +0000",
 "path": "/Getting_Started.pdf",
 "is_dir": false,
 "icon": "page_white_acrobat",
 "root": "dropbox",
 "mime_type": "application/pdf",
 "revision": 220823
 * */