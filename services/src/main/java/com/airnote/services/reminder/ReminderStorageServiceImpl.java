package com.airnote.services.reminder;


import com.airnote.services.notes.NoteMetaInfo;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@Service("reminderStorageService")
public class ReminderStorageServiceImpl implements ReminderStorageService {

    private DBCollection remainderMetaInfos;

    @Autowired
    public ReminderStorageServiceImpl(@Qualifier("reminderMetaInfo") DBCollection remainderMetaInfos){
        this.remainderMetaInfos = remainderMetaInfos;
    }

    @Override
    public ReminderMetaInfo storeRemainderInfo(String userId, String emailId, Date eventAt, String content) {
        final ReminderMetaInfo metaInfo = new ReminderMetaInfo(userId, emailId, eventAt, content);
        remainderMetaInfos.insert(metaInfo.asDBObject(), WriteConcern.ACKNOWLEDGED);
        return metaInfo;
    }

    @Override
    public List<ReminderMetaInfo> fetchAllRemindersByUserId(String userId, Integer skip, Integer limit) {
        DBObject byUserId = QueryBuilder.start("userId").is(userId).get();
        DBObject byEventDate = new BasicDBObject("eventAt",-1);
        return remainderMetaInfos.find(byUserId).limit(limit).skip(skip).sort(byEventDate).toArray().stream().map(info-> ReminderMetaInfo.instance(info)).collect(Collectors.toList());

    }

    @Override
    public void removeReminder(String userId, String reminderId) {
        remainderMetaInfos.remove(new BasicDBObject("_id", new ObjectId(reminderId)).append("userId",userId),WriteConcern.FSYNCED);
    }
}
