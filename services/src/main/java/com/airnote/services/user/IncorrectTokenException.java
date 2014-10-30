package com.airnote.services.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncorrectTokenException extends Exception {
    private final String message;
    private final String accessToken;

    public IncorrectTokenException(String message, String accessToken) {
        super(message);
        this.message = message;
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
