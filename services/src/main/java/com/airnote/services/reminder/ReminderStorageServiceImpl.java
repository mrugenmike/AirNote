package com.airnote.services.reminder;


import com.airnote.services.notes.NoteMetaInfo;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

@Service("reminderStorageService")
public class ReminderStorageServiceImpl implements ReminderStorageService {

    private DBCollection reminderMetaInfos;

    @Autowired
    public ReminderStorageServiceImpl(@Qualifier("reminderMetaInfo") DBCollection remainderMetaInfos){
        this.reminderMetaInfos = remainderMetaInfos;
    }

    @Override
    public ReminderMetaInfo storeRemainderInfo(String userId, String emailId, Date eventAt, String content, boolean emailSent) {
        final ReminderMetaInfo metaInfo = new ReminderMetaInfo(userId, emailId, eventAt, content, emailSent);
        reminderMetaInfos.insert(metaInfo.asDBObject(), WriteConcern.ACKNOWLEDGED);
        return metaInfo;
    }

    @Override
    public List<ReminderMetaInfo> fetchAllRemindersByUserId(String userId, Integer skip, Integer limit) {
        DBObject byUserId = QueryBuilder.start("userId").is(userId).get();
        DBObject byEventDate = new BasicDBObject("eventAt",-1);
        return reminderMetaInfos.find(byUserId).limit(limit).skip(skip).sort(byEventDate).toArray().stream().map(info-> ReminderMetaInfo.instance(info)).collect(Collectors.toList());

    }

    @Override
    public Optional<ReminderMetaInfo> fetchReminderInfoBy(String userId, String reminderId) {
        DBObject byReminderIdAndUserId = QueryBuilder.start("_id").is(reminderId).and("userId").is(userId).get();
        DBObject reminderMetaInfoOne = reminderMetaInfos.findOne(byReminderIdAndUserId);
        if (reminderMetaInfoOne!=null){
            return Optional.of(ReminderMetaInfo.instance(reminderMetaInfoOne));
        }
        return Optional.empty();
    }

    @Override
    public ReminderMetaInfo updateReminderInfo(ReminderMetaInfo reminderInfoFetched, Date eventAt, String content){
        final ReminderMetaInfo metaInfo = new ReminderMetaInfo(reminderInfoFetched.getUserId(),reminderInfoFetched.getEmailId(), eventAt, content, reminderInfoFetched.isEmailSent());
        metaInfo.setReminderId(reminderInfoFetched.getReminderId());
        DBObject byReminderIdAndUserId = QueryBuilder.start("_id").is(reminderInfoFetched.getReminderId()).and("userId").is(reminderInfoFetched.getUserId()).get();
        reminderMetaInfos.update(byReminderIdAndUserId, metaInfo.asDBObject(), false, false, WriteConcern.ACKNOWLEDGED);
        return metaInfo;
    }

    @Override
    public void removeReminder(String userId, String reminderId) {
        reminderMetaInfos.remove(new BasicDBObject("_id", new ObjectId(reminderId)).append("userId",userId),WriteConcern.FSYNCED);
    }

    @Override
    public Long findTotalRemindersBy(String userId) {
        return reminderMetaInfos.count(new BasicDBObject("userId",userId));
    }

}
