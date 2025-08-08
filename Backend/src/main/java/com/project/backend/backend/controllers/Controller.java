package com.project.backend.backend.controllers;

import com.project.backend.backend.models.CollegesModel;
import com.project.backend.backend.services.SignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class Controller {
    private final SignUpService signUpService;

    public Controller(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/signUp")
    ResponseEntity<CollegesModel> signUpPage(@RequestBody CollegesModel collegesModel){
        return new ResponseEntity<>(signUpService.createOrganization(collegesModel), HttpStatus.CREATED);
    }

}
