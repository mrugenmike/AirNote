package com.airnote.services.reminder;


import com.mongodb.DBCollection;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("reminderStorageService")
public class ReminderStorageServiceImpl implements ReminderStorageService {
    private DBCollection remainderMetaInfos;

    @Autowired
    public ReminderStorageServiceImpl(@Qualifier("reminderMetaInfo") DBCollection remainderMetaInfos){
        this.remainderMetaInfos = remainderMetaInfos;
    }

    @Override
    public ReminderMetaInfo storeRemainderInfo(String userId, String emailId, String eventAt, String content) {
        final ReminderMetaInfo metaInfo = new ReminderMetaInfo(userId, emailId, eventAt, content);
        remainderMetaInfos.insert(metaInfo.asDBObject(), WriteConcern.ACKNOWLEDGED);
        return metaInfo;
    }
}
