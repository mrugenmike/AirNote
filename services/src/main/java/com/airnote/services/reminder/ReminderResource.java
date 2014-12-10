package com.airnote.services.reminder;

import com.airnote.services.notes.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RestController
@RequestMapping("api/remainder")
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

    //Scheduler component
     private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        @Scheduled(fixedRate = 5)
        public void reportCurrentTime() {
            System.out.println("The time is now " + dateFormat.format(new Date()));
        }

}
