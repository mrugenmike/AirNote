package com.airnote.services.reminder;

import com.airnote.services.notes.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/reminder")
@EnableScheduling
public class ReminderResource {
    @Autowired @Qualifier("reminderService")
    ReminderService reminderService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderMetaInfo createNote(@RequestBody ReminderCreationRequest request){
        return reminderService.createReminder(request);
    }

    @RequestMapping(value = {"/{userId}"},method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ReminderMetaInfo> fetchReminders(@PathVariable String userId,@RequestParam(defaultValue = "0") Integer skip,@RequestParam(defaultValue = "10")Integer limit) throws NoContentException {
        List<ReminderMetaInfo> reminderMetaInfos = reminderService.fetchReminders(userId, skip, limit);
        if (reminderMetaInfos.isEmpty()){
            throw new NoContentException();
        }
        return reminderMetaInfos;
    }

    @RequestMapping(value={"/{userId}/{reminderId}"},method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ReminderMetaInfo fetchReminder(@PathVariable String userId,@PathVariable String reminderId){
        return reminderService.fetchReminderContent(userId, reminderId);
    }

    @RequestMapping(value = {"/{userId}/{reminderId}"},method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderMetaInfo updateReminder(@RequestBody ReminderUpdationRequest request, @PathVariable String userId, @PathVariable String reminderId) {
        return reminderService.updateReminder(request, userId, reminderId);
    }

    @RequestMapping(value = {"/{userId}/{reminderId}"},method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteReminder(@PathVariable String userId,@PathVariable String reminderId){
        reminderService.deleteReminder(userId,reminderId);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}
