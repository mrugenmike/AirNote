package com.airnote.services.reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/remainder")
public class ReminderResource {
    @Autowired @Qualifier("reminderService")
    ReminderService reminderService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderMetaInfo createNote(@RequestBody ReminderCreationRequest request){
        return reminderService.createReminder(request);
    }

}
