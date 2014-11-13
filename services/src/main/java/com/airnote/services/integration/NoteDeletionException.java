package com.airnote.services.integration;

import com.airnote.services.notes.Note;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoteDeletionException extends Exception {
    private final String message;

    public NoteDeletionException(String message){
        super(message);
        this.message = message;
    }
}

