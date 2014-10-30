package com.airnote.services.notes;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.dropbox.core.DbxClient;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;


@Component
public class NotesService {
    //@Autowired
    RestTemplate restTemplate = new RestTemplate();

    final private String targetPath = "/AirNote";

    public void createNote(NoteCreationRequest request) {

        HashMap<String, String> uriVariables = new HashMap<String,String>();
        ResponseEntity<NoteUploadResponse> noteUploadResponseResponseEntity = restTemplate.postForEntity("https://api-content.dropbox.com/1/files_put/auto/AirNote/some.txt", request.getContent(), NoteUploadResponse.class, uriVariables);
        NoteUploadResponse body = noteUploadResponseResponseEntity.getBody();
    }
}
