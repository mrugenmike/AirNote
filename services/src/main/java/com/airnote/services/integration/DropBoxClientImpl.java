package com.airnote.services.integration;

import com.airnote.services.notes.NoteUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class DropBoxClientImpl implements DropBoxClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${airnote.default.folder}")
    private String tagetPath;

    @Override
    public NoteUploadResponse storeNote(String accessToken, String title, String content) {
        final HashMap<String, String> uriVariables = new HashMap<String,String>();
        uriVariables.put("locale","en_uk");
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",accessToken);
        final HttpEntity<String> stringHttpEntity = new HttpEntity<String>(content,headers);
        String filePath = "https://api-content.dropbox.com/1/files_put/auto/"+tagetPath+"/"+title.replaceAll("\\s","");
        ResponseEntity<NoteUploadResponse> noteUploadResponseResponseEntity = restTemplate.exchange(filePath, HttpMethod.POST,stringHttpEntity, NoteUploadResponse.class, uriVariables);
        return noteUploadResponseResponseEntity.getBody();
    }
}
