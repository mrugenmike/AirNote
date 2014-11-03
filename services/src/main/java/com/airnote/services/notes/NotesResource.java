package com.airnote.services.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/notes")
public class NotesResource {
    @Autowired
    NotesService notesService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public NoteMetaInfo createNote(@RequestBody NoteCreationRequest request,@RequestHeader("Authorization") String accessToken){
       return notesService.createNote(request,accessToken);
    }

    @RequestMapping(value={"/{userId}"},method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<NoteMetaInfo> fetchListofNotes(@PathVariable String userId, @RequestParam(defaultValue = "10") Integer limit) throws NoContentException {
        List<NoteMetaInfo> noteMetaInfos = notesService.fetchNotesMetaInfo(userId, limit);
        if(noteMetaInfos.size()>0)
            return noteMetaInfos;
        else
            throw new NoContentException();
    }

    @RequestMapping(value={"/{userId}/{noteId}"},method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Note fetchNote(@PathVariable String userId,@PathVariable String noteId,@RequestHeader("Authorization") String accessToken) throws NoteNotFoundException {
        return notesService.fetchNote(userId, noteId, accessToken).orElseThrow(NoteNotFoundException::new);
    }


}
