package com.airnote.services.integration;

import com.airnote.services.notes.NoteMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Component
public class DropBoxClientImpl implements DropBoxClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${airnote.default.folder}")
    private String tagetPath;

    private final String dropBoxContentEndpoint = "https://api-content.dropbox.com/1";
    private final String authorization = "Authorization";

    @Override
    public NoteMetadata storeNote(String accessToken, String title, String content) {
        final HashMap<String, String> uriVariables = new HashMap<String,String>();
        uriVariables.put("locale","en_uk");
        final HttpHeaders headers = new HttpHeaders();
        headers.set(authorization,accessToken);
        final HttpEntity<String> stringHttpEntity = new HttpEntity<String>(content,headers);
        String filePath = "https://api-content.dropbox.com/1/files_put/auto/"+tagetPath+"/"+title.replaceAll("\\s","");
        ResponseEntity<NoteMetadata> noteUploadResponseResponseEntity = restTemplate.exchange(filePath, HttpMethod.POST,stringHttpEntity, NoteMetadata.class, uriVariables);
        return noteUploadResponseResponseEntity.getBody();
    }

    @Override
    public String fetchNoteContent(String filePath, String accessToken) throws FileContentException {
        final HttpHeaders authHeader = new HttpHeaders();
        authHeader.set(authorization,accessToken);
        try {
            ResponseEntity<String> content = restTemplate.exchange(dropBoxContentEndpoint + "/files/auto/" + filePath.trim(), HttpMethod.GET, new HttpEntity<String>(authHeader), String.class);
            return content.getBody();
        } catch (RestClientException exception) {
            throw new FileContentException(exception.getMessage());
        }
    }

    @Override
    public void deleteNote(String filePath, String accessToken) throws NoteDeletionException {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(authorization,accessToken);
        final HttpEntity<String> stringHttpEntity = new HttpEntity<String>(headers);
        try{
            UriComponentsBuilder uri = UriComponentsBuilder.newInstance().fromHttpUrl("https://api.dropbox.com/1/fileops/delete").queryParam("locale", "en_uk").queryParam("root","dropbox").queryParam("path",filePath);
            restTemplate.exchange(uri.build().toUri(), HttpMethod.POST, stringHttpEntity, String.class);
        }catch(HttpClientErrorException clientException){
            throw new NoteDeletionException(clientException.getMessage());
        }
    }
}
