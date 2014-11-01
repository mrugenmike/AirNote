package com.airnote.services.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/notes")
public class NotesResourse {
    @Autowired
    NotesService notesService;
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public NoteMetaInfo createNote(@RequestBody NoteCreationRequest request,@RequestHeader("Authorization") String accessToken){
       return notesService.createNote(request,accessToken);
    }
}
