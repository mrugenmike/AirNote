package com.airnote.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    public UserDetails fetchUserDetails(String accessToken) throws IncorrectTokenException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",accessToken);
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(headers);
        try{
            return restTemplate.exchange("https://api.dropbox.com/1/account/info", HttpMethod.GET, stringHttpEntity, UserDetails.class).getBody();
        }catch(HttpClientErrorException e){
           throw new IncorrectTokenException(e.getMessage(),accessToken);
        }
    }
}


