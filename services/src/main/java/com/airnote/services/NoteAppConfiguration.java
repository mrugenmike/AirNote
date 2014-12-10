package com.airnote.services;

import com.airnote.services.reminder.ReminderEmailImpl;
import com.airnote.services.reminder.ReminderEmailService;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;


@EnableAutoConfiguration
@ComponentScan
@PropertySource(value = "classpath:/app.properties",ignoreResourceNotFound = false)
public class NoteAppConfiguration {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NoteAppConfiguration.class, args);
    }

    @Value("${mongo.database.url}")
    String mongoUrl;

    @Value("${mongo.database.name}")
    String mongoDatabase;

    @Bean
    RestTemplate getRestTemplate(){
       return new RestTemplate();
    }

    @Bean
    MongoClient getMongoClient() throws UnknownHostException {
       return new MongoClient(new MongoClientURI(mongoUrl));
    }

    @Bean(name={"noteMetaInfo"})
    DBCollection getNotesCollection() throws UnknownHostException {
        return getMongoClient().getDB(mongoDatabase).getCollection("noteMetaInfo");
    }


    @Bean(name={"reminderMetaInfo"})
    DBCollection getReminderCollection() throws UnknownHostException {
        return getMongoClient().getDB(mongoDatabase).getCollection("reminderMetaInfo");
    }

    @Bean
    SendGrid sendGridEmail() { return new SendGrid("mrugen.deshmukh@sjsu.edu" ,"airnote@123");}

}

