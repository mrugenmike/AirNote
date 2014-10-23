package com.airnote.services.signup;

import com.airnote.services.signup.SignUpRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping({"airnote/api/users"})
public class SignUpController {

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    SignUpRequest SignUp(@RequestBody SignUpRequest signUpRequest){
        return signUpRequest;
    }

}
