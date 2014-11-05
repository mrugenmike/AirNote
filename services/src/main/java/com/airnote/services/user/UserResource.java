package com.airnote.services.user;

import com.airnote.services.notes.NoteCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET,produces="application/json" )
    @ResponseBody()
    public UserDetails createNote(@RequestHeader("Authorization") String accessToken) throws IncorrectTokenException {
        return userService.fetchUserDetails(accessToken);
    }
}
