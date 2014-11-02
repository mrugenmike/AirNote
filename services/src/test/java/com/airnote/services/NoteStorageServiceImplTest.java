package com.airnote.services;

import com.airnote.services.notes.NoteMetaInfo;
import com.airnote.services.notes.NoteStorageService;
import com.airnote.services.notes.NoteMetadata;
import com.mongodb.DBCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
@SpringApplicationConfiguration(classes = TestNoteAppConfiguration.class)
@WebAppConfiguration
public class NoteStorageServiceImplTest {

    @Autowired
    NoteStorageService noteStorageService;

    @Autowired
    DBCollection notesMetaInfo;

    @Before
    @After
    public void setup() {
        notesMetaInfo.drop();
    }

    @Test
    public void shouldFindNotesByUserId() {
 noteStorageService.storeNoteInfo(new NoteMetadata(), "myNote.txt", "1234");
        List<NoteMetaInfo> noteMetaInfos = noteStorageService.fetchAllNoteMetaInfoByUserId("1234", 10);

        assertThat(noteMetaInfos.size()).isEqualTo(1);
    }

    @Test
    public void shouldNoFindNotesByUserId() {
       noteStorageService.storeNoteInfo(new NoteMetadata(), "myNote.txt", "1234");

        List<NoteMetaInfo> noteMetaInfos = noteStorageService.fetchAllNoteMetaInfoByUserId("5678", 10);

        assertThat(noteMetaInfos.size()).isZero();
    }

}