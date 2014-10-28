package com.airnote.services.notes;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/notes")
public class NotesResourse {

    NotesService notesService;
    @RequestMapping(method = RequestMethod.POST)
    public String createNote(@RequestBody NoteCreationRequest request){
       notesService.createNote(request);
       return "";
    }
}
