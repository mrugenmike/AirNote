package com.airnote.services.user;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    final RestTemplate restTemplate = new RestTemplate();
    public UserDetails fetchUserDetails(String accessToken) throws IncorrectTokenException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+accessToken);
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(headers);
        try{
            return restTemplate.exchange("https://api.dropbox.com/1/account/info", HttpMethod.GET, stringHttpEntity, UserDetails.class).getBody();
        }catch(HttpClientErrorException e){
           throw new IncorrectTokenException(e.getMessage(),accessToken);
        }
    }
}


