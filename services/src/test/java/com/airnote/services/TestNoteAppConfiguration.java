package com.airnote.services;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import com.sendgrid.*;

import java.net.UnknownHostException;

@Profile("dev")
@EnableAutoConfiguration
@ComponentScan
@PropertySource(value = "classpath:/application-test.properties",ignoreResourceNotFound = false)
public class TestNoteAppConfiguration {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestNoteAppConfiguration.class, args);
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

    @Bean(name ={"reminderMetaInfo"})
    DBCollection getRemainderCollection() throws UnknownHostException {
        return getMongoClient().getDB(mongoDatabase).getCollection("reminderMetaInfo");
    }

    @Bean(name ={"sendGrid"})
    SendGrid sendGridEmail() { return new SendGrid("dummy@email.com" ,"dummy@creds");}
}
