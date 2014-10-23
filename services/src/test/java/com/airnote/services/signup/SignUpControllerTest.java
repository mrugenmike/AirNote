package com.airnote.services.signup;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpControllerTest {

    @Test
    public void itShouldReturnASignUpRequest(){
        SignUpController signUpController = new SignUpController();
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("abc@abc.com");
        signUpRequest.setName("Maithili");

        SignUpRequest actualResponse = signUpController.SignUp(signUpRequest);

        assertEquals(signUpRequest.getName(),actualResponse.getName());
        assertEquals(signUpRequest.getEmail(),actualResponse.getEmail());
    }

}