package com.airnote.services.notes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NotesMetaInfoResponse {
    private final @JsonProperty List<NoteMetaInfo> notes;
    private final @JsonProperty Long totalNotes;

    public NotesMetaInfoResponse(List<NoteMetaInfo> notes, Long totalNotes) {
        this.notes = notes;
        this.totalNotes = totalNotes;
    }
}
