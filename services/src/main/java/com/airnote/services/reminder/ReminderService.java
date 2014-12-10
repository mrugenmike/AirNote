package com.airnote.services.reminder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReminderService {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ReminderStorageService storageService;

}
